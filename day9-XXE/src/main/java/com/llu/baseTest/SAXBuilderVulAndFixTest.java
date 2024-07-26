package com.llu.baseTest;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class SAXBuilderVulAndFixTest {

    public static void main(String[] args)throws Exception {
        fix();
    }
    public static void vul() throws Exception {
        // 创建SAXBuilder实例
        SAXBuilder saxBuilder = new SAXBuilder();

        // 解析XML文件并构建Document对象
        Document document = saxBuilder.build(new File("C:\\Users\\it\\Desktop\\code\\代码审计\\CodeReviewFromZero\\day9-XXE\\src\\main\\java\\com\\llu\\baseTest\\example.xml"));

        // 获取根元素
        Element rootElement = document.getRootElement();
        System.out.println("Root element: " + rootElement.getName());

        // 获取并遍历子元素
        for (Element element : rootElement.getChildren()) {
            System.out.println("Element: " + element.getName() + ", Value: " + element.getText());
        }

    }
    public static void fix() throws Exception  {
        // 创建SAXBuilder实例
//        SAXBuilder saxBuilder = new SAXBuilder();
        // 防止XXE攻击，方式2,
        SAXBuilder saxBuilder = new SAXBuilder(true);

        // 防止XXE攻击,方式1
//        saxBuilder.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
//        saxBuilder.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
//        saxBuilder.setFeature("http://xml.org/sax/features/external-general-entities", false);
//        saxBuilder.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
//        saxBuilder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        // 解析XML文件并构建Document对象
        Document document = saxBuilder.build(new File("C:\\Users\\it\\Desktop\\code\\代码审计\\CodeReviewFromZero\\day9-XXE\\src\\main\\java\\com\\llu\\baseTest\\example.xml"));

        // 获取根元素
        Element rootElement = document.getRootElement();
        System.out.println("Root element: " + rootElement.getName());

        // 获取并遍历子元素
        for (Element element : rootElement.getChildren()) {
            System.out.println("Element: " + element.getName() + ", Value: " + element.getText());
        }

    }
}
