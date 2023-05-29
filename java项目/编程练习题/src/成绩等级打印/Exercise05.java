package 成绩等级打印;

import java.util.Scanner;

/*
利用条件运算符的嵌套来完成此题：
学习成绩> =90分的同学用A表示，60-89分之间的用B表示，60分以下的用C表示。
*/
public class Exercise05 {
    public static void main(String[] args) {
        System.out.println("请输入一个分数：");
        Scanner s = new Scanner(System.in);
        int score = s.nextInt();
        char grade;
        grade = score >= 90? 'A':score >= 60? 'B':'C';
        System.out.println("等级为："+grade);
        /*if (score >= 90){
            grade = 'A';
        }else if (0 < score & score < 60){
            grade = 'C';
        }else grade = 'B';
        System.out.println(grade);*/
    }
}
