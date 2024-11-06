package JUC.ThreadCommunicate;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class ShareDataOnePlus{
    private Integer number = 0;

    private final ReentrantLock lock = new ReentrantLock();
    final Condition addCondition = lock.newCondition();
    final Condition subCondition = lock.newCondition();
    //+1
    public  void increment() throws InterruptedException{
        lock.lock();
        while(number!=0){
            addCondition.await();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+":"+number);
        subCondition.signal();
        lock.unlock();
    }

    //-1
    public  void decrement() throws InterruptedException{
        lock.lock();
        while(number!=1){
            subCondition.await();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+":"+number);
        addCondition.signal();
        lock.unlock();
    }

}

public class SignalAwaitDemo {


    public static void main(String[] args) {
        ShareDataOnePlus one = new ShareDataOnePlus();

        new  Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    one.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"+1").start();


        new  Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    one.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"-1").start();

    }

}
