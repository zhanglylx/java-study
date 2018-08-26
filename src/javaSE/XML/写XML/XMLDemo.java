package XML.写XML;

import XML.读XML.Emp;
import org.dom4j.DocumentHelper;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用DOM写出一个XML
 * xml解析02  1:56：06
 */
public class XMLDemo {
    public static void main(String[] args) {
        List<Emp> list = new ArrayList<>();
        list.add(new Emp(1, "jack", 55, "男", 1000));
        list.add(new Emp(2, "boss", 55, "男", 2000));
        list.add(new Emp(3, "marry", 55, "女", 3000));
        list.add(new Emp(4, "kate", 55, "男", 4000));
        list.add(new Emp(5, "tom", 55, "女", 5000));

        /**
         * 生成一个xml的基本步骤
         * 1:创建文档对象Document
         * 2:为Document添加根节点
         * 3:为根节点组建树状结构
         * 4:创建XMLWriter
         * 5:为XMLWriter指定写出目标
         * 6:写出xml
         */
        Document doc = (Document) DocumentHelper.createDocument();

    }
}
