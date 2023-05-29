package 对象锁;

//线程并发，对同一个账户并发，账户余额出现了错误。
public class ThreadTest {
    public static void main(String[] args) {
        Account account = new Account("actno101",10000);
        AccountThread t1 = new AccountThread(account);
        AccountThread t2 = new AccountThread(account);
        t1.setName("t1");
        t2.setName("t2");
        t1.start();
        t2.start();
    }
}
