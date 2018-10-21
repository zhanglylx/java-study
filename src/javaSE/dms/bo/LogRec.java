package dms.bo;

/**
 * 该类用于描述一组匹配成对的日志
 */
public class LogRec {
    private LogData login;
    private LogData logout;

    public LogRec(LogData login, LogData logout) {
        this.login = login;
        this.logout = logout;
    }

    public LogData getLogin() {
        return login;
    }

    public void setLogin(LogData login) {
        this.login = login;
    }

    public LogData getLogout() {
        return logout;
    }

    public void setLogout(LogData logout) {
        this.logout = logout;
    }

    @Override
    public String toString() {
        return this.login + "|" + this.logout;
    }
}
