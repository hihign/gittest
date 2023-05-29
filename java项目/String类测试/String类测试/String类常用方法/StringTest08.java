package com.bjpowernode.javase.array.assignment;
/*
①int indexOf(String str,int fromIndex) 返回指定子字符串第一次出现的索引，从指定下标搜索
        int indexOf(String str) 返回指定子字符串第一次出现的索引，全局搜索
② int lastIndexOf(String str, int fromIndex) 返回子字符串最后一次出现的索引,从指定下标反向搜索
        int lastIndexOf(String str) 返回子字符串最后一次出现的索引，全局搜索
③ String replace(CharSequence target, CharSequence replacement)
*/
public class StringTest08 {
    public static void main(String[] args) {
//         ①int indexof(String str,int fromIndex)
//              int indexof(String str)
        String s = "abcdefgcde";
        String s1 = "cde";
        System.out.println(s.indexOf(s1));
        System.out.println(s.indexOf(s1,2));
//        ②int lastIndexOf(String str)
        System.out.println(s.lastIndexOf(s1));
        System.out.println(s.lastIndexOf(s1,5));
//        ③ String replace(CharSequence target, CharSequence replacement)
        String s2 = "http://www.baidu.com";
        String s3 = "http";
        String s4 = "https";
        String s5 = s2.replace(s3,s4);
        System.out.println(s5);
    }
}
