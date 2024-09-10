package JVM.OOM;

import java.util.ArrayList;
import java.util.List;


/**
 * jvm参数配置：
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * 98%的时间用来做GC并且回收了不到2%的内存
 *
 */
public class GCOverheadDemo {
    public static void main(String[] args) {
        int i  = 0;
        List<String> lsit = new ArrayList<>();

        try {
            while (true) {
                lsit.add(String.valueOf(++i).intern());
            }
        }catch (Throwable e){
            System.out.println("i = :"+i);
            e.printStackTrace();
            throw e;
        }
    }
}
