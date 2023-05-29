import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializeTest01 {
    public static void main(String[] args) throws  Exception{
        FileOutputStream fos = new FileOutputStream("studentt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(new Student(23,"lisi"));
    }
}
class Student implements Serializable {
    int age;
    String name;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}