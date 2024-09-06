package JVM.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 虚引用：get方法总是返回null
 *
 * 创建引用的时候可以指定关联的队列，当GC释放对象内存的时候，会将引用加入到引用队列。
 * 如果程序发现某个虚引用已经被加入到引用队列，那么就可以在所引用的对象被回收之前采取必要的行动，这相当于是一种通知机制。
 *
 * 当关联的引用队列中有数据时：意味引用指向的堆内存中的对象被回收。通过这种方式，JVM允许我们在对象销毁后做一些我们自己想做的事情
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        Object o1 = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        PhantomReference<Object> phantomReference = new PhantomReference<>(o1, referenceQueue);

        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());

        System.out.println("==== begin gc ====");
        o1 = null;
        System.gc();
        Thread.sleep(500);
        System.out.println(o1);
        System.out.println(phantomReference.get());
        System.out.println(referenceQueue.poll());//在发生GC后，虚引用被放到引用队列

    }
}
