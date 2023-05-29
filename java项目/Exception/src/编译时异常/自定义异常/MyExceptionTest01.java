package 编译时异常.自定义异常;

public class MyExceptionTest01 {
    public static void main(String[] args) {
        MyException myException = new MyException("自定义异常");
        System.out.println(myException);
    }
}
