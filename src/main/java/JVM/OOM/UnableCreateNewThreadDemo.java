package JVM.OOM;


/**
 * 应用创建太多线程超出系统范围
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for(int i =1;;i++){
            System.out.println("******** i  = " + i);
            new Thread(()->{
                try {Thread.sleep(Integer.MAX_VALUE);} catch (InterruptedException e) {throw new RuntimeException(e);}
            },""+i).start();
        }
    }
}
