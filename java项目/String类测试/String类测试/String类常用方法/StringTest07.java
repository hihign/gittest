package com.bjpowernode.javase.array.assignment;
/*
    ①byte[] getBytes() 将字符串对象转换成字节数组
    ②boolean isEmpty() 判断字符串是否为空
    ③int length() 计算字符串中的字符个数
*/
public class StringTest07 {
    public static void main(String[] args) {
//        ①byte[] getBytes()
        String s = "abcde";
        byte[] bytes = s.getBytes();
        for (int i = 0; i < bytes.length; i++){
            System.out.println(bytes[i]);
        }
//        ②boolean isEmpty()
        String s1 = "";
        System.out.println(s1.isEmpty());
//        ③int length()
        String s2 = "hasdkjh";
        System.out.println(s2.length());
    }
}
