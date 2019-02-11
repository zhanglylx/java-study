package day02;

import java.sql.Statement;

/**
 * 创建表  user_zhanglianyu
 * 字段:
 * id     NUMBER(4)
 * name   VARCHAR2(30)
 * password VARCHAR2(30)
 * MONEY  NUMBER(10)
 * email  VARCHAR2(60)
 */
public class 创建表 {
    public static void main(String[] args) {
        try {
            /**
             * CREATE TABLE user_zhanglianyu(
             *  id NUMBER(4),
             *  name VARCHAR2(30),
             *  password VARCHAR2(30),
             *  money NUMBER(6),
             *  email VARCHAR2(60)
             * )
             */
            String sql = "CREATE TABLE user_zhanglianyu(" +
                    "id NUMBER(4)," +
                    "name VARCHAR2(30)," +
                    "password VARCHAR2(30)," +
                    "money NUMBER(6)," +
                    "email VARCHAR2(60)" +
                    ")";
            Statement statement = DBUtils.getConnection().createStatement();
            /**
             * 使用execute(String sql)执行DDL
             * execute方法可以执行任何sql语句
             * 返回值为true:说明执行的是查询语句,并返回了结果集
             */
            boolean b = statement.execute(sql);
            if (!b) {
                System.out.println("表创建成功");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection();
        }
    }
}
