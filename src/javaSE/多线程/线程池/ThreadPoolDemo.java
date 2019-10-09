package 多线程.线程池;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池
 * 用于重用线程以及控制线程数量
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        for (int i = 0; i < 5; i++) {
            Runnable runn = new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        System.out.println(i);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            };
            threadPoolExecutor.execute(runn);
        }
        threadPoolExecutor.shutdown();

        System.out.println();
        int queueSize = threadPoolExecutor.getQueue().size();
        System.out.println("当前排队线程数：" + queueSize);

        int activeCount = threadPoolExecutor.getActiveCount();
        System.out.println("当前活动线程数：" + activeCount);

        long completedTaskCount = threadPoolExecutor.getCompletedTaskCount();
        System.out.println("执行完成线程数：" + completedTaskCount);

        long taskCount = threadPoolExecutor.getTaskCount();
        System.out.println("总线程数：" + taskCount);

    }
}
