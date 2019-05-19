package day03;

import day02.DBUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class 向表中追加一个新的部门同时向该部门添加一个用户 {
    public static void main(String[] args) {
        /*
        1：先插入一个新的部门记录
        2:在向USER表中插入一个员工信息
            但同时该员工的部门编号应该是
            新插入的部门记录的主键值
         */
        try {
            Connection connection = DBUtils.getConnection();
            Statement statement = connection.createStatement();
            /*
            先插入一个部门
             */
            String deptSql
                    = "INSERT INTO dept_zhanglianyu "
                    + "VALUES(dept_seq_zhanglinayu.NEXTVAL,'JAVA','SHANGHAI')";
            statement.executeUpdate(deptSql);
            /*
             获取刚刚插入的部门记录的主键值
             用于作为USER表中新记录外键值
             */
            String idSql
                    = "SELECT MAN(deptno) FROM dept_zhanglianyu";
            ResultSet resultSet
                    = statement.executeQuery(idSql);
            int id = 0;//新插入的部门id
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            resultSet.close();
            String userSql
                    = "INSERT INTO user_zhanglianyu "
                    + "(id,name,depton) "
                    + "VALUES(user_seq_zhanglianyu.NEXTVAL,'JACKSON',"
                    + id + ")";
            statement.executeUpdate(userSql);
            System.out.println("保存完毕");
            /*
            当Statement使用完毕后，应当关闭。
             */
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection();
        }
    }
}
