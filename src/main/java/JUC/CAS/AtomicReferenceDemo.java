package JUC.CAS;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.concurrent.atomic.AtomicReference;

@AllArgsConstructor
@Getter
@ToString
class User {
    String userName;
    int age;
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {
        User user1 = new User("zhangsan",22);
        User user2 = new User("lisi",25);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(user1);
        System.out.println(atomicReference.compareAndSet(user1,user2)+" "+atomicReference.get().toString());
        System.out.println(atomicReference.compareAndSet(user1,user2)+" "+atomicReference.get().toString());
    }
}
