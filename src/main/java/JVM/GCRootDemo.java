package JVM;

/**
 * 在java中，可作为GC Roots的对象有：
 *
 * 1. 虚拟机栈中引用的对象
 * 2. 方法区中常量引用的对象
 * 3. 方法区中类的静态属性引用的对象
 * 4.本地方法栈中引用的对象
 */
public class GCRootDemo {
    private byte[] byteArray = new byte[100*1024*1024];

    public static void m1(){
        GCRootDemo demo = new GCRootDemo();
        System.gc();
        System.out.println("第一次 GC 完成");
    }

    public static void main(String[] args) {
        m1();
    }
}
