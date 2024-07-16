package com.llu.runtime;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RuntimeTest {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process p = runtime.exec("cmd /c ping 127.0.0.1&&dir");

//            Process p = runtime.exec(new String[]{"cmd","/c","ping 127.0.0.1 && dir"});

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
            System.out.println("执行完毕");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
