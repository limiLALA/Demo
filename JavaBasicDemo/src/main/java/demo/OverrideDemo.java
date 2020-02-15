package demo;

public class OverrideDemo {

    static String s = "Asd";

    static class A {
        int a1(int i){
            return 0;
        }

        static final void fun(){
          System.out.println('A');
        }

    }
    static class B extends A{
        //override ���뷵��ֵ���β��븸��һ����
        @Override
        int a1(int i) {
            System.out.println(s);
            return 0;
        }

        static final void fun(){
          System.out.println('B');
        }
    }

    public static void main(String[] args) {
        A a = new B();
        a.fun();//A.fun()
        B b = new B();
        b.fun();//B.fun()
    }
}
