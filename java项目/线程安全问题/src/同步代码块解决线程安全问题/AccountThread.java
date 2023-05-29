package 同步代码块解决线程安全问题;

public class AccountThread extends Thread{
    private Account act;

    public AccountThread(Account act) {
        this.act = act;
    }
    public void run(){
        double money = 5000;
        act.withdraw(money);
    }
}
