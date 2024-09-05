package JVM.reference;

public class StrongReferenceDemo {
    public static void main(String[] args) {
        Object obj1 = new Object();//强引用
        Object obj2 = obj1;//引用赋值
        obj1 = null;
        System.gc();
        System.out.println(obj2);//obj2不会被垃圾回收
    }
}
