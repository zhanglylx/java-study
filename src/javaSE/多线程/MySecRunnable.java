package 多线程;

public class MySecRunnable implements Runnable{
	public void run(){
		for(int i=1;i<=100000;i++){
			System.out.println("你好"+i+"次");
		}
	}
}
