package day02;

import day01.使用连接池技术管理数据库连接;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class 使用Statement执行DML操作 {
    public static void main(String[] args) {
        try {
            Statement statement = DBUtils.
                    getConnection().createStatement();
            //插入
            /**
             * INSERT INTO emp(empno,emname,deptno)
             * VALUES
             * (8000,'JACK',30)
             */
            String sql = "INSERT INTO emp VALUES (10000,'JACK',30)";
            System.out.println(sql);
            System.out.println(statement.executeUpdate(sql));
            DBUtils.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
