package day02;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 与用户相关的业务逻辑
 */
public class UserService {
    public static void main(String[] args) {
        /*
         *程序启动后:
         * 选择1,2,3,4等操作
         * 1:注册新用户  用户ID从1开始
         * 2:更改用户信息
         * 3:删除用户信息
         * 4:查询用户信息
         */
        regUser("张连宇", "123456", "1000", "zhanglianyu@qq.com");
    }

    public static void regUser(String name, String password, String money, String email) {
        try {
            Statement statement = DBUtils.getConnection().createStatement();
            int id = 1;
            String sql = "SELECT MAX(id) as maxID FROM user_zhanglianyu";
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                id += resultSet.getInt("maxID");
            }
            sql = "INSERT INTO user_zhanglianyu VALUES (" +
                    id + "," +
                    "'" + name + "'" + "," +
                    "'" + password + "'" + "," +
                    "'" + money + "'" + "," +
                    "'" + email + "'" +
                    ")";
            System.out.println(sql);
            System.out.println(statement.executeUpdate(sql));
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection();
        }
    }

    public static void updateUser() {

    }
}
