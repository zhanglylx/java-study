import java.io.*;

public class Demo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        DownloadInfo info =
//                new DownloadInfo(
//                        "http://www.baidu.com/download/demo.zip"
//                ,"demo.zip");
//        info.setPos(300);
//        info.setFileSize(40000);
//        /**
//         * 将info对象序列化后写到文件中
//         */
        File file = new File("download.info");
//        FileOutputStream fileOutputStream = new FileOutputStream(file);
//        ObjectOutputStream objectOutputStream =
//                new ObjectOutputStream(fileOutputStream);
//        objectOutputStream.writeObject(info);
//        objectOutputStream.close();


        /**
         * 反序列化操作
         */
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        //反序列化

        DownloadInfo obj = (DownloadInfo)ois.readObject();
        System.out.println(obj.getFileName());
        System.out.println(obj.getUrl());
        System.out.println(obj.getFileSize());
        System.out.println(obj.getPos());
        System.out.println(obj.getClass());

    }
}
