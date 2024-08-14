package ContainerUnsafe;

import java.util.concurrent.ConcurrentHashMap;

public class HashMapDemo {
    public static void main(String[] args) {
        ConcurrentHashMap map = new ConcurrentHashMap();

        map.put("1", "1");
    }
}
