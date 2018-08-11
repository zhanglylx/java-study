package 多线程.多线程并发安全;

public class wait_notify {
    public static void main(String[] args) throws InterruptedException {
        Bank1 bank = new Bank1();
        Bank1.Preson p1 = bank.new Preson();
        Bank1.Preson p2 = bank.new Preson();
        p1.start();
        p2.start();
        /**
         * p1和p2都在bank对象上等待了，进入了阻塞
         */
        Thread.sleep(5000);
        bank.count = 10000;
        synchronized(bank){
            bank.notifyAll();//通知当前对象等待的线程开始运行
        }

    }

}

class Bank1 {
    int count;
    /**
     * synchronized修饰方法后，方法就不是异步的了，而是同步的了
     * synchronized会为方法上锁
     *
     * @param money
     */
    synchronized void getMoney(int money) {
        //线程锁，块
        /**
         * synchronized同步块
         * synchronized(Object){
         *     需要同步的代码片段
         * }
         * 这里必须注意！Object要确保所有线程看到的是同一个对象！
         * 否则起不到同步的效果
         */
        synchronized (this) {
            if (count == 0) {
                throw new RuntimeException("余额为0");
            }
            Thread.yield();
            count -= money;
        }

    }

    class Preson extends Thread {
        public void run() {
            try {
                synchronized (Bank1.this){
                    Bank1.this.wait();//当前线程(Preson)在银行对象上等待
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            while (true) {
                getMoney(100);
                System.out.println("当前余额:" + count);
                Thread.yield();
            }
        }
    }

}