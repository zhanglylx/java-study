package day01;


import java.io.InputStream;
        import java.sql.Connection;
        import java.sql.DriverManager;
        import java.util.Properties;

/**
 * 该类用来管理数据库的连接
 */
public class 使用配置文件链接JDBCUtils {
    private static String url;
    private static String user;
    private static String pwd;
    //用于管理不同线程所获取的连接
    private static ThreadLocal<Connection> threadLocal
            = new ThreadLocal<>();

    //静态块
    static {
        try {
            //读取配置文件
            Properties properties
                    = new Properties();
            /**
             * 这种写法是将来更加推荐的相对路径写法。
             */
            InputStream inputStream
                    = 使用配置文件链接JDBCUtils.class.getClassLoader().
                    getResourceAsStream("day01/config.properties");
            properties.load(inputStream);
            inputStream.close();
            //获取驱动
            String dirver = properties.getProperty("driver");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            pwd = properties.getProperty("pwd");
            //注册驱动
            Class.forName(dirver);
            Connection connection = DriverManager.getConnection(
                    url, user, pwd
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取一个连接
     *
     * @return
     */
    public static Connection getConnection() throws Exception {
        try {
            /**
             * 通过DriverManager创建一个数据库连接
             * 并返回
             */
            Connection connection =
                    DriverManager.getConnection(url, user, pwd);
            /**
             * ThreadLocal的set方法
             * 会将当前线程作为key,并将给定的
             * 值作为value存入内容的map中保存。
             */
            threadLocal.set(connection);
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            //通知调用者，创建连接出错
            throw e;
        }
    }

    /**
     * 关闭给定的连接
     */
    public static void closeConnection() {
        try {
            Connection connection = threadLocal.get();
            if (connection != null) {
                connection.close();
                threadLocal.remove();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
