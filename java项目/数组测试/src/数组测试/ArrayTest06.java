package 数组测试;

public class ArrayTest06 {
    public static void main(String[] args) {
        int[] i = {1,11,22,4};
        int[] is = new int[10];
        System.arraycopy(i,0,is,2,3);
        for(int j = 0; j < is.length; j++){
            System.out.println(is[j]);
        }
    }
}
