package JUC.Synchronized;


class Ticket{
    private int ticket=10;

    public synchronized void sale(){
        if(ticket<=0){
            System.out.println(Thread.currentThread().getName()+"票卖完了");
            return;
        }
        try {
            System.out.println(Thread.currentThread().getName()+"余票："+ticket);
            Thread.sleep(200);
            System.out.println(Thread.currentThread().getName()+"开始售票，余票："+--ticket);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class SaleTicket {

    public static void main(String[] args) {

        Ticket ticket=new Ticket();
        //线程操作资源类
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                ticket.sale();
            }
        },"窗口1").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                ticket.sale();
            }
        },"窗口2").start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                ticket.sale();
            }
        },"窗口3").start();
    }
}
