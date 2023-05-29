package 同步代码块嵌套产生死锁;
//假设线程t1先执行（占有对象o1锁），然后线程t2执行，线程t1在等线程t2的对象锁o2释放
//线程t2在等线程t1的对象锁o1释放，一直处于这种僵持状态。
public class Test01 {
    public static void main(String[] args) {
        Object o1 = new Object();
        Object o2 = new Object();
        Thread t1 = new MyThread1(o1,o2);
        Thread t2 = new MyThread2(o1,o2);
        t1.start();
        t2.start();
    }
}
class MyThread1 extends Thread{
    Object o1;
    Object o2;
    public MyThread1(Object o1, Object o2){
        this.o1 = o1;
        this.o2 = o2;
    }
    public void run(){
        synchronized(o1){
            try{
                Thread.sleep(1000*10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            synchronized(o2){

            }
        }

    }
}
class MyThread2 extends Thread{
    Object o1;
    Object o2;
    public MyThread2(Object o1, Object o2){
        this.o1 = o1;
        this.o2 = o2;
    }
    public void run(){
        synchronized(o2){
            try{
                Thread.sleep(1000*10);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            synchronized(o1){
            }
        }
    }
}
