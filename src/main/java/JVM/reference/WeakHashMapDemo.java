package JVM.reference;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakHashMapDemo {
    public static void main(String[] args) throws InterruptedException {
        myHashMap();
        System.out.println("=============");
        myWeakHashMap();
    }

    private static void myHashMap(){
        HashMap<Integer,String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.out.println("----GC----");
        System.gc();
        System.out.println(map+" size: "+map.size());
    }

    private static void myWeakHashMap() throws InterruptedException {
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(130);
        String value = "WeakHashMap";

        map.put(key,value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.out.println("----GC----");
        System.gc();
        Thread.sleep(500);
        System.out.println(map +" size: "+map.size());
    }
}
