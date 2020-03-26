package test;

public class Runner {
    public static void main(String[] args) {
        Object obj = new B();
        A a = (A) obj;
        a.show();
    }
}
