package demo;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

class OuterClass {
    class MemberInnerClass {
        public int a = 1;
//        public static int b = 1; // member inner class 不能有static

        public MemberInnerClass(int a) {
            this.a = a;
            OuterClass.this.run();
        }
    }


    public void run() {
        int a = 2;
        class LocalInnerCLass {
            int get() {
                //local inner class 只能获取 final local variable
//                return a;
                return 0;
            }

            void set(int newa) {
                // local inner class 无法修改local variable
//                a = newa;

            }
        }
    }
}


public class OOPDemo {

    static class UMLDemo{
        static interface Worker{

        }
        static class Base {

        }
        static class Ext extends Base implements Worker{
            Resource resource = new Resource();
        }
        static class Resource {}

    }



    static class Base {
        private int a = 8;
        void call() {
            System.out.println(this);
            System.out.println("base.call");

        }

        void call(int a){}


        static void staticCall(){
            System.out.println("base.static call");
        }

        public static void main(String[] args) {
            Base base  = new Extend();
//            base.call(1);
//            System.out.println(base.a);
            base.call();
        }
    }

    static class Extend extends Base {
        //int a = 9;
        int val = 0;

        static void staticCall(){
            System.out.println("ext.static call");
        }

        public static void main(String[] args) {
            new Extend().call();
        }

        void call() {
            super.call();
            System.out.println("ext.call");
        }
    }

    class InnerClassDemo {

    }


    public static Object getInstance() {
        class LocalInnerClass {
            LocalInnerClass() {

            }
        }
        return new LocalInnerClass();
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Base base = new Extend();
        Extend extend = (Extend) base;
        //System.out.println(extend.a);
    }
}
