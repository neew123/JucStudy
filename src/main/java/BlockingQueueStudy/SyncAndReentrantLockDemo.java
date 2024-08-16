package BlockingQueueStudy;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized和Lock的区别
 * 1. synchronized是Java中的关键字，Lock是一个类
 * 2. synchronized是不可中断，Lock可中断
 * 3.synchronized不能绑定多个条件，lock的锁可以绑定多个条件
 *
 * 题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
 * AA打印5次，BB打印10次，CC打印15次
 * 紧接着
 * AA打印5次，BB打印10次，CC打印15次
 * ...
 * 重复10轮
 */

class ShareResource{
    private int number = 1;//A:1  B:2  C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();
    //判断
    public void print5(){
        lock.lock();
        try{
            //判断
            while(number!=1){
                c1.await();
            }
            //执行
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number = 2;
            c2.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print10(){
        lock.lock();
        try{
            //判断
            while(number!=2){
                c2.await();
            }
            //执行
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number = 3;
            c3.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    public void print15(){
        lock.lock();
        try{
            //判断
            while(number!=3){
                c3.await();
            }
            //执行
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            //通知
            number = 1;
            c1.signal();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }
}

public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
       ShareResource shareResource = new ShareResource();
       new Thread(()->{
           for (int i = 1; i <= 10; i++) {
               shareResource.print5();
           }
       },"AAA").start();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                shareResource.print10();
            }
        },"BBB").start();
        new Thread(()->{
            for (int i = 1; i <= 10; i++) {
                shareResource.print15();
            }
        },"CCC").start();
    }
}
