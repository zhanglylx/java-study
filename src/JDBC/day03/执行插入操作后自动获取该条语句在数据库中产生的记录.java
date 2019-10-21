package day03;

import day02.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * PreparedStatement支持一个方法
 * 可以在执行插入操作后，获取该条语句
 * 在数据库表中产生的记录中每个字段的值
 * 有了这个功能，我们在向从表中插入数据时
 * 可以获取该主键作为外键插入。而无需
 * 为获取主表中主键的值而进行一次查询
 */
public class 执行插入操作后自动获取该条语句在数据库中产生的记录 {
    public static void main(String[] args) {
        try {
            Connection connection
                    = DBUtils.getConnection();
            String sql = "INSERT INTO dept_zhanglianyu "
                    + "VALUES(dept_seq_zhanglianyu.NEXTVAL,)"
                    + "?,?";
            /*
            创建PreparedStatement时，可以使用Connection重载方法，
            第二个参数要求我们插入一个字符串数组，用来指定当通过
            ps执行插入操作后，该记录在表中想获取的值所在的字段名。
             */
            PreparedStatement statement
                    = connection.prepareStatement(sql, new String[]{"deptno", "dname"});
            statement.setString(1, "ORCALE");
            statement.setString(2, "NANJING");
            if (statement.executeUpdate() > 0) {
              /*
              获取刚刚插入进去的记录中关注的那几列的值
               */
                ResultSet resultSet
                        = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    //获取deptno的值
                    int deptno = resultSet.getInt("deptno");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
