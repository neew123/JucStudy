package JUC.ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 四种使用线程的方式
 * 1.继承Thread类
 * 2.实现runnable接口，没有返回值，不抛异常
 * 3.实现callable接口，有返回值，会抛异常
 * 4.线程池
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {
        //查看CPU的核数
        System.out.println("CPU核数："+Runtime.getRuntime().availableProcessors());

        //固定线程池：线程池中只有5个线程  模拟10个用户在5个窗口办理业务
        ExecutorService threadPool = Executors.newFixedThreadPool(5);
        try {
            for(int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
         threadPool.shutdown();
        }

        //单个线程池：newSingleThreadExecutor
        System.out.println("*********单个线程池*****************");
        ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
        try {
            for(int i = 1; i <= 10; i++) {
                singleThreadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            singleThreadPool.shutdown();
        }

        //可扩容线程池：newCachedThreadPool 一池N个线程
        System.out.println("*********可扩容线程池*****************");
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        try {
            for(int i = 1; i <= 10; i++) {
                cachedThreadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cachedThreadPool.shutdown();
        }
    }
}
