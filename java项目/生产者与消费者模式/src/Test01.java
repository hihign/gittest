import java.util.ArrayList;
import java.util.List;

public class Test01 {
    public static void main(String[] args) {
        List list = new ArrayList();
        Thread t1 = new Thread(new Producer(list));
        Thread t2 = new Thread(new Consumer(list));
        t1.start();
        t2.start();
    }
}
class Producer implements Runnable{
    List list;

    public Producer(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        while(true){
            synchronized(list){
                if (list.size() > 0){
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Object obj = new Object();
                list.add(obj);
                System.out.println(Thread.currentThread().getName()+"-->"+obj);
                list.notify();
            }

        }
    }
}
class Consumer implements Runnable{
    List list;

    public Consumer(List list) {
        this.list = list;
    }

    @Override
    public void run() {
        while(true){
            synchronized(list) {
                if (list.size() == 0) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Object obj = list.remove(0);
                System.out.println(Thread.currentThread().getName() + "-->" + obj);
                list.notify();
            }
        }
    }
}