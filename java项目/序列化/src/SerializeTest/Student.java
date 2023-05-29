package SerializeTest;

import java.io.Serializable;

class Student implements Serializable {
    int age;
    transient String name;

    public Student() {
        System.out.println("Student类的无参构造执行了");
    }

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
