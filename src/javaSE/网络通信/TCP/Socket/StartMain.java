package 网络通信.TCP.Socket;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StartMain {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Runnable runnable2=new Runnable() {
            @Override
            public void run() {
                try {
                    Client client = new Client();
                    client.start();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        executorService.execute(runnable2);
        /**
         * 服务端中的一个线程，用于某个客户端交互。
         * 使用线程的目的是使得服务端可以处理多客户端
         */

    }
}
