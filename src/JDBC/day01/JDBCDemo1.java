package day01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * 通过Utils获取连接，并执行sql
 */
public class JDBCDemo1 {
    public static void main(String[] args) {
        try {
            Connection connection =
                    使用连接池技术管理数据库连接.getConnection();
            System.out.println("数据库已连接");
            Statement statement =
                    connection.createStatement();
            String sql = "SELECT * FROM emp";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("emname"));
            }
            /**
             * 结果集使用完毕后就应当关闭，释放资源。
             * 但是若Statement关闭了，那么rs也会自动关闭
             */
            resultSet.close();
            /**
             * 当不再通过Statement执行其他sql
             * 时，我们应当及时关闭Statement
             * 以释放JDBC与数据库的资源占用
             */
            statement.close();
            使用连接池技术管理数据库连接.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
