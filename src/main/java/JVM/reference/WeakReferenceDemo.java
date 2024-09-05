package JVM.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object obj1 = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(obj1);

        System.out.println(obj1);
        System.out.println(weakReference.get());

        obj1 = null;
        System.gc();

        System.out.println(" --- 垃圾回收 ---");
        System.out.println("obj1: "+obj1);
        System.out.println("weakReference: "+weakReference.get());


    }
}
