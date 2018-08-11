package Socket;
Unit05： 多线程基础 、 TCP通信 02     1:31:18
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 客户端应用程序
 */
public class Client {
    private Socket socket;

    /**
     * 客户端构造方法，用于初始化客户端
     */
    public Client() throws Exception{
        try {
            System.out.println("正在连接服务端");
            /**
             * 创建Socket对象时，就会尝试根据
             * 给定的地址与端口连接服务端。
             * 所以，若该对象创建成果，说明
             * 与服务端连接正确。
             */
            socket =
                    new Socket("localhost", 8088);
            System.out.println("成功连接服务端");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 客户端启动方法
     */
    public void start(){

    }

    public static void main(String[] args) {
        try {
            Client client = new Client();
            client.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("客户端初始化失败");
        }
    }
}
