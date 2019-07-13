package day03.DAO;

/**
 * 实体类
 * 用于表示数据库中的user表
 */
public class User_ZhangLianYu {
    private int id;
    private String name;
    private String password;
    private int money;
    private String email;
    private int deptno;
    public User_ZhangLianYu(){

    }
    public User_ZhangLianYu(int id, String name, String password, int sal, String email, int deptno) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.money = sal;
        this.email = email;
        this.deptno = deptno;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    @Override
    public String toString() {
        return "User_ZhangLianYu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", money=" + money +
                ", email='" + email + '\'' +
                ", deptno=" + deptno +
                '}';
    }
}
