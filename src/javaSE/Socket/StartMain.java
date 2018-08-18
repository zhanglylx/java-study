package Socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
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
