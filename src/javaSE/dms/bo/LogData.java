package dms.bo;


import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

/**
 * LogData的每一个实例用于表示wtmpx文件中一条
 * 日志信息
 */
public class LogData {
    //测试用
    public static final int LOG_TEST =0;
    /**
     * 日志在wtmpx文件中的长度
     * 每一条日志的长度都是372字节
     */
    public static final int LOG_LENGTH = 372;

    /**
     * user在单条日志的起始字节
     */
    public static final int USER_OFFSET = 0;

    /**
     * user在日志中占用的字节量
     */
    public static final int USER_LENGTH = 32;

    /**
     * PID在日志中的起始位置
     */
    public static final int PID_OFFSET = 68;

    /**
     * PID在日志中的占用字节量
     */
    public static final int PID_LENGTH = 71;

    /**
     * TYPE在日志中的起始位置
     */
    public static final int TYPE_OFFSET = 72;

    /**
     * TYPE在日志中的长度
     */
    public static final int TYPE_LENGTH = 79;

    /**
     * TIME在日志中的起始位置
     */
    public static final int TIME_OFFSET = 80;

    /**
     * TIME在日志文件中的长度
     */
    public static final int TIME_LENGTH = 113;


    /**
     * HOST在日志中的起始位置
     */
    public static final int HOST_OFFSET = 114;

    /**
     * HOST在日志文件中的长度
     */
    public static final int HOST_LENGTH = 258;

    /**
     * 日志类型:登入操作
     */
    public static final short TYPE_LOGIN = 7;

    /**
     * 日志类型:登出操作
     */
    public static final short TYPE_LOGOUT = 8;

    //登录用户的用户名
    private String user;

    //进行ID
    private int pid;

    //日志类型(登入或登出)
    private short type;

    //日志生成的时间(登入和登出的时间)
    private int time;

    //登录用户的IP地址
    private String host;

    public LogData(String user, int pid, short type, int time, String host) {
        this.user = user;
        this.pid = pid;
        this.type = type;
        this.time = time;
        this.host = host;
    }

    /**
     * 给定一个字符串
     * (格式应当是当前类toString方法生成)
     * 将该字符串转换为一个LogData对象
     *
     * @param lineJson
     */
    public LogData(String lineJson) {
        //解析json
        JSONObject jsonObject =
                JSONObject.fromObject(lineJson);
        this.user = jsonObject.getString("user");
        this.pid = jsonObject.getInt("pid");
        this.time = jsonObject.getInt("time");
        this.type = Short.parseShort(
                jsonObject.getString("type"));
        this.host = jsonObject.getString("host");
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        JSONObject jsonObject =
                new JSONObject();
        jsonObject.put("user", user);
        jsonObject.put("pid", pid);
        jsonObject.put("type", type);
        jsonObject.put("time", time);
        jsonObject.put("host", host);
        return jsonObject.toString();
    }


    public static void main(String[] args) {
        System.out.println(
                new LogData(
                        "q", 1, (short) 1, 1, "2")
        );
    }
}
