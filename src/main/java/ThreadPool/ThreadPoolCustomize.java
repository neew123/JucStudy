package ThreadPool;

import java.util.concurrent.*;

/**
 * 自定义线程池
 */
public class ThreadPoolCustomize {
    public static void main(String[] args) {
        //CallerRunsPolicy main线程使用了线程池递交任务，任务回退到main线程。
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,5,
                1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.CallerRunsPolicy());


        //模拟10个用户来办理业务，每个用户就是一个来自外部的请求线程
        try {
            for (int i = 1; i <= 10 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"在办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
