package JUC.CountDownLatchCyclicSemaph;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch 是 Java 平台提供的一个同步工具类，用于协调多个线程之间的执行顺序。
 * 它允许一个或多个线程等待其他线程完成某些操作后再继续执行。
 */
public class CountDownLatchDemo {
    public static void main(String[] args)
    {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <=6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"：国，被灭 ");
                countDownLatch.countDown();//减少计数值
            },CountryEnum.forEach_CountryEnum(i).getRetMessage()).start();
        }

        try {
            countDownLatch.await();// 阻塞直到计数值为 0
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+"：秦帝国统一华夏");
    }

    private static void closeDoor() {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <=6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName()+"：号同学离开教室");
                countDownLatch.countDown();//减少计数值
                },String.valueOf(i)).start();
        }

        try {
            countDownLatch.await();// 阻塞直到计数值为 0
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(Thread.currentThread().getName()+"：班长最后锁门走人");
    }
}
