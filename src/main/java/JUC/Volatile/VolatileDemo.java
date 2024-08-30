package JUC.Volatile;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Mydata{
    volatile int number = 0;
    public void addTo60(){
        this.number = 60;
    }

    //尽管加了volatile关键字，但是并不能保证原子性
    public void addPlusPlus(){
        //number++的操作：1. 先读取number的值 2.在number基础上加1 3.将number的值重新写回
        this.number = this.number+2;
    }

    public void mySort(){
        int x = 11; //语句1
        int y = 12; //语句2
        x = x+5; //语句3
        y = x*x; //语句4
    }
    AtomicInteger atomicInteger = new AtomicInteger();
    public void addAtomic(){
        atomicInteger.getAndIncrement();
    }
}

/*
* 1. 验证volatile可见性（即时通讯机制）
* 1.1 假如int number = 0；number 变量之前根本没有添加volatile关键字修饰,没有可见性。举例：seeOkByVolatile
*
* 2. 验证volatile不保证原子性
* 2.1 如何解决原子性
*     * 加上Synchronized 关键字
*     * 使用JCUC的AtomicInteger
 */
public class VolatileDemo {
    public static void main(String[] args) { //一切方法的入口
        Mydata mydata = new Mydata();
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    mydata.addPlusPlus();
                    mydata.addAtomic();
                }
            }, String.valueOf("线程"+i)).start();
        }
        //需要等待上面20个线程全部计算完成后，再用main线程取得最终的结果值是多少？
        while (Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+" int type,main thread get the value " + mydata.number);
        System.out.println(Thread.currentThread().getName()+" AtomicInteger type,main thread get the value " + mydata.atomicInteger);

    }

    //volatile可以保证可见性，及时通知其它线程，主物理内存的值已经被修改
    private static void seeOkByVolatile() {
        Mydata mydata = new Mydata();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " come in");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mydata.addTo60();
            System.out.println(Thread.currentThread().getName() + " update number value to 60");
        }, "AAA").start();

        //第2个线程就是main线程
        while (mydata.number == 0){
            //main线程就一直在这里等待循环，直到number的值发生改变
        }
        System.out.println(Thread.currentThread().getName() + " main thread over,main thread get the value " + mydata.number);
    }
}
