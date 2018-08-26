package XML.读XML;

import XML.读XML.Emp;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
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
            File xmlFile = new File("./src/javaSe/XML/读XML/emp.xml");
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
            List<Element> elements = root.elements();

            /**
             * 创建一个集合，用于保存xml中
             * 的每一个用户信息。我们先将
             * 用户信息取出，然后创建一个Emp实例，
             * 将信息设置到该实例的相应属性上。最终将所有emp对象
             * 存入该集合。
             */
            List<Emp> list = new ArrayList<>();
            /**
             * 遍历每一个emp标签
             */
            Emp e;
            Attribute id;
            Element name;
            String nameStr;
            Element eage ;
            String eageStr;
            Element gender;
            String genderStr;
            Element salary;
            String salaryStr;
            for (Element emp : elements) {
                //创建一个Emp对象，用于保存信息
                e = new Emp();

                /**
                 * 解析emp标签
                 */
                //获取name的值
                /**
                 * 首先获取名为name的字标签
                 * 其次，获取前后标签中间的文本
                 */
                name = emp.element("name");
                nameStr = name.getText();
                e.setName(nameStr);

                //获取年龄
                eage = emp.element("age");
                eageStr = eage.getText();
               e.setAge(Integer.parseInt(eageStr));

               //获取性别
                gender = emp.element("gender");
                genderStr = gender.getText();
                e.setGender(genderStr);

                //获取薪资
                salary = emp.element("salary");
                salaryStr = salary.getText();
                e.setSalary(Integer.parseInt(salaryStr));

                /**
                 * 通过Element获取元素属性
                 * 获取id属性
                 * Attribute attribute(String name)
                 * 获取当前标签中指定名字的属性
                 *
                 * String getValue()
                 * 获取该属性的值
                 *
                 * String getName()
                 * 获取该属性的名字
                 */
                id = emp.attribute("id");
                e.setId(Integer.parseInt(id.getValue()));
                //添加到empList
                list.add(e);
            }
            System.out.println("解析了"+list.size()+"个员工信息");
            for(Emp ignored : list){
                System.out.println(ignored.toString());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
