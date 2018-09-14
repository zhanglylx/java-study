package XML.写XML;

import XML.读XML.Emp;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

import java.io.FileOutputStream;
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

        /**
         * Document的方法
         * Element addElement(String name)
         * 该方法用于向文档中添加给定名字的根
         * 元素，返回Element实例就表示该根元素
         * 需要注意的是，该方法只能调用一次。
         * 调用第二次会抛出异常。
         */
        Element root = (Element) doc.addElement("list");
        /**
         * 循环添加每一个员工信息
         */
        for (Emp e : list) {
            /**
             * Element同样支持方法:
             * Element addElement(String name)
             * 向当前标签中添加给定名字的子标签
             */
            //向根标签中添加emp标签
            Element emp =
                    root.addElement("emp");

            //向emp标签中添加子标签name
            Element name =
                    emp.addElement("name");
            name.addText(e.getName());
            //简写
            emp.addElement("age")
                    .addText(String.valueOf(e.getAge()));
            emp.addElement("gender")
                    .addText(String.valueOf(e.getGender()));
            emp.addElement("salary")
                    .addText(String.valueOf(e.getSalary()));
            /**
             * 为标签添加属性
             * Element addAttribute(
             *      String name,String value
             *      )
             * 为当前标签添加给定名字以及对应值得属性
             * 返回值仍然为当前标签
             * 这样做的目的是可以连续添加若干属性
             * 就好像StringBuilder的append的
             * 返回值效果和作用
             */
            emp.addAttribute(
                    "id", String.valueOf(e.getId()));
        }
        /**
         * 当退出循环后，那么Document中的
         * 结构就已经构建完成
         * 需要将其写出为xml
         */
        try {
            XMLWriter writer = new XMLWriter();
            FileOutputStream out
                    = new FileOutputStream("myemp.xml");
            writer.setOutputStream(out);
            /**
             * 将Document对象写出到文件中
             * 这时会将Document转换为xml格式
             * 写入文件
             */
            writer.write(doc);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
