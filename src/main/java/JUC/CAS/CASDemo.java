package JUC.CAS;

import java.util.concurrent.atomic.AtomicInteger;

/** 比较并交换  CAS：compare and set
 * @create 2021-03-07 21:07
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);
        System.out.println(atomicInteger.compareAndSet(5, 2020)+" current data:"+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 1024)+" current data:"+atomicInteger.get());

        atomicInteger.getAndIncrement();
    }

}
