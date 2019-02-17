package day02;

import java.sql.*;
import java.util.Scanner;

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


    /**
     * 自己的写法
     *
     * @param name
     * @param password
     * @param money
     * @param email
     */
    public static void regUser(String name, String password, String money, String email) {
        /**
         * 若是注册用户操作:
         * 1:获取用户输入的相关信息
         * 2:获取连接
         * 3:获取Statement
         * 4.先获取id的最大值
         * 5;对该值+1,作为当前记录的主键值
         * 6:插入记录
         * 7:关闭连接
         */
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

    /**
     * 老师的写法
     */
    public static void regUser(Scanner scanner) {
        /**
         * 若是注册用户操作:
         * 1:获取用户输入的相关信息
         * 2:获取连接
         * 3:获取Statement
         * 4.先获取id的最大值
         * 5;对该值+1,作为当前记录的主键值
         * 6:插入记录
         * 7:关闭连接
         */
        try {
            System.out.println("请输入用户名:");
            String user = scanner.nextLine().trim();
            System.out.println("请输入密码:");
            String pwd = scanner.nextLine();
            System.out.println("请输入账户金额:");
            String money = scanner.nextLine();
            System.out.println("请输入邮箱地址:");
            String email = scanner.nextLine();
            Connection connection = DBUtils.getConnection();
            Statement statement = connection.createStatement();
            String idSql =
                    "SELECT MAX(id) AS id FROM user_zhanglianyu";
            ResultSet resultSet =
                    statement.executeQuery(idSql);
            int id = -1;
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            //统计出最大值后，对ID加1
            id++;
            resultSet.close();

            //6
            String sql = "INSERT INTO user_zhanglianyu VALUES (" +
                    id + "," +
                    "'" + user + "'" +
                    ",'" + pwd + "'" +
                    ",'" + money + "'" +
                    ",'" + email + "'" +
                    ")";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection();
        }
    }

    /**
     * 登录
     *
     * @param scanner
     */
    public static void login(Scanner scanner) {
        /*
         *1:要求用户输入用户名及密码
         * 2:根据用户输入作为条件去表查询
         * 3:若查询出数据,说明输入正确
         */
        System.out.println("现在是登录操作");
        System.out.println("请输入用户名:");
        String user = scanner.nextLine().trim();
        System.out.println("请输入密码");
        String pwd = scanner.nextLine().trim();
        try {
            Connection connection =
                    DBUtils.getConnection();
            String sql =
                    "SELECT  *" +
                            "FROM user_zhanglianyu " +
                            "WHERE name=? AND password=?";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql);

            if (preparedStatement.executeQuery().next()) {
                System.out.println("登录成功");
            } else {
                System.out.println("用户名或密码错误");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection();
        }
    }

    public static void updateUser() {

    }
}
