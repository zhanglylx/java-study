package 网络通信.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 客户端
 */
public class Client {
    /**
     * 客户端的启动方法
     */
    public void start() {
        try {
            /**
             * 向服务端发送数据的步骤:
             * 1:创建好Socket(一次就可以)
             * 2:准备数据
             * 3:创建数据包
             * 4:将数据存入包中 (2,3是一不完成的)
             * 5:将数据包通过socket发送给服务端
             */
            DatagramSocket socket = new DatagramSocket();
            String str = "你好！服务端";
            byte[] data = str.getBytes("utf-8");
            //打包:准备包裹，填写地址，装入数据
            InetAddress address = InetAddress.getByName("localhost");
            int port = 8088;
            //创建发送包
            DatagramPacket sendPacket = new DatagramPacket(
                    data,data.length,address,port);
            //将包裹发送出去
            socket.send(sendPacket);

            /**
             * 接收服务端说
             */
            data = new byte[100];
            DatagramPacket recvPacket = new DatagramPacket(
                    data, data.length
            );
            //接收数据到包中
            //注意，该方法是个阻塞方法
            socket.receive(recvPacket);
            //拆包拿数据
            byte [] d = recvPacket.getData();
            int dlen = recvPacket.getLength();
            /**
             * String(
             *  byte[] b,
             *  int offset,
             *  int len,
             *  String charsetName
             *  将给定的字符数组中，从offset处开始到len个字节，在根据给定的
             *  字符集转换为字符串
             * )
             */
            String  info = new String(d,0,dlen,"utf-8");
            System.out.println("服务端说:"+info);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Client client = new Client();
        client.start();
    }
}
