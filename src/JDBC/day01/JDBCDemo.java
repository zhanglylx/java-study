package day01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 测试使用JDBC连接oracle数据库
 */
public class JDBCDemo {
    public static void main(String[] args) {
        try {
            /**
             * 第一步
             * 加载驱动
             * 当出现了:java.lang.ClassNotFoundException: oracle.jdbc.driver.OracleDriver
             * 异常时，说明数据库的驱动jar包没有导入到项目中
             */
            Class.forName("oracle.jdbc.driver.OracleDriver");
            /**
             * 第二步:
             * 通过DriverManager获取数据库连接
             * 注意:
             * 导入的包都在java.sql.*
             * DriverManager连接ORACLE时的路径格式
             * jdbc:oracle:thin:@<host>:<port>:<sid>
             *
             * Mysql的路径格式
             * mysql端口通常是:3306
             * jdbc:mysql://<host>:<port>/<dbname>
             */
            Connection conn
                    = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl",
                    "system",
                    "Jia60951327"
            );
            /**
             * 通过Connection创建Statement
             * 用来执行sql语句
             */
            Statement state =
                    conn.createStatement();

            /**
             * 通过Statement执行查询语句
             * 查询emp表中的信息
             * SELECT empno,emname FROM emp
             */
            String sql = "SELECT empno,emname FROM emp";
            //输出SQL，用于检查拼写是否有错误
            System.out.println(sql);
            /**
             * 使用executeQuery来执行SQL语句
             * 并且查询后会得到一个查询结果集
             */
            ResultSet rs =
                    state.executeQuery(sql);
            /**
             * 需要注意的是:ResultSet表示的
             * 是查询结果集，但实际上查询的结果集在
             * ORACLE数据服务器上,并没有全部保存在
             * 本地，所以，通过ResultSet的next方法
             * 获取下一条记录时,ResultSet会发送请求
             * 至服务器获取数据，若连接已经关闭，会
             * 抛出异常
             */
            while (rs.next()){
                int empno = rs.getInt("empno");
                String ename = rs.getString("emname");
                System.out.println(empno+":"+ename);
            }
            //关闭连接
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
