package JVM;

public class GCParametersDemo {
    public static void main(String[] args) {
        System.out.println("GC root");
        byte[] bytes = new byte[1024 * 1024 * 50];
    }
}
