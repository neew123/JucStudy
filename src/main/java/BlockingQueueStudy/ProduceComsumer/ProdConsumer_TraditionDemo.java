package BlockingQueueStudy.ProduceComsumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//资源类
class ShareData{
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() throws InterruptedException {
        lock.lock();
        try{
            //要用while进行判断，不能用if
            while(number!=0){
                //等待
                condition.await();
            }
            //生产
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);
            //通知唤醒
            condition.signalAll();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException {
    lock.lock();
    try{
        //判断
        while (number==0){
            //等待
            condition.await();
        }
        //消费
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);
        //通知唤醒
        condition.signalAll();
    }catch(Exception e){
        e.printStackTrace();
    }finally{
        lock.unlock();
    }
    }
}
/**
 * 一个初始值为0的变量，两个线程对其交替操作，一个加1一个减1，来5轮
 */
public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
            ShareData shareData = new ShareData();
            new Thread(()->{
                for (int i = 0; i < 5; i++) {
                    try {
                        shareData.increment();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            },"AAA").start();
            new Thread(()->{
                for (int i = 0; i < 5; i++) {
                    try {
                        shareData.decrement();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            },"BBB").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"CCC").start();
        new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    shareData.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"DDD").start();
    }
}
