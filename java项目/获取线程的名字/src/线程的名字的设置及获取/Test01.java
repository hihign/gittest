package 线程的名字的设置及获取;

public class Test01 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadImplements());
        String name1 = t1.getName();
        System.out.println(name1);
        t1.setName("线程1");
        System.out.println(t1.getName());
    }
}
class ThreadImplements implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++){
            System.out.println("分支线程-->"+i);
        }
    }
}
