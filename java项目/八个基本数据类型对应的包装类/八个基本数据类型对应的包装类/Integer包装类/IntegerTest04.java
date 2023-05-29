package com.bjpowernode.javase.array.assignment;

public class IntegerTest04 {
    public static void main(String[] args) {
        Integer m = 128;
        Integer n = 128;
// 如果整数范围在[-128,127]，方法区中的整数常量池中已经创建了该对象，存储了该整数。
        Integer x = 127;
        Integer y = 127;
        System.out.println(m == n);//false
        System.out.println(x == y);//true
    }
}
