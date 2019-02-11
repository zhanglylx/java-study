package day02;

import java.sql.SQLException;
import java.sql.Statement;

public class 删除记录 {
    public static void main(String[] args) {
        try {
            Statement statement = DBUtils.getConnection().createStatement();
            String sql = "DELETE FROM emp WHERE empno=8000";
            if(statement.executeUpdate(sql)>0){
                System.out.println("删除成功");
            }else {
                System.out.println("删除失败");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
