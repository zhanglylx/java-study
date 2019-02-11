package day01;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class 测试配置文件的读取Properties {
    public static void main(String[] args) {
        try {
            /**
             * java.util.Properties
             *
             * Properties类用于读取properties文件
             * 使用该类可以以类似Map的形式读取配置
             * 文件中的内容
             * properties文件中的内容格式类似:
             * user=openlab
             * 那么等号左面就是key,等号右面就是value
             */
            Properties prop
                    = new Properties();
            /**
             * 使用Properties去读取配置文件
             */
            FileInputStream fis
                    = new FileInputStream("./src/JDBC/day01/config.properties");
            prop.load(fis);
            fis.close();
            System.out.println("成功加载完毕配置文件");
            /**
             * 当加载完毕后,就可以根据文本文件中
             * 等号左面的内容(KEY)来获取等号右面的
             * 内容(VALUE)
             * 可以变相的把Properties看做一个Mao
             */
            String driver = prop.getProperty("driver").trim();
            String url = prop.getProperty("url").trim();
            String user = prop.getProperty("user").trim();
            String pwd = prop.getProperty("pwd").trim();
            System.out.println("driver:"+driver);
            System.out.println("url:"+url);
            System.out.println("user:"+user);
            System.out.println("pwd:"+pwd);

        } catch (Exception e) {
            e.printStackTrace();
    }
    }
}
