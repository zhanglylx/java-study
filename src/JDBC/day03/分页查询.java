package day03;

import day02.DBUtils;
import sun.security.pkcs11.Secmod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 * 分页机制
 */

public class 分页查询 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入要查看的表名：");
        String tableName = scanner.nextLine().trim();
        System.out.println("请输入排序的列：");
        String colName = scanner.nextLine().trim();
        System.out.println("请输入一页显示的条数：");
        int pageSize = Integer.parseInt(scanner.nextLine().trim());
        System.out.println("请输入查看的页数：");
        int page = Integer.parseInt(scanner.nextLine().trim());
        try {
            Connection connection = DBUtils.getConnection();
            /*
             *  SELECT * FROM(
             *   SELECT ROWNUM rn,t.* FROM(
             *      SELECT * FROM t ORDER BY c
             *  )t
             * )
             *  WHERE rn BETWEEN ? AND ?
             *
             */
            String sql
                    = "SELECT * FROM ( " +
                    "SELECT ROWNUM rn,t.* FROM (" +
                    "SELECT * FROM " + tableName +
                    " ORDER BY " + colName + " " +
                    ") t " +
                    ") " +
                    "WHERE rn BETWEEN ? AND ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int start = (page - 1) * pageSize + 1;
            int end = page * pageSize;
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, end);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int rw = resultSet.getInt(1);
                int id = resultSet.getInt(2);
                String name = resultSet.getString(3);
                System.out.println(rw+":"+id+":"+name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection();
        }
    }

}
