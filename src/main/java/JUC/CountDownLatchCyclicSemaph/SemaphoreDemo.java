package JUC.CountDownLatchCyclicSemaph;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 信号量主要用于两个目的：一个是用于多个共享资源的互斥作用；另一个控制并发线程数。（争车位）
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟三个停车位
        for (int i = 1; i <= 6 ; i++) { //模拟6部汽车
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName()+"抢到车位");
                    try {TimeUnit.MILLISECONDS.sleep(3);} catch (InterruptedException e) {throw new RuntimeException(e);}
                    System.out.println(Thread.currentThread().getName()+"停车3s后离开车位");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
