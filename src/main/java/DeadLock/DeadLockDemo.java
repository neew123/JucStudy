package DeadLock;

import java.util.concurrent.TimeUnit;

/**
 * 两个或两个以上的进程在执行过程中，
 * 因争夺资源而造成的一种互相等待现象，
 * 若无外力干涉那它们都将无法推进下去。
 */

class HoldLockThread implements Runnable{

    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockA+"\t 尝试获得："+lockB);
            try {TimeUnit.SECONDS.sleep(2);} catch (InterruptedException e) {throw new RuntimeException(e);}
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t 自己持有："+lockB+"\t 尝试获得："+lockA);
            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
            String lockA = "lockA";
            String lockB = "lockB";
            new Thread(new HoldLockThread(lockA,lockB),"ThreadAAA").start();
            new Thread(new HoldLockThread(lockB,lockA),"ThreadBBB").start();
    }
}
