package day03.DAO;

import java.util.List;

public class MyUserService {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        User_ZhangLianYu user_zhangLianYu = userDAO.findById(1);
        System.out.println("欢迎你："+user_zhangLianYu.getName());
        List<User_ZhangLianYu> lianYus = userDAO.findAll();
        for(User_ZhangLianYu user_zhangLianYu1:lianYus){
            System.out.println(user_zhangLianYu);
        }
        User_ZhangLianYu user= new User_ZhangLianYu();
        user.setName("marry");
        user.setPassword("123456");
        user.setMoney(123);
        user.setEmail("@123");
        user.setDeptno(2);
        System.out.println("id:"+user.getId());
        if(userDAO.save(user)){
            System.out.println("注册成功");
            System.out.println("id:"+user.getId());
        }
    }

}
