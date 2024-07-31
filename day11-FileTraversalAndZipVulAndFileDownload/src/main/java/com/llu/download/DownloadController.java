package com.llu.download;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


@RestController
@RequestMapping("/day11")
public class DownloadController {

    Logger log = LoggerFactory.getLogger(DownloadController.class);

    @GetMapping("/download")
    public String download(String filename, HttpServletResponse response) {
        // 下载的文件路径
        String filePath = System.getProperty("user.dir") + "/logs/" + filename;
        log.info("[vul] 任意文件下载：" + filePath);

        try (InputStream inputStream = new BufferedInputStream(Files.newInputStream(Paths.get(filePath)))) {
            response.setHeader("Content-Disposition", "attachment; filename=" + filename);
            response.setContentLength((int) Files.size(Paths.get(filePath)));
            response.setContentType("application/octet-stream");

            // 使用 Apache Commons IO 库的工具方法将输入流中的数据拷贝到输出流中
            IOUtils.copy(inputStream, response.getOutputStream());
            log.info("文件 {} 下载成功，路径：{}", filename, filePath);
            return "下载文件成功：" + filePath;
        } catch (IOException e) {
            log.error("下载文件失败，路径：{}", filePath, e);
            return "未找到文件：" + filePath;
        }
    }

    //@ApiOperation(value = "vul：任意路径遍历")
    @GetMapping("/list")
    public String fileList(String filename) {
        String filePath = System.getProperty("user.dir") + "/logs/" + filename;
        log.info("[vul] 任意路径遍历：" + filePath);
        StringBuilder sb = new StringBuilder();

        File f = new File(filePath);
        File[] fs = f.listFiles();

        if (fs != null) {
            for (File ff : fs) {
                sb.append(ff.getName()).append("<br>");
            }
            return sb.toString();
        }
        return filePath + "目录不存在！";
    }
    //@ApiOperation(value = "safe：过滤../")
    @GetMapping("/download/safe")
    public String safe(String filename) {
        if (!checkTraversal(filename)) {
            String filePath = System.getProperty("user.dir") + "/logs/" + filename;
            return "安全路径：" + filePath;
        } else {
            return "检测到非法遍历！";
        }
    }

    /**
     * 目录遍历检测
     */
    public  boolean checkTraversal(String content) {
        return content.contains("..") || content.contains("/");
    }

}
