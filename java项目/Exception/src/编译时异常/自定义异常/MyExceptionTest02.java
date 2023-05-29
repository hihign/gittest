package 编译时异常.自定义异常;

public class MyExceptionTest02 {
    public static void main(String[] args) {
        MyException m2 = new MyException("自定义的异常");
        m2.getMessage();//获取简单异常信息
        m2.printStackTrace();//打印异常追踪信息
    }
}
