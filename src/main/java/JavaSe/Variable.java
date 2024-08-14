package JavaSe;

public class Variable {
    static int s;
    int i,j;
    {
        int i = 1;
        i++;
        j++;
        s++;
    }
    public void test(int j){
        j++;
        i++;
        s++;
    }
    public static void main(String[] args) {
        Variable v1 = new Variable();
        Variable v2 = new Variable();
        v1.test(10);
        v1.test(20);
        v2.test(30);
        System.out.println(v1.i+" "+v1.j+" "+v1.s);// 2 1 5
        System.out.println(v2.i+" "+v2.j+" "+v2.s);// 1 1 5
    }
}
