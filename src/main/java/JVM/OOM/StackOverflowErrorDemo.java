package JVM.OOM;


/**
 * 栈：方法栈溢出
 */
public class StackOverflowErrorDemo  {

    public static void main(String[] args) {
        stackOverflow();
    }
    private static void stackOverflow() {
        stackOverflow();
    }
}
