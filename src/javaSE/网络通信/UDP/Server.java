package 网络通信.UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 服务端
 */
public class Server {
    /**
     * 服务端启动方法
     */
    public void start() {
        try {
            /**
             * 接收包的步骤:
             * 1:创建Socket(一次)
             * 2:创建一个合适大小的包
             * 3:通过socket接收数据到包中
             * 4:拆包取数据
             */
            DatagramSocket socket = new DatagramSocket(8088);
            byte[] data = new byte[100];
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
            System.out.println("客户端说:"+info);

            /**
             * 回复客户端
             */
            String str = "你好！客户端";
            data = str.getBytes("utf-8");
            //打包:准备包裹，填写地址，装入数据
            InetAddress address = InetAddress.getByName("localhost");
            int port = 8088;
            //创建发送包
            DatagramPacket sendPacket = new DatagramPacket(
                    data,data.length,address,port);
            //将包裹发送出去
            socket.send(sendPacket);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.start();
    }
}
