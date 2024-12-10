package JUC.BlockingQueueStudy;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * 同步队列：生产者和消费者线程必须同时准备着
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + " put 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName() + " put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + " put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"AAA").start();


        new Thread(() -> {
           try {
               try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {throw new RuntimeException(e);}
               System.out.println(Thread.currentThread().getName() + " take " +blockingQueue.take());
               try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {throw new RuntimeException(e);}
               System.out.println(Thread.currentThread().getName() + " take " +blockingQueue.take());
               try {TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {throw new RuntimeException(e);}
               System.out.println(Thread.currentThread().getName() + " take " +blockingQueue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        },"BBB").start();
    }
}
