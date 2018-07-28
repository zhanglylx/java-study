package 多线程;

public class TestThread {

	public static void main(String[] args) {
		/**
		 * 测试并发操作多个任务
		 */
		Thread t1 = new MyFirstThread();
		Thread t2 = new MySecThread();
		/**
		 * 启动线程开始并发执行任务
		 * 注意！想并发操作不要直接调用run方法！而是调用线程的
		 * start()方法启动线程。
		 */
		t1.start();
		t2.start();
		
		//t1.stop();
		/**
		 * 不要使用stop()方法来停止线程的运行。这里不安全的操作
		 * 想让线程停止，应该通过run方法的执行完毕来进行自然结束
		 */
	}

}
