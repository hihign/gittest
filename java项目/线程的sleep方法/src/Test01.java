//代码出现在哪个线程中，该线程就会进入休眠状态
//让当前线程进入"阻塞状态"，释放CPU时间片
//静态方法: static void sleep(long millis)毫秒
public class Test01 {
    public static void main(String[] args) {
        try {
            Thread.sleep(1000*2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hello world!");
    }
}
