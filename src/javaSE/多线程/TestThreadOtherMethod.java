package 多线程;

/**
 * 线程的方法
 * yield():放弃当次时加片，主动进入Runnable状态
 * setPriority():设置线程优先级
 *               优先级越高的线程，理论上获取cpu的次数就越多
 *               设置线程   一定要在线程启动前设置
 */
public class TestThreadOtherMethod {
    public static void main(String[] args) {
        Thread t1 = new Thread(){
            @Override
            public void run() {
                super.run();
                for(int i = 0;i<100;i++){
                    System.out.println("我是t1");
                    Thread.yield();
                }
            }
        };
        Thread t2 = new Thread(){
            @Override
            public void run() {
                super.run();
                for(int i = 0;i<100;i++){
                    System.out.println("我是t2");
                    Thread.yield();
                }
            }
        };

        Thread t3 = new Thread(){
            @Override
            public void run() {
                super.run();
                for(int i = 0;i<100;i++){
                    System.out.println("我是t3");
                    Thread.yield();
                }
            }
        };
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.NORM_PRIORITY);
        t3.setPriority(Thread.MAX_PRIORITY);
        t1.start();
        t2.start();
        t3.start();
    }
}
