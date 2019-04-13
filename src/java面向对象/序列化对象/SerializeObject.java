package 序列化对象;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化对象
 * @author Administrator
 *
 */
public class SerializeObject {

	public static void main(String[] args) {
		try{
			DownloadInfo info =
					new DownloadInfo(
							"http://www.baidu.com/download/xxx.zip"
							,
							"xxx.zip"
							);
			info.setPos(12587);
			info.setFileSize(5566987);
			/**
			 * 将info对象序列化后写到文件中
			 */
			File file = new File("obj.tmp");
			FileOutputStream fos = new FileOutputStream(file);
			//通过oos可以将对象序列化后写入obj.tmp文件中
			ObjectOutputStream oos = 
					new ObjectOutputStream(fos);
			//将info序列化后写出
			oos.writeObject(info);
			oos.close();
			
			
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
			ois.close();
			
		}catch (Exception e){
			e.printStackTrace();
			System.out.println("非常sorry");
		}
	}

}
