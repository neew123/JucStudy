package Lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 */
class MyCahe {
    //volatile修饰，保证可见性。因为缓存速度特别快。
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    //写入
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t 正在写入:" + key);
            //暂停一会线程
            try {TimeUnit.MILLISECONDS.sleep(300);} catch (InterruptedException e) {throw new RuntimeException(e);}
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            readWriteLock.writeLock().unlock();
        }
    }

    //读取
    public void get(String key) {
        readWriteLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + "\t 正在读取:");
            //暂停一会线程
            try {
                TimeUnit.MILLISECONDS.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Object res = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成:" + res);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            readWriteLock.readLock().unlock();
        }

    }

    //清除缓存
    public void clearMap(){
        map.clear();
    }
}

/**
 * 多个线程同时读取一个资源类没有问题，所以为了满足并发量，读取共享资源应该可以同时进行
 * 但是如果有一个线程想去写共享资源时，就不应该有其它线程可以对该资源进行读或写
 * <p>
 * 写操作：原子性+独占性,整个过程必须是一个完整的过程，中间不允许被分割，被打断
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCahe myCache = new MyCahe();
        for (int i = 1; i <= 5; i++) {
            final  int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt+"", tempInt+"");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 5; i++) {
            final  int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt+"");
            }, String.valueOf(i)).start();
        }
    }
}
