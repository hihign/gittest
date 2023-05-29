package 同步代码块解决线程安全问题;

public class Account {
    String actno;
    double balance;

    public Account() {
    }

    public Account(String actno, double balance) {
        this.actno = actno;
        this.balance = balance;
    }

    public String getActno() {
        return actno;
    }

    public void setActno(String actno) {
        this.actno = actno;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    public void withdraw(double money){
        synchronized(this) {
            double before = this.getBalance();
            double after = before - money;
            //模拟网络延迟
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setBalance(after);
            System.out.println("账户" + this.getActno() + "，余额" + this.getBalance());
        }
    }
}
