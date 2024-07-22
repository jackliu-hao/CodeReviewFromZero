package com.llu.baseTest;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

public class BaseTest {
    public static void main(String[] args) throws IOException {
        // 1、设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        // 2、初始化velocity引擎
        Velocity.init(prop);
        // 3、创建velocity容器
        VelocityContext context = new VelocityContext();
        context.put("name", "Hello Velocity");
        // 向容器中放入数据
        context.put("now", new Date());
        // 向容器中放入数据
        context.put("msg", "外部输入的消息");
        // 4、加载velocity模板
        Template tpl = Velocity.getTemplate("template/test.vm", "utf-8");
        // 5、合并数据到模板
        FileWriter fw = new FileWriter("./velocityDemo.html");
        tpl.merge(context, fw);
        // 6、释放资源
        fw.close();
    }
}
