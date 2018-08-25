package XML;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 使用DOM解析XML文件
 */
public class XMLDemo1 {
    public static void main(String[] args) {
        try {
            /**
             * 解析XML文件的基本流程
             * 1:创建SAXReader,用来读取XML文件
             * 2:指定xml文件使用SAXReader读取，
             *   并解析文档对象Document
             * 3:获取根元素
             * 4:获取每一个元素，从而达到解析的目的。
             */
            //1
            //org.dom4j.xxx
            SAXReader reader = new SAXReader();

            //2
            /**
             * 常用的读取方法
             * Document read(InputStream in)
             * Document read(Reader read)
             * Document read(File file)
             */
            File xmlFile = new File("./src/javaSe/XML/ emp.xml");
            /**
             * read方法的作用:
             * 读取给定的xml，并将其解析转换为一个Document对象
             * 实际上这里已经完成了对整个xml解析的工作。
             * 并将所有内容封装到了Document对象中
             * Document对象可以描述当前xml文档
             */
            Document doc = reader.read(xmlFile);
            //3
            Element root = doc.getRootElement();
            //4
            /**
             * Element element(String name)
             * 获取当前标签下第一个名为给定名字的标签
             *
             * List elements(String name)
             * 获取当前标签下所有给定名字的标签
             *
             * List elements()
             * 获取当前标签下所有子标签
             *
             */
            List<Element> elements  = root.elements();
            /**
             * 遍历每一个emp标签
             */
            for(Element element : elements){

            }






        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
