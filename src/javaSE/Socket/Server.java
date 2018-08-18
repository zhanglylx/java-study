package Socket;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务端应用程序
 */
public class Server {
    //运行在服务端的Socket
    private ServerSocket server;
    //线程池，用于管理客户端连接的交互线程
    private ExecutorService threadPool;
    //保存所有客户端输出流的集合
    private List<PrintWriter> allOut;
    /**
     * 构造方法，用于初始化服务端
     */
    public Server() throws IOException {
        try {
            System.out.println("初始化服务端");
            /**
             * 创建ServerSocket时需要指定服务端口
             */
            server = new ServerSocket(8088);
            /**
             * 使用线程池分配空闲线程来处理
             * 当前连接的客户端
             */
            this.threadPool = Executors.newFixedThreadPool(50);
            //初始化存放所有客户端输出流的集合
            this.allOut = new ArrayList<>();

            System.out.println("服务端初始化完毕");
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * 服务端开始工作的方法
     */
    public void start() {
        try {
            while (true) {
                System.out.println("等待客户端连接");
                /**
                 * ServerSocket的accept方法
                 * 用于监听8088端口，等待客户端的连接
                 * 该方法是一个阻塞方法，直到一个客户端连接，
                 * 否则该方法一直阻塞。
                 * 若一个客户端连接了，会返回该客户端的Socket
                 */
                Socket socket = server.accept();
                /**
                 * 当一个客户端连接后，启动一个线程
                 * ClientHandler，将该客户端的Socket传入，
                 * 使得该线程处理与该客户端的交互。
                 * 这样，我们能再次进入循环，接收下一个客户端的
                 * 连接了。
                 */
                Runnable handler = new ClientHandler(socket);
                threadPool.execute(handler);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class ClientHandler implements Runnable {
        //当前线程处理的客户端的socket
        private Socket socket;

        /**
         * 根据给定的客户端的Socket，创建
         * 线程体
         */
        public ClientHandler(Socket socket) {
            this.socket = socket;
            /**
             * 通过socket获取远端的地址信息
             * 对于服务端而言，远端就是客户端
             */
            InetAddress address = socket.getInetAddress();
            //获取远端计算机的IP地址
            address.getHostAddress();
            //获取客户端的端口后
            int port = socket.getPort();
            System.out.println("客户端连接了:" + address.getHostAddress() + ":" + port);
        }

        /**
         * 该线程会将当前Socket中的输入流获取用来循环读取客户端发送
         * 过来的消息
         */
        @Override
        public void run() {
            //定义在try语句外的目的是，为了在finally中也可以引用到
            PrintWriter printWriter = null;
            try {
                /*
                 *为了让服务端与客户端发送信息，
                 * 我们需要通过socket获取输出流。
                 */
                OutputStream outputStream = socket.getOutputStream();
                //转换为字符流，用于指定编码集
                OutputStreamWriter outputStreamWriter =
                                            new OutputStreamWriter(outputStream,"utf-8");
                //创建缓冲字符输出流
                printWriter = new PrintWriter(outputStreamWriter,true);
                /*
                    将该客户端的输出流存入共享集合
                    以便使得该客户端也能接收服务端
                    转发的消息
                 */
                allOut.add(printWriter);
                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader =
                        new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String message;
                while ((message = bufferedReader.readLine()) != null) {
                    System.out.println("客户端说:" + message);
                    printWriter.println(message);
                }
            } catch (IOException e) {
                //在windows中的客户端，
                //报错通常是因为客户端断开了连接
                e.printStackTrace();
            } finally {
                /**
                 * 首选将该客户端的输出流从共享集合中删除
                 */
                allOut.remove(printWriter);


                /**
                 * 无论是linux用户还是windows用户，当与服务端断开连接后，
                 * 都应该在服务端与客户端客户端断开连接
                 */
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("一个客户端下线了...");
            }
        }
    }

    public static void main(String[] args) {
        Server server = null;
        try {
            server = new Server();
        } catch (IOException e) {
            e.printStackTrace();
        }
        server.start();
    }
}
