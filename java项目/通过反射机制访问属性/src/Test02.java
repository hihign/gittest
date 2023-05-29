import java.lang.reflect.Method;

public class Test02 {
    public static void main(String[] args) throws Exception{
        Class c = Class.forName("Student");
        Object obj = c.newInstance();
        Method method = c.getDeclaredMethod("login",String.class,String.class);
        Object object = method.invoke(obj,"admin","123");
        System.out.println(object);
    }
}
