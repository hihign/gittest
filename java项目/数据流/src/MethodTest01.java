import java.io.DataOutputStream;
import java.io.FileOutputStream;
//通常用于数据加密
public class MethodTest01 {
    public static void main(String[] args) throws Exception{
        DataOutputStream  dos = new DataOutputStream(new FileOutputStream("file3.txt"));
        byte b = 127;
        short s = 20;
        int i = 12;
        long l = 888L;
        double d = 3.14;
        float f = 3.1F;
        boolean b1 = true;
        char c = '你';
        dos.writeByte(b);
        dos.writeShort(s);
        dos.writeInt(i);
        dos.writeLong(l);
        dos.writeFloat(f);
        dos.writeDouble(d);
        dos.writeBoolean(b1);
        dos.writeChar(c);
        dos.flush();
        dos.close();
    }
}
