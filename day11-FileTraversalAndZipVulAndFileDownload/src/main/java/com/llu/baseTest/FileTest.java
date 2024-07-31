package com.llu.baseTest;

import java.io.*;

public class FileTest {
    public static void main(String[] args) throws Exception {
        String filename = "/aaa/../../velocityDemo.html";
        String filePath = System.getProperty("user.dir") + "/logs/" + filename;
        System.out.println(filePath);
        File file = new File(filePath);
        // 读取文件内容
        InputStream inputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF8"));
        String line ;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

    }
}
