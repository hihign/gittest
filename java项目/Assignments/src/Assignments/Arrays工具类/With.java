package Assignments.Arrays工具类;

import java.util.Arrays;
public class With {
    public static void main(String[] args) {
        int[] a = {12,52,14,98,24,23};
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
        System.out.println("=============================");
        int index = Arrays.binarySearch(a,24);
        System.out.println(index);
    }

}
