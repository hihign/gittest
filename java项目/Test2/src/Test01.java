import java.lang.reflect.Field;
public class Test01{
    public static void main(String[] args) throws Exception{
        Class c = Class.forName("Student");
        Object obj = c.newInstance();
        Field field = c.getDeclaredField("name");
        field.setAccessible(true);
        field.set(obj,"牛马");
        System.out.println(field.get(obj));
    }
}
