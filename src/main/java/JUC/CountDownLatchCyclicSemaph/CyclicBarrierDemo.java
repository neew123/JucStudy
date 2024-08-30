package JUC.CountDownLatchCyclicSemaph;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 的核心是一个屏障，线程调用 await() 方法时会在此屏障上等待，直到所有参与的线程都到达屏障。一旦最后一个线程到达屏障，所有线程都会被释放并继续执行。
 * 与 CountDownLatch 不同的是，CyclicBarrier 可以重用，即在一次所有线程都通过屏障后，它可以再次被所有线程使用。
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        //CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });

        for (int i = 1; i <= 7 ; i++) {
            int finalI = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集到第"+ finalI +"颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();

        }

    }
}
