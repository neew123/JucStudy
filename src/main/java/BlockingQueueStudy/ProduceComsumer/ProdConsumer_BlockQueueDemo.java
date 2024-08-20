package BlockingQueueStudy.ProduceComsumer;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者消费者，阻塞队列模式
 */
class MyResource{
    private volatile boolean FLAG = true;//高并发情况下保证可见性，默认开启：进行生产+消费
    private AtomicInteger atomicInteger = new AtomicInteger();

    //适配不同的阻塞队列类型
    private BlockingQueue<String> blockingQueue = null;
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println("当前阻塞队列类型：" + blockingQueue.getClass().getName());
    }

    public void myProd() throws Exception {
        String data = null;
        boolean returnValue;
        while(FLAG){
            data = atomicInteger.incrementAndGet()+"";
            returnValue = blockingQueue.offer(data,2L, TimeUnit.SECONDS);
            if(returnValue){
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"成功");
            }else {
                System.out.println(Thread.currentThread().getName()+"\t 插入队列"+data+"失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t 停止生产，FLAG=false");
    }

    public void myConsumer() throws Exception{
        String result = null;
        while (FLAG){
            result = blockingQueue.poll(2L,TimeUnit.SECONDS);
            if(null == result || result.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t 消费停止，FLAG=FALSE");
            }
            System.out.println(Thread.currentThread().getName()+"\t 消费队列"+result+"成功");
        }
    }


}
public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {

    }
}
