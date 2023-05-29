package Assignments.数字格式化;

import java.text.DecimalFormat;

public class DecimalTest {
    public static void main(String[] args) {
        DecimalFormat d = new DecimalFormat("###,###.0000");
        String s = d.format(18972);
        System.out.println(s);
    }
}
