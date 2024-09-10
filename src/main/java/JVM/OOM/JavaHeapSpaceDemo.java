package JVM.OOM;

import java.util.Random;

public class JavaHeapSpaceDemo  {

    public static void main(String[] args) {
        String str = "atguigu";
        while (true){
            str += str+new Random().nextInt(111111)+new Random().nextInt(222222);
            str.intern();
        }
    }
}
