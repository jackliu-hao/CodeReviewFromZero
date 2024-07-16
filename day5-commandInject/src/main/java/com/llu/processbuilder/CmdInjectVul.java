package com.llu.processbuilder;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CmdInjectVul {

    @GetMapping("/ping")
    public Map ping(String ip) throws Exception {
//        ProcessBuilder pb = new ProcessBuilder("ping" ,ip);
        ProcessBuilder pb = new ProcessBuilder("cmd","/c","ping" ,ip);
        Process p = pb.start();
        InputStream inputStream = p.getInputStream();
        // 命令执行失败的输出
        InputStream errorStream = p.getErrorStream();
        // 封装返回结果
        HashMap hashMap = new HashMap();


        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"GBK"));
        String line = null;
        StringBuilder result = new StringBuilder();

        // 如果命令正确，输出结果
        if (inputStream != null){
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                result.append(line + "<hr/>");

            }
        }

        // 如果命令失败，输出结果
        if (errorStream != null){
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(errorStream,"GBK"));
            while ((line = errorReader.readLine()) != null) {
                System.out.println(line);
                result.append(line + "<hr/>");
            }
        }
        bufferedReader.close();
        inputStream.close();
//        p.destroy();
        System.out.println("执行完毕");
        hashMap.put("data",result);
        return hashMap;
    }
}
