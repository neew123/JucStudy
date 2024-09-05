package JUC.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMultiCondition {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition conditionA = lock.newCondition();
    private static final Condition conditionB = lock.newCondition();
    private static volatile boolean isA = true;


    public static void main(String[] args) {
        Thread threadA = new Thread(()->{
            for(int i = 0;i<5;i++){
                lock.lock();
                try {
                    while (!isA){
                        conditionA.await();
                    }
                    System.out.println("Thread A running...");
                    isA = false;
                    conditionB.signal();
                }catch (Exception e){
                    Thread.currentThread().interrupt();// 重新设置中断状态
                }finally {
                    lock.unlock();
                }
            }
        },"AA");

        Thread threadB = new Thread(()->{
            for(int i  = 0;i<5;i++){
                lock.lock();
                try {
                    while (isA){
                        conditionB.await();
                    }
                    System.out.println("ThreadB is running...");
                    isA = true;
                    conditionA.signal();
                }catch (Exception e){
                    Thread.currentThread().interrupt();// 重新设置中断状态
                }finally {
                    lock.unlock();
                }
            }
        },"BB");

        threadA.start();
        threadB.start();
    }
}
