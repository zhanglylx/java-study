package 多线程;
/**
 * 使用匿名类方式创建线程
 * @author Administrator
 *
 */
public class TestInnerClass {

	public static void main(String[] args) {
		/**
		 * 匿名类实现继承Thread形式
		 */
		Thread t1 = new Thread(){
			public void run(){
				for(int i =0;i<100000;i++){
					System.out.println(i);
				}
			}
		};
		t1.start();
		/*
		 * 匿名类实现 Runnable接口的形式
		 */
		Thread t2 = new Thread(new Runnable(){
			public void run(){
				for(int i=0;i<100000;i++){
					System.out.println("你好"+i+"次");
				}
			}
		});
		t2.start();
	}

}
