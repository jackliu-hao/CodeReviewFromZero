package com.llu.js;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileReader;
import java.io.IOException;

public class JavaScriptExecutor {
    public static void main(String[] args) {
        // 创建一个 ScriptEngineManager 实例
        ScriptEngineManager manager = new ScriptEngineManager();

        // 通过 ScriptEngineManager 获取一个 JavaScript 引擎实例
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        // 检查是否成功获取引擎
        if (engine != null) {
            System.out.println("JavaScript 引擎已成功加载。");
        } else {
            System.out.println("未能加载 JavaScript 引擎。");
        }
//        try {
//            // 执行 JavaScript 代码
//            engine.eval("print('Hello, World!');");
//            engine.eval("var array = [1, 2, 3, 4, 5];for (var i = 0; i < array.length; i++) {print('index:' + i + ',value:' + array[i]);}");
//        }catch (Exception e){
//            e.printStackTrace();
//        }

        try {
            String test = "var a = mainOutput(); function mainOutput() { var x=java.lang.Runtime.getRuntime().exec(\"calc\"); };";
            engine.eval(test);

        }catch (Exception e){

        }

    }
}


