public class ThreadDemo {
    public static int i = 0;

    public static void main(String[] args) throws Exception {
        RRR r = new ThreadDemo.RRR();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 1000; index++) {
                    r.setI(1);
                }
                System.out.println("我1执行完了");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int index = 0; index < 1000; index++) {
                    r.setI(1);
                }
                System.out.println("我2执行完了");
            }
        });
        thread.start();
        thread2.start();
        Thread.sleep(2000);
        System.out.println(r.getI());
    }

    static class RRR {
        public int getI() {
            return i;
        }

        public synchronized void setI(int i) {
            this.i += i;
        }

        private int i;

        RRR() {
            this.i = 0;
        }
    }
}
