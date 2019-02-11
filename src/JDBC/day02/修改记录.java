package day02;

import day01.使用连接池技术管理数据库连接;

import java.sql.Statement;

public class 修改记录 {
    public static void main(String[] args) {
        try {
            Statement statement = DBUtils.getConnection().createStatement();
            /**
             * UPDATE emp
             * SET emname = 'JACK'
             * WHERE empno = 10000
             */
            String sql = "UPDATE emp SET emname = 'JACK1' WHERE empno = 10000";
            System.out.println(sql);
            if (statement.executeUpdate(sql) > 0) {
                System.out.println("更新成功");
            } else {
                System.out.println("更新失败");
            }
            DBUtils.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
