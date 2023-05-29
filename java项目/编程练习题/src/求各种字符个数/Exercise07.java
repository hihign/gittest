package 求各种字符个数;

import java.util.Scanner;

//【程序7】   
//题目：输入一行字符，分别统计出其中英文字母、空格、数字和其它字符的个数。
public class Exercise07 {
    public static void main(String[] args) {
        int charcter = 0;
        int blank = 0;
        int digital = 0;
        int other = 0;
        System.out.println("请输入要查询的字符：");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i++){
            if (ch[i] >= 'a'&ch[i] <='z' || ch[i] >= 'A'&ch[i] <='Z'){
                charcter++;
            }else if (ch[i] == ' '){
                blank++;
            }else if (ch[i] >= '0' & ch[i] <= '9'){
                digital++;
            }else other++;
        }
        System.out.println("英文字母的数量是：" + charcter);
        System.out.println("空白的数量是：" + blank);
        System.out.println("数字的数量是：" + digital);
        System.out.println("其他字符的数量是：" + other);
    }
}
