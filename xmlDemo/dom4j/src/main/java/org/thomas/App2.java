package org.thomas;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.Iterator;

/**
 * author Thomas
 * version V1.0
 * 2020/11/5 10:28
 * Description:
 */
public class App2 {
    public static void main(String[] args) throws DocumentException {
        SAXReader reader = new SAXReader();

        Document document = reader.read(new File("E:\\Documents\\jacob\\books.xml"));

        Element root = document.getRootElement();


        Iterator it = root.elementIterator();

        while (it.hasNext()) {

            Element element = (Element) it.next();


            //未知属性名称情况下

            /*Iterator attrIt = element.attributeIterator();

            while (attrIt.hasNext()) {

                Attribute a  = (Attribute) attrIt.next();

                System.out.println(a.getValue());

            }*/


            //已知属性名称情况下

            System.out.println("id: " + element.attributeValue("id"));


            //未知元素名情况下

            /*Iterator eleIt = element.elementIterator();

            while (eleIt.hasNext()) {

                Element e = (Element) eleIt.next();

                System.out.println(e.getName() + ": " + e.getText());

            }

            System.out.println();*/


            //已知元素名情况下

            System.out.println("title: " + element.elementText("title"));

            System.out.println("author: " + element.elementText("author"));

            System.out.println();

        }
    }
}
