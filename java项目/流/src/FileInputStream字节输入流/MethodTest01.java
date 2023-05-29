package FileInputStream字节输入流;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MethodTest01 {
    public static void main(String[] args) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("file1.txt");

            int readCount = 0;
           /* while((readCount = fis.read()) != -1) {//这个方法返回的是字节本身的ASCII码值
                System.out.println(readCount);
            }*/
           readCount = fis.read();
           int rest = fis.available();//此方法是查看剩余多少字节未读取
            System.out.println(rest);
            fis.skip(3);//此方法是跳过3个字节不读
            int readCount1 = fis.read();//的
            System.out.println(readCount1);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
