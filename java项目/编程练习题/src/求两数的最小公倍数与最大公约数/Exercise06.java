package 求两数的最小公倍数与最大公约数;

import java.util.Scanner;

/*题目：输入两个正整数m和n，求其最大公约数和最小公倍数。   
/**在循环中，只要除数不等于0，用较大数除以较小的数，
将小的一个数作为下一轮循环的大数，取得的余数作为下一轮循环的较小的数，
如此循环直到较小的数的值为0，返回较大的数，
此数即为最大公约数，最小公倍数为两数之积除以最大公约数。* /*/
public class Exercise06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入一个整数：");
        int a = scanner.nextInt();
        System.out.println("请再次输入一个整数：");
        int b = scanner.nextInt();
        int f1 = a;
        int f2 = b;
        int r;
        while(a%b != 0){
            r = a%b;
            a = b;
            b = r;
        }
        //最大公约数
        System.out.println(b);
        //最小公倍数
        System.out.println(f1*f2/b);
    }
}
