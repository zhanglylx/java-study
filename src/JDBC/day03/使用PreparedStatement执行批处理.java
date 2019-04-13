package day03;

import day02.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class 使用PreparedStatement执行批处理 {
    public static void main(String[] args) {
        try {
            Connection connection = DBUtils.getConnection();
            String sql
                    = "INSERT INTO user_zhanglianyu" +
                    "(id,name) " +
                    "VALUES(?,?)";
            PreparedStatement preparedStatement
                    = connection.prepareStatement(sql);
            for(int i=9500;i<9999;i++){
                preparedStatement.setInt(1,i);
                preparedStatement.setString(2,"test"+i);
                preparedStatement.addBatch();
            }
            int[] i=preparedStatement.executeBatch();
            System.out.println(i.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
