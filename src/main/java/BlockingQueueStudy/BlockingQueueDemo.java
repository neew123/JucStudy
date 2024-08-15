package BlockingQueueStudy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue：由数组结构组成的有界阻塞队列
 * LinkedBlockingQueue
 * synchronousQueue
 *
 * 阻塞队列：
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //1.抛出异常
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);
        System.out.println("---------抛出异常场景------------");
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        //System.out.println(blockingQueue.add("d"));插入越界
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        ///System.out.println(blockingQueue.remove());删除越界
        //2.特殊场景
        System.out.println("---------特殊情况场景------------");
        BlockingQueue<String> blockingQueue2 = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue2.offer("a"));
        System.out.println(blockingQueue2.offer("b"));
        System.out.println(blockingQueue2.offer("c"));
        System.out.println(blockingQueue2.offer("d"));//插入越界
        System.out.println(blockingQueue2.poll());
        System.out.println(blockingQueue2.poll());
        System.out.println(blockingQueue2.poll());
        System.out.println(blockingQueue2.poll());//删除越界
        //3.阻塞
        System.out.println("---------阻塞场景------------");
        BlockingQueue<String> blockingQueue3 = new ArrayBlockingQueue<>(3);
        blockingQueue3.put("a");
        blockingQueue3.put("b");
        blockingQueue3.put("c");
        //System.out.println("----d阻塞------");
        //blockingQueue3.put("d");
        blockingQueue3.take();
        blockingQueue3.take();
        blockingQueue3.take();
        //blockingQueue3.take();

        //4.超时
        System.out.println("---------超时场景------------");
        BlockingQueue<String> blockingQueue4 = new ArrayBlockingQueue<>(3);
        System.out.println(blockingQueue4.offer("a", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue4.offer("b", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue4.offer("c", 2L, TimeUnit.SECONDS));
        System.out.println(blockingQueue4.offer("d", 2L, TimeUnit.SECONDS));
    }
}
