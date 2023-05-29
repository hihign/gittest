package com.bjpowernode.javase.array.assignment;
//数据类型转换之 装箱与拆箱
public class IntegerTest01 {
    public static void main(String[] args) {
//      将基本数据类型-->引用数据类型 ：装箱
        Integer i1 = new Integer(123);
//        将引用数据类型-->基本数据类型 ：拆箱
        float f = i1.floatValue();
        long l = i1.longValue();
        System.out.println(f);
        System.out.println(l);
    }
}
