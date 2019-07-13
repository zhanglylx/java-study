package day03.DAO;

import day02.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于操作数据库User表的DAO
 */
public class UserDAO {
    private static final String FIND_BY_ID_SQL = "SELECT * FROM user_zhanglianyu WHERE ID=?";
    private static final String FIND_ALL = "SELECT * FROM user_zhanglianyu";
    private static final String SAVEN_USER = "INSERT INTO  user_zhanglianyu " +
            "VALUES(2000,?,?,?,?,?)";

    /**
     * 根据ID查询对应的user记录
     *
     * @param id
     * @return
     */
    public User_ZhangLianYu findById(int id) {
        try {
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User_ZhangLianYu user_zhangLianYu = new User_ZhangLianYu();
                int i = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String pwd = resultSet.getString("password");
                int money = resultSet.getInt("money");
                String email = resultSet.getString("email");
                int deptno = resultSet.getInt("deptno");
                user_zhangLianYu.setId(i);
                user_zhangLianYu.setName(name);
                user_zhangLianYu.setPassword(pwd);
                user_zhangLianYu.setMoney(money);
                user_zhangLianYu.setEmail(email);
                user_zhangLianYu.setDeptno(deptno);
                preparedStatement.close();
                return user_zhangLianYu;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection();
        }
        return null;
    }

    /**
     * 查询所有的user记录
     *
     * @return
     */
    public List<User_ZhangLianYu> findAll() {
        try {
            Connection connection = DBUtils.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(FIND_ALL);
            List<User_ZhangLianYu> lianYus = new ArrayList<>();
            while (resultSet.next()){
                User_ZhangLianYu user_zhangLianYu = new User_ZhangLianYu();
                int i = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String pwd = resultSet.getString("password");
                int money = resultSet.getInt("money");
                String email = resultSet.getString("email");
                int deptno = resultSet.getInt("deptno");
                user_zhangLianYu.setId(i);
                user_zhangLianYu.setName(name);
                user_zhangLianYu.setPassword(pwd);
                user_zhangLianYu.setMoney(money);
                user_zhangLianYu.setEmail(email);
                user_zhangLianYu.setDeptno(deptno);
                lianYus.add(user_zhangLianYu);
            }
            statement.close();
            return lianYus;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtils.closeConnection();
        }
        return null;
    }

    /**
     * 保存一个user信息
     *
     * @param user_zhangLianYu
     * @return
     */
    public boolean save(User_ZhangLianYu user_zhangLianYu) {
        try{
            Connection connection = DBUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVEN_USER);
            preparedStatement.setString(1,user_zhangLianYu.getName());
            preparedStatement.setString(2,user_zhangLianYu.getPassword());
            preparedStatement.setInt(3,user_zhangLianYu.getMoney());
            preparedStatement.setString(4,user_zhangLianYu.getEmail());
            preparedStatement.setInt(5,user_zhangLianYu.getDeptno());

            if(preparedStatement.executeUpdate()>0){
                ResultSet resultSet = preparedStatement.getGeneratedKeys();
                resultSet.next();
                int id = resultSet.getInt(1);
                user_zhangLianYu.setId(id);
                preparedStatement.close();
                return true;
            }
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            DBUtils.closeConnection();
        }
        return false;
    }

    /**
     * 更新一个user信息
     *
     * @param user_zhangLianYu
     * @return
     */
    public boolean update(User_ZhangLianYu user_zhangLianYu) {

        return false;
    }

    /**
     * 根据id删除一个user信息
     *
     * @param id
     * @return
     */
    public boolean deleteById(int id) {


        return false;
    }
}
