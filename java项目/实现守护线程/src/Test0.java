//t1.setDaemon() 设置守护进程,让该进程放入后台执行
public class Test0 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyThread());
        t1.setName("t1");
        t1.setDaemon(true);
        t1.start();
        for (int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName()+"-->"+i);
        }
    }
}
class MyThread implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            System.out.println(Thread.currentThread().getName()+"-->"+i);
            try {
                Thread.sleep(1000*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
