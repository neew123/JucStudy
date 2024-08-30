package JUC.ThreadPool.Callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("******** come in callable ********");
        return 1024;
    }
}

/**
 * 1.多个线程做同样的futureTask，计算结果只会计算一次；
 */
public class CallableDemo {

    public static void main(String[] args) throws Exception {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());
        Thread thread1 = new Thread(futureTask, "AA");
        //这时：BB线程和AA做同样的futureTask，但是AA线程已经执行完了，BB线程会直接拿到AA线程的计算结果
        Thread thread2 = new Thread(futureTask, "BB");
        thread1.start();
        thread2.start();
        System.out.println("****** futureTask get:"+futureTask.get());
        //多线程工作
        int result01 = 100;
        //get方法要求获得callable线程的计算结果，如果没算完，main线程因需要获取结果会一直阻塞直到计算完成
        while (!futureTask.isDone()){

        }
        int result02 = futureTask.get();
        System.out.println("****** final result:"+(result01+result02));
    }

}
