package JUC.ThreadCommunicate;


class ShareDataOne{
    private Integer number = 0;

    //+1
    public synchronized void increment() throws InterruptedException{
        while(number!=0){
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+":"+number);
        this.notifyAll();
    }

    //-1
    public synchronized void decrement() throws InterruptedException{
        while(number!=1){
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+":"+number);
        this.notifyAll();
    }

}

public class NotifyWaitDemo {


    public static void main(String[] args) {
        ShareDataOne one = new ShareDataOne();

        new  Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    one.increment();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"+1").start();


        new  Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    one.decrement();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        },"-1").start();

    }

}
