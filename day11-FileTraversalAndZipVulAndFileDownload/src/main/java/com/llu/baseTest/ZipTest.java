package com.llu.baseTest;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipTest {
    public static void main(String[] args) throws Exception {
        String filePath = System.getProperty("user.dir") + "\\zip";
        ZipFile zip = new ZipFile(new File(filePath + "\\out.zip"));

        // 创建一个指定目录用于存储解压后的文件
        File destinationDir = new File(filePath + "\\zipSlid");

        // 获取 ZIP 文件中的所有条目
        Enumeration<? extends ZipEntry> entries = zip.entries();

        // 遍历 ZIP 文件中的每个条目
        while (entries.hasMoreElements()) {
            ZipEntry e = entries.nextElement();

            // 构建目标文件对象，使用解压后的文件名和指定的存储目录
            File f = new File(destinationDir, e.getName());

            // 从 ZIP 条目中获取输入流
            InputStream is = zip.getInputStream(e);

            // 将输入流内容复制到目标文件
            // （注意：copy 方法需要定义，可能是第三方库 IOUtils 提供的）
            IOUtils.copy(is,new FileOutputStream(f));
        }

    }
}
