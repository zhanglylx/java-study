package XML;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * DOM4J对XPATH的支持
 */
public class XPath {
    public static void main(String[] args) {
        try {
            //读取xml文件转换为Document
            SAXReader reader
                    = new SAXReader();
            Document doc = reader.read(
                    new File(
                            "myemp.xml"));
//            String path = "/list/emp[salary<5000]";
//            String path = "//@id";
            //获取所有女同志的工资
            //String path = "/list/emp[gender='女']/salary";
            //获取所有女同志的工资大于4000
            String path = "/list/emp[gender='女']/salary[.>4000]";
            /**
             * List selectNodes(String xpath)
             * 根据给定的XPATH查询对应的节点
             */
            List list
                    = doc.selectNodes(path);
            for(Object o : list){
                System.out.println(o);
            }
        } catch (Exception e) {

        }
    }
}
