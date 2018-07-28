package 多线程;
/**
 * 线程
 *
 *实现线程需要两步
 *1:继承自Thread
 *2:重写run方法
 *	run方法中应该定义我们需要并发执行的任务逻辑代码
 *
 */
public class MyFirstThread extends Thread{
	public void run(){
		for(int i=0;i<100000;i++){
			System.out.println(i);
		}
	}
}
