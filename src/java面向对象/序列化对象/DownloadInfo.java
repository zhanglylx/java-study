package 序列化对象;

import java.io.Serializable;

/**
 * 
 * 保存一个下载任务所需要的信息
 * 
 * VO : Value Object 值对象 作用:保存一组数据。
 * 
 * 若这组数据表示的是数据库中的一条数据的话 那么这个对象就叫做:Entity 实体
 * 
 * @author Administrator
 *
 */
public class DownloadInfo  implements Serializable {
	/**
	 * 版本号。用于匹配当前类与其被反序列化的对象是否处于同样的特征(属性列表一致等)。
	 * 反序列化时，ObjectInputStream会根据被反序列化对象的版本
	 * 与当前类版本进行匹配来决定是否反序列化
	 * 不加版本号可以，但是可能存在反序列化失败的风险。
	 * 
	 * JDK提供的大多数java bean都实现了该接口
	 */
	private static final long serialVersionUID = 1L;
	private String url;// 下载文件的地址
	/*
	 * transient 关键字用于表示属性在对象序列化时被忽略
	 */
	private transient long  pos; // 已经下载的字节数
	private long fileSize; // 文件总大小
	private String fileName; // 保存在本地的文件名

	public DownloadInfo(String url, String fileName) {
		this.url = url;
		this.fileName = fileName;
	}

	public long getPos() {
		return pos;
	}

	public void setPos(long pos) {
		this.pos = pos;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
