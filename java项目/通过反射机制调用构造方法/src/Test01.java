import java.lang.reflect.Constructor;

public class Test01 {
    public static void main(String[] args) throws Exception{
        Class c = Class.forName("Student");
        //调用无参构造
        //第一种方式
        Object obj = c.newInstance();
        //第二种方式
        Constructor constructor3 = c.getConstructor();
        constructor3.newInstance();


        //调用有参构造
        //一个参数
        Constructor constructor = c.getConstructor(int.class);
        constructor.newInstance(10);
        //两个参数
        Constructor constructor1 = c.getConstructor(int.class,String.class);
        constructor1.newInstance(15,"lisi");
        //三个参数
        Constructor constructor2 = c.getConstructor(int.class,String.class,boolean.class);
        constructor2.newInstance(20,"wangwu",true);
    }
}
