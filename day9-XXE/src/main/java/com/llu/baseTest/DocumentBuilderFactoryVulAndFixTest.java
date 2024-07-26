package com.llu.baseTest;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileInputStream;

public class DocumentBuilderFactoryVulAndFixTest {

    public static void main(String[] args) throws Exception {
        fix();
    }

    public static void vul() throws Exception {
       /**
     * 创建一个 DocumentBuilderFactory 实例来解析 XML 文档
     */
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    /**
     * 使用 DocumentBuilderFactory 创建一个 DocumentBuilder 实例
     */
    DocumentBuilder builder = factory.newDocumentBuilder();

    /**
     * 解析 example.xml 文件并获取一个 Document 对象
     */
    Document document = builder.parse(new File("C:\\Users\\it\\Desktop\\code\\代码审计\\CodeReviewFromZero\\day9-XXE\\src\\main\\java\\com\\llu\\baseTest\\example.xml"));

    /**
     * 输出文档根元素的内容
     * 这里处理文档
     */
    System.out.println(document.getDocumentElement().getTextContent());


    }

    /**
     * DocumentBuilder builder = dbf.newDocumentBuilder();这行代码需要在dbf.setFeature()之后才能够生效；
     */

    public static void fix() throws Exception {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        // 错误的修改方式 DocumentBuilder builder = dbf.newDocumentBuilder();这行代码需要在dbf.setFeature()之后才能够生效；
        // DocumentBuilder builder = dbf.newDocumentBuilder();
        String FEATURE = null;
        FEATURE = "http://javax.xml.XMLConstants/feature/secure-processing";
        dbf.setFeature(FEATURE, true);
        FEATURE = "http://apache.org/xml/features/disallow-doctype-decl";
        dbf.setFeature(FEATURE, true);
        FEATURE = "http://xml.org/sax/features/external-parameter-entities";
        dbf.setFeature(FEATURE, false);
        FEATURE = "http://xml.org/sax/features/external-general-entities";
        dbf.setFeature(FEATURE, false);
        FEATURE = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
        dbf.setFeature(FEATURE, false);
        dbf.setXIncludeAware(false);
        dbf.setExpandEntityReferences(false);
        // 正确的修复方式
        DocumentBuilder builder = dbf.newDocumentBuilder();
        /**
         * 解析 example.xml 文件并获取一个 Document 对象
         */
        Document document = builder.parse(new File("C:\\Users\\it\\Desktop\\code\\代码审计\\CodeReviewFromZero\\day9-XXE\\src\\main\\java\\com\\llu\\baseTest\\example.xml"));

        /**
         * 输出文档根元素的内容
         * 这里处理文档
         */
        System.out.println(document.getDocumentElement().getTextContent());
    }
}
