package 模拟两个线程对一个账户操作;

import java.util.Scanner;

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
