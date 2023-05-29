package File的常用方法;

import java.io.File;
import java.io.IOException;

public class MethodTest01 {
    public static void main(String[] args) throws IOException {
        File file = new File("F:\\javase02");
        if (file.exists()){
            file.createNewFile();
        }
    }
}
