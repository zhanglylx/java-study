package 多线程.后台线程;
/**
 * 后台线程也称为守护线程
 * 特点:
 *      当当前进行中所有前台线程死亡后，后台线程强制死亡。无论
 *      是否还在运行。
 */
public class DaemonThread {
    public static void main(String[] args) {
        Thread rose = new Thread(){
          public void run(){
              for(int i=0;i<10;i++){
                  System.out.println("let me go!");
                  try {
                      Thread.sleep(500);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
              System.out.println("AAAAaaaaaaaaa...噗通");
          }
        };
        Thread jack = new Thread(){
            public void run(){
                for(int i=0;i<1000;i++){
                    System.out.println("you jump!i jump!");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        jack.setDaemon(true); //守护线程

        rose.start();
        jack.start();
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
