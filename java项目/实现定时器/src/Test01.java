import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

//实现定时器
public class Test01 {
    public static void main(String[] args) throws Exception{
        //创建一个定时器对象
        Timer timer = new Timer();
        LogTimerTask logTimerTask = new LogTimerTask();
        //timer.schedule(TimerTask执行的任务,Date第一次执行的时间，long间隔多少时间执行一次ms);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date carryTime = sdf.parse("2022-10-19 18:37:30");
        timer.schedule(logTimerTask,carryTime,2000);
    }
}
class LogTimerTask extends TimerTask {
    @Override
    public void run() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowtime = sdf.format(date);
        System.out.println(nowtime+"系统更新了BUG");
    }
}