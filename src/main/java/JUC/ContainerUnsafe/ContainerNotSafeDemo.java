package JUC.ContainerUnsafe;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 集合类不安全问题
 */

public class ContainerNotSafeDemo {
    public static void main(String[] args) {
       // unSafeArrayList();
       // unSafeHashSet();
        unSafeMap();
    }

    private static void unSafeArrayList() {
        List<String> list = new ArrayList<>();

        for(int i = 1;i<=30;i++){
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)) .start();
        }

        //线程报错：java.util.ConcurrentModificationException

        //解决方法2：
        List<String> list2 = Collections.synchronizedList(new ArrayList<>());

        //解决方法3：
        List<String> list3 = new CopyOnWriteArrayList<>();
        for(int i = 1;i<=30;i++){
            new Thread(()->{
                list3.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list3);
            },String.valueOf(i)) .start();
        }
    }

    public static void unSafeHashSet(){
        Set<String> set = new HashSet<>();

        //解决方法1：
        Set<String> set1 = Collections.synchronizedSet(new HashSet<>());

        //解决方法2：
        Set<String> set2 = new CopyOnWriteArraySet<>();

        //HashSet


        for(int i = 1;i<=30;i++){
            new Thread(()->{
                set2.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set2);
            },String.valueOf(i)) .start();
        }
    }

    public static void unSafeMap(){
        Map<String,String> map = new HashMap<>();

        //解决方法1：
        Map<String,String> map1 = Collections.synchronizedMap(new HashMap<>());
        //解决方法2：
        Map<String,String> map2 = new ConcurrentHashMap<>();

        for(int i = 1;i<=30;i++){
            new Thread(()->{
                map2.put(Thread.currentThread().getName(),UUID.randomUUID().toString().substring(0,8));
                System.out.println(map2);
            },String.valueOf(i)) .start();
        }
    }
}
