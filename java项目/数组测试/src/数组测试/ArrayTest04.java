package 数组测试;
//一维数组的扩容(把一个容量小的数组中的元素拷贝到一个容量较大的数组中去)
public class ArrayTest04 {
    public static void main(String[] args) {
        int[] a = {1,2,3,4};
        int[] b = new int[6];
        System.arraycopy(a,0,b,0,4);
        for (int i = 0; i < b.length; i++){
            System.out.println(b[i]);
        }
        String[] str ={"hello","haha","jiuzhe","nihao"};
        String[] newStr = new String[8];
        System.arraycopy(str,0,newStr,0,4);
        for (int i = 0; i < newStr.length; i++){
            System.out.println(newStr[i]);
        }
    }
}
