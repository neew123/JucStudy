package JUC.Volatile;

public class SingletonDemo {

    private static  volatile SingletonDemo instance = null;

    private SingletonDemo() {
        System.out.println(Thread.currentThread().getName()+"线程的构造方法");
    }

    //方法1：加synchronized关键字
    public static synchronized SingletonDemo getInstance1() {
        if (instance == null) {
            instance = new SingletonDemo();
        }
        return instance;
    }

    //方法2：双端检锁机制，在很多个线程的情况下可能因为指令重排出现多个构造器实例
    public static SingletonDemo getInstance2() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    //方法3：双端检测+用volatile关键字修饰
    public static SingletonDemo getInstance3() {
        if (instance == null) {
            synchronized (SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) throws InterruptedException {
        //单线程下的测试
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
//        System.out.println(SingletonDemo.getInstance()==SingletonDemo.getInstance());
//

        //多线程下的测试
        for(int i = 1;i<=10;i++){
            new Thread(()->{
                SingletonDemo.getInstance3();
                //System.out.println(SingletonDemo.getInstance());
            },String.valueOf(i)).start();
        }
    }
}
