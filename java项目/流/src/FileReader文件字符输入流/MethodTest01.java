package FileReader文件字符输入流;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MethodTest01 {
    public static void main(String[] args) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("file1.txt");
            char[]  chars = new char[2];
            int readCount = 0;
            while((readCount = fileReader.read(chars)) != -1) {
                System.out.print(new String(chars, 0, readCount));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileReader != null){
                try {
                    fileReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
