package com.llu.baseTest;

import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;


public class XmlReaderVulAndFixTest {

    public static void main(String[]args) throws Exception {
        XmLReaderFixTest();
    }


    //XMLReader
    public static void XMLReaderTest() throws SAXException, IOException, ParserConfigurationException {

        // 创建XMLReader实例
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();

        // 创建处理器
        DefaultHandler handler = new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                // 处理开始元素
                System.out.println("Start Element: " + qName);
            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                // 处理结束元素
                System.out.println("End Element: " + qName);
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                // 处理元素内容
                System.out.println("Characters: " + new String(ch, start, length));
            }
        };

        // 将处理器设置为XMLReader的内容处理器
        xmlReader.setContentHandler(handler);


        // 解析XML文档
        File file = new File("C:\\Users\\it\\Desktop\\code\\代码审计\\CodeReviewFromZero\\day9-XXE\\src\\main\\java\\com\\llu\\baseTest\\example.xml");
        InputSource inputSource = new InputSource(file.toURI().toURL().toString());
        xmlReader.parse(inputSource);


    }
    public static void XmLReaderFixTest() throws Exception {
        // 配置SAXParserFactory以防止XXE攻击
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 创建XMLReader实例 ，错误的修复方式， 需要先设置setFeature ，在获取 xmlReader
//        XMLReader xmlReader = factory.newSAXParser().getXMLReader();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
        // 正确的修复方式
        XMLReader xmlReader = factory.newSAXParser().getXMLReader();


        // 创建处理器
        DefaultHandler handler = new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                System.out.println("Start Element: " + qName);
            }

            @Override
            public void endElement(String uri, String localName, String qName) {
                System.out.println("End Element: " + qName);
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                System.out.println("Characters: " + new String(ch, start, length));
            }
        };

        // 将处理器设置为XMLReader的内容处理器
        xmlReader.setContentHandler(handler);

        // 解析XML文档
        File file = new File("C:\\Users\\it\\Desktop\\code\\代码审计\\CodeReviewFromZero\\day9-XXE\\src\\main\\java\\com\\llu\\baseTest\\example.xml");
        InputSource inputSource = new InputSource(file.toURI().toURL().toString());
        xmlReader.parse(inputSource);

    }

}


