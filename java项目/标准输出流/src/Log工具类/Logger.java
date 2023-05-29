package Log工具类;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    public static void log(String msg){
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream("log1.txt",true));
            System.setOut(ps);
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SSS");
            String s1 = sdf.format(date);
            System.out.println(s1 +":" +msg);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
