package com.llu.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.RequestContext;
import org.apache.commons.io.IOUtils;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;
import java.util.List;

@RestController
public class MyTestController {

    @Value("${file.upload-dir}")
    private String uploadDir;


    @GetMapping("/test")
    public String test(){
        return "test";
    }

    /**
     * 文件上传，通过文件流的方式上传
     */

    @PostMapping("/uploadByStream")
    // 需要配置，关闭springboot默认的文件封装 spring.servlet.multipart.enabled=false
    public String upload(@RequestParam("file") CommonsMultipartFile file){
        // 获取上传的源文件名
        System.out.println( "fileName" + file.getOriginalFilename());
        //这样，fos和inputStream会在try块结束时自动关闭，无需显式调用close()方法。
        //使用Java 7引入的try-with-resources语句，可以自动关闭实现了AutoCloseable接口的资源

        try(
                // 尝试创建一个文件输出流，用于将输入流中的数据写入到指定的文件中
                FileOutputStream fos = new FileOutputStream(uploadDir + new Date().getTime() + file.getOriginalFilename());
                // 获取上传文件的输入流
                InputStream inputStream = file.getInputStream()
        ) {
            // 读取输入流中的数据，并将其写入到输出流中
            int temp;
            while ((temp = inputStream.read()) != -1) {
                fos.write(temp);
            }
            // 确保所有数据都被写入到文件中
            fos.flush();
        } catch (Exception e) {
            // 如果在处理文件过程中发生异常，则抛出运行时异常
            throw new RuntimeException(e);
        }

        return "success";

    }


    // 通过ServletFileUpload 封装上传
    @PostMapping("/uploadServletFileUpload")
    // 需要配置，关闭springboot默认的文件封装 spring.servlet.multipart.enabled=false
    public String uploadFile(HttpServletRequest request ) throws FileUploadException, UnsupportedEncodingException {
        /**
         * 定义上传文件的目录。
         */
        String tmpPath= "/tmp/";
        // 目标路径
        String realPath = request.getServletContext().getRealPath("/upload");
        File realFile = new File(realPath);
        if (!realFile.exists() && !realFile.isDirectory()){
            realFile.mkdirs();
        }
        // 临时目录
        File temFile = new File(tmpPath);
        if (!temFile.exists() && !temFile.isDirectory()){
            temFile.mkdirs();
        }
        //创建DiskFileItemFactory对象后，可以通过调用其方法来创建FileItem对象。
        // 这些FileItem对象可以用于处理上传的文件或表单字段。
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        // 设置临时目录
        diskFileItemFactory.setRepository(temFile);
        //创建一个ServletFileUpload对象，用于处理文件上传操作，并通过DiskFileItemFactory对象对文件项的存储方式进行配置。
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        servletFileUpload.setHeaderEncoding("UTF-8");

        if (!servletFileUpload.isMultipartContent(request)){
            //如果请求不是multipart/form-data类型
            return "notMultipartContent";
        }
        //将请求中的文件和表单数据转换为FileItem对象的列表。每个FileItem对象代表一个表单字段或上传的文件。
        // 可以通过遍历fileItems列表来处理每个字段或文件。
        //  如果字段是文件，则可以使用FileItem对象的方法获取文件名、内容类型、输入流等信息。
        //  如果字段是普通表单字段，则可以使用FileItem对象的方法获取字段的名称和值
        List<FileItem> fileItems = servletFileUpload.parseRequest(request);

        for (FileItem fileItem : fileItems) {
            if (fileItem.isFormField()){
                //普通表单字段
                String fieldName = fileItem.getFieldName();
                String fieldValue = fileItem.getString("Utf-8");
                System.out.println("fieldName：" + fieldName + "  fieldValue：" + fieldValue);
            }else {
                //
                String fileName = fileItem.getName();
                if (fileName != null && !"".equals(fileName)){
                    // 获取上传文件的原始文件名
                    // todo
                    String suffix = fileName.substring(fileName.indexOf("."));
                    // todo
//                    fileName = new Date().getTime() + suffix;
                    try(
                            // 创建一个文件输出流，用于将上传的文件写入到指定的文件中

                            FileOutputStream fos = new FileOutputStream(realPath + "/" + fileName);
                            // 获取上传文件的输入流
                            InputStream inputStream = fileItem.getInputStream()
                    ) {
                        // 读取输入流中的数据，并将其写入到输出流中
                        int temp;
                        while ((temp = inputStream.read()) != -1) {
                            // 该函数的功能是将字节temp写入到文件输出流fos中。
                            fos.write(temp);
                        }
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        return "uploadSuccess";
    }

    // 通过 MultipartFile 方式
    @PostMapping("/uploadMultipartFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()){
            return "file is empty";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
//        String uploadDir = "./test/";
        // 获取文件后缀
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        File dest = new File(uploadDir + fileName);
        // dest 如果不存在就创建
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            // 返回文件路径
            return "success! filePath: is " + uploadDir + fileName;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
