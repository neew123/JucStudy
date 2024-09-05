package JVM.reference;

import java.lang.ref.SoftReference;

public class SoftReferenceDemo {

    public static void softRef_memory_enough(){
        Object obj1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(softReference.get());
        System.out.println("----内存够用时软引用不会被回收----");
        obj1 = null;
        System.gc();

        System.out.println("obj1: "+obj1);
        System.out.println("softReference: "+softReference.get());
    }

    /**
     * JVM配置，故意产生大对象并配置小内存，观察软引用的回收情况
     * -Xms5m -Xmx5m -XX:+PrintGCDetails
     */
    public static void softRef_memory_notEnough(){
        Object obj1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(obj1);
        System.out.println(obj1);
        System.out.println(softReference.get());
        System.out.println("----内存不够用时软引用会被回收----");
        obj1 = null;
        try {
                byte[] bytes = new byte[30 * 1024 * 1024];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("obj1: "+obj1);
            System.out.println("softReference: "+softReference.get());
        }
    }

    public static void main(String[] args) {
        System.out.println("---内存够用情况---");
        softRef_memory_enough();
        System.out.println();
        System.out.println("---内存不够用情况---");
        softRef_memory_notEnough();
    }
}
