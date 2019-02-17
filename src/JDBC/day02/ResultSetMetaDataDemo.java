package day02;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

/**
 * 取的结果集中的元数据
 */
public class ResultSetMetaDataDemo {
    public static void main(String[] args) {
        try {
            Connection connection =
                    DBUtils.getConnection();
            Statement statement =
                    connection.createStatement();
            String sql = "SELECT * FROM emp";
            ResultSet resultSet =
                    statement.executeQuery(sql);
            ResultSetMetaData resultSetMetaData =
                    resultSet.getMetaData();
            /*
             *获取当前结果集查询的列总过多少个
             */
            int columnCount =
                    resultSetMetaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String colName =
                        resultSetMetaData.getCatalogName(i);
                System.out.println(colName);
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection();
        }
    }


}



