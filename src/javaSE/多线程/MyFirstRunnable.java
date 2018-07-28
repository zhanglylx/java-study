package 多线程;
/**
 * 定义线程体(线程要执行的任务逻辑)
 * @author Administrator
 *
 */
public class MyFirstRunnable implements Runnable {
/**
 * run方法中定义线程要执行的逻辑
 */
	public void run(){
		for(int i=0;i<100000;i++){
			System.out.println(i);
		}
	}
}
