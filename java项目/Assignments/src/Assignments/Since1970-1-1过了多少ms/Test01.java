public class Test01 {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();
        System.out.println(l);
        //可以算出一个程序执行所需要的时间
        long begin = System.currentTimeMillis();
        print();
        long end = System.currentTimeMillis();
        System.out.println("程序执行了"+(end - begin)+"ms");

    }
    public static void print(){
        System.out.println("HelloWorld");
    }
}
