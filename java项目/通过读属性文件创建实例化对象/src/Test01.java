import java.io.FileReader;
import java.util.Properties;

public class Test01 {
    public static void main(String[] args) throws Exception{
        FileReader reader = new FileReader("通过读属性文件创建实例化对象/src/classinfo.properties");
        Properties pro = new Properties();
        pro.load(reader);
        reader.close();
        String className = pro.getProperty("className");
        System.out.println(className);
        Class c = Class.forName(className);
        c.newInstance();
    }
}
