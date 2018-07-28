package 多线程;

public class TestRunnable {
	public static void main(String[] args) {
		/**
		 * 创建两个需要并发的任务
		 */
		Runnable r1 = new MyFirstRunnable();
		Runnable r2 = new MySecRunnable();
		/**
		 * 将两个任务分别交给线程去并发处理
		 * 将任务交给线程可以使用线程的重载构造方法
		 * Thread(Runnable runnable)
		 */
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		
		t1.start();
		t2.start();
		
		
		
	}
}
