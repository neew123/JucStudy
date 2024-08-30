package JUC.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Phone implements Runnable{
    public synchronized void sendSms() {
        System.out.println(Thread.currentThread().getName() + "\t sendSms()");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t sendEmail()");
    }

    Lock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }

    public void get() {
        //lock 与 unlock 方法必须配对使用
        lock.lock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t get()");
            set();
        } finally {
            lock.unlock();
            lock.unlock();
        }
    }
    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t set()");
        } finally {
            lock.unlock();
        }
    }
}


/**
 * 同一个线程外层函数获得锁之后，内层递归函数仍然能获取该锁的代码，在同一个线程在外层方法获取锁的时候，在进入内层方法会自动获取锁。
 */
public class ReenterLockDemo {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        //synchronized是可重入锁
        new Thread(() -> {
            phone.sendSms();
        }, "T1").start();
        new Thread(() -> {
            phone.sendSms();
        }, "T2").start();
        System.out.println();
        TimeUnit.SECONDS.sleep(2);
        //ReentrantLock是可重入锁
        new Thread(() -> {
            phone.get();
        }, "T3").start();
        new Thread(() -> {
            phone.get();
        }, "T4").start();
    }
}
