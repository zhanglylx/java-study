package dms;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 服务端应用程序
 */
public class Server {
    //运行在服务端的Socket
    private ServerSocket server;
    //线程池，用于管理客户端连接的交互线程
    private ExecutorService threadPool;
    //保存所有客户端发送过来的配对日志的文件
    private File serverLogFile;
    //创建一个双缓冲队列，用于存取配对日志
    private BlockingDeque<String> messageQueue;

    /**
     * 构造方法，用于初始化服务端
     */
    public Server() throws IOException {
        try {
            System.out.println("初始化服务端");
            /**
             * 创建ServerSocket时需要指定服务端口
             */
            server = new ServerSocket(15000);
            /**
             * 使用线程池分配空闲线程来处理
             * 当前连接的客户端
             */
            this.threadPool = Executors.newFixedThreadPool(50);

            //初始化保存日志的文件
            this.serverLogFile =
                    new File("server-log.txt");
            //初始化缓冲队列
            this.messageQueue =
                    new LinkedBlockingDeque<>();


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
            /**
             * 将写日志文件的线程启动
             */
            WriteLogThread thread
                    = new WriteLogThread();
            thread.start();


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
                        new OutputStreamWriter(outputStream, "utf-8");
                //创建缓冲字符输出流
                printWriter = new PrintWriter(outputStreamWriter, true);
                /*
                    将该客户端的输出流存入共享集合
                    以便使得该客户端也能接收服务端
                    转发的消息
                 */
//                allOut.add(printWriter);  线程不安全

                InputStream inputStream = socket.getInputStream();
                InputStreamReader inputStreamReader =
                        new InputStreamReader(inputStream, "utf-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);


                String message;
                /**
                 * 循环读取客户端发送过来的每一组
                 * 配对日志
                 * 读取到一组,就将该日志存入消息
                 * 队列，等待被写入文件。
                 */
                while ((message = bufferedReader.readLine()) != null) {
                    System.out.println(message);
                    /**
                     * 若读取到客户端发送的内容是"over"
                     * 表示客户端发送完毕所有日志
                     * 应当停止在接收客户端发送的
                     * 内容
                     */
                    if ("over".equals(message)) break;
                    messageQueue.offer(message);
                }
                /**
                 * 当退出循环，说明所有客户端发送的日志
                 * 均接收成功，并存入了消息队列中
                 * 那么我们回复客户端"ok"
                 */
                printWriter.println("OK");
            } catch (IOException e) {
                //在windows中的客户端，
                //报错通常是因为客户端断开了连接
                if (printWriter != null)
                    printWriter.println("ERROR");
                e.printStackTrace();
            } finally {
                /**
                 * 无论是linux用户还是windows用户，当与服务端断开连接后，
                 * 都应该在服务端与客户端客户端断开连接
                 */
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }
    }

    /**
     * 该线程在Server中仅有一个实例
     * 作用是:循环从消息队列中取出一个
     * 配对日志并写入server-log.txt文件
     * 中，当队列中没有日志后，就休眠一段
     * 时间等待客户端发送新的日志
     */
    class WriteLogThread extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                PrintWriter pw =
                        new PrintWriter(serverLogFile);
                String log;
                while (true) {
                    if(messageQueue.size()>0){
                        log = messageQueue.poll();
                        pw.println(log);
                    }else{
                        pw.flush();
                        Thread.sleep(5000);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
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
