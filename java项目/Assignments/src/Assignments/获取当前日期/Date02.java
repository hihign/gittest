package Assignments.获取当前日期;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Date02 {
    public static void main(String[] args) {
//       表示从1970-01-01 00:08:00 000起 到经过指定时间后 的时间
        Date date = new Date(1);//1970-01-01 00:08:00 001
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        String date1 = sdf.format(date);
        System.out.println(date1);
        Date dates = new Date(System.currentTimeMillis());
        SimpleDateFormat sdf1 = new SimpleDateFormat();
        String date2 = sdf1.format(dates);
        System.out.println(date2);
    }

}
