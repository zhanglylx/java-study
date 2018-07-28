package 多线程.多线程并发安全;

/**
 * 多线程并发安全问题
 * <p>
 * synchronized 关键字
 * 线程安全锁
 * synchronized可以修饰方法也可以单独作为语句块存在
 * synchronized的作用是限制多线程并发时同时访问该作用域
 */
public class ThreadDemo {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Bank.Preson p1 = bank.new Preson();
        Bank.Preson p2 = bank.new Preson();
        p1.start();
        p2.start();
    }

}

class Bank {
    int count = 10000;

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
        }
        Thread.yield();
        count -= money;
    }

    class Preson extends Thread {
        public void run() {
            while (true) {
                getMoney(100);
                System.out.println("当前余额:" + count);
                Thread.yield();
            }
        }
    }

}