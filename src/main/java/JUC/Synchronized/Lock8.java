package JUC.Synchronized;


import java.util.concurrent.TimeUnit;


/**
 * 1.先短信还是先邮件
 *  先短信后邮件，先发优势（多个线程一把锁，锁对象是实例）
 * 2.停4s在短信方法内，先短信还是先邮件
 *    先短信后邮件
 * 3.停4s在短信方法内，先访问短信再访问hello方法，先短信还是hello
 *    先hello后短信
 * 4.先有两部手机，第一部发短信，第二部发邮件，先发短信还是先发邮件
 *    先邮件后短信（2线程2把锁）
 * 5.两个静态同步方法，一部手机，先发短信还是先发邮件
 *    先短信后邮件（多个线程一把锁，锁对象是类）
 * 6.两个静态同步方法，一部手机，先发短信还是先发邮件
 *    先短信后邮件（多个线程一把锁，锁对象是类）
 * 7.一个静态同步方法，一个普通同步方法，一部手机，先发短信还是先发邮件
 *    先邮件后短信（2个线程2把锁，锁对象是类和对象）
 * 8.一个静态同步方法，一个普通同步方法，两部手机，先发短信还是先发邮件
 *    先邮件后短信（2个线程2把锁，锁对象是类和对象）
 */
class Phone{
    public static synchronized void sendSms(){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void senEmail(){
        System.out.println("发邮件");
    }

    public void hello(){
        System.out.println("hello");
    }

}



public class Lock8 {

    public static void main(String[] args) {
        Phone phone1 = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone1.sendSms();
        }, "A").start();

        new Thread(()-> {
            //phone.senEmail();
            phone2.senEmail();
        },"B").start();

    }
}
