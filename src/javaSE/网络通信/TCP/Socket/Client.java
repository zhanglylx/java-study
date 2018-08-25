package 网络通信.TCP.Socket;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 客户端应用程序
 */
public class Client {
    private Socket socket;
    private ExecutorService executorService;

    /**
     * 客户端构造方法，用于初始化客户端
     */
    public Client() throws Exception {
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
            executorService = Executors.newFixedThreadPool(1);
            System.out.println("成功连接服务端");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 客户端启动方法
     */
    public void start() {
        PrintWriter pw = null;
        try {
            //创建并启动线程，来接收服务端消息
            executorService.execute(new GetServerInfoHandlet());
            /**
             * 可以通过Socket的getOutputStream()
             *  方法获取一条输出流，用于将信息发送至服务端
             */
            OutputStream out = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(out, "utf-8");
            /**
             * 将字符流包装为缓冲流字符流，就可以
             * 按行为单位写出字符串
             */
            pw = new PrintWriter(osw, true);
            //输出欢迎语
            System.out.println("欢迎来到张连宇的聊天室");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                /**
                 * 首先输入昵称
                 */
                System.out.println("请输入昵称:");
                String nickName = scanner.nextLine();
                if (nickName.trim().length() > 0) {
                    pw.println(nickName);
                    break;
                }
                System.out.println("昵称不能是空");
            }
            while (true) {
                pw.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(this.socket!=null)this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("客户端连接关闭了");
        }
    }

    /**
     * 该线程的作用是循环接收服务端发送过来的信息，并输出
     * 到控制台
     */
    class GetServerInfoHandlet implements Runnable {
        public GetServerInfoHandlet() {
        }

        @Override
        public void run() {
            try {
                /**
                 * 通过Socket获取输入流
                 */
                InputStream in = socket.getInputStream();
                //将输入流转换为字符输入流，指定编码
                InputStreamReader isr = new InputStreamReader(in, "utf-8");
                //将字符输入流转换为缓冲流
                BufferedReader br = new BufferedReader(isr);
                String message;
                while ((message = br.readLine()) != null) {
                    System.out.println("服务端说:" + message);
                }

            } catch (Exception e) {

            } finally {
                System.out.println("服务端连接关闭了");
            }
        }
    }
}
