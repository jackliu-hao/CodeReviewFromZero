package com.llu.processbuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class TestProcess {

    public static void main(String[] args) throws IOException {
//        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "calc");
        ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "dir && calc");
        Process p = pb.start();
        // 输出结果
        // 命令正常执行的输出
        InputStream inputStream = p.getInputStream();
        // 命令执行失败的输出
        InputStream errorStream = p.getErrorStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
        String line = null;

        // 如果命令正确，输出结果
        if (inputStream != null){
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }

        // 如果命令失败，输出结果
        if (errorStream != null){
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream,"GBK"));
            while ((line = errorReader.readLine()) != null) {
                System.out.println(line);
            }
        }
        bufferedReader.close();
        inputStream.close();
        p.destroy();
        System.out.println("执行完毕");

    }
}
