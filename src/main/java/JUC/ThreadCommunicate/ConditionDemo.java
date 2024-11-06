package JUC.ThreadCommunicate;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionDemo {

    private final ReentrantLock lock = new ReentrantLock();

    private Integer number = 1;

    private final Condition conditionA = lock.newCondition();
    private final Condition conditionB = lock.newCondition();
    private final Condition conditionC = lock.newCondition();

    public void print5() throws InterruptedException {
        lock.lock();
        while (number!=1){
            conditionA.await();
        }
        for (int i = 1; i <= 5; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
        number = 2;
        conditionB.signal();
        lock.unlock();
    }

    public void print10() throws InterruptedException {
        lock.lock();
        while (number!=2){
            conditionB.await();
        }
        for (int i = 1; i <= 10; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
        number = 3;
        conditionC.signal();
        lock.unlock();
    }


    public void print15() throws InterruptedException {
        lock.lock();
        while (number!=3){
            conditionC.await();
        }
        for (int i = 1; i <= 15; i++) {
            System.out.println(Thread.currentThread().getName()+":"+i);
        }
        number = 1;
        conditionA.signal();
        lock.unlock();
    }


    public static void main(String[] args) {
        ConditionDemo conditionDemo = new ConditionDemo();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    conditionDemo.print5();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"AA").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    conditionDemo.print10();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"BB").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    conditionDemo.print15();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"CC").start();
    }
}
