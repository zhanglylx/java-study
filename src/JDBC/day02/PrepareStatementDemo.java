package day02;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 * 使用预编译SQL提高执行效率
 */
public class PrepareStatementDemo {
    public static void main(String [] args){
        try {
            Connection connection =
                    DBUtils.getConnection();
            Statement statement =
                    connection.createStatement();
            String sql ="INSET INTO user_zhanglianyu "+
                    "VALUES(?,?,?,?,?)";
            PreparedStatement prepareStatement =
                    connection.prepareStatement(sql);
            long start = System.currentTimeMillis();
                for(int i=2000;i<3000;i++){
                    prepareStatement.setInt(1,i);
                    prepareStatement.setString(2,"TESTUSER"+i);
                    prepareStatement.setString(3,"TESTPWD");
                    prepareStatement.setString(4,"123.13");
                    prepareStatement.setString(5,"TEST@TEST.TEST");
                    prepareStatement.executeUpdate();
                }
            System.out.println("插入完毕");
            System.out.println("耗时"+(System.currentTimeMillis()-start));


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
