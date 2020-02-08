package demo;


import com.sun.javafx.util.Logging;
import com.sun.javafx.util.Utils;

import java.util.ArrayList;

class Super {
    static {
        //1
        System.out.println("super.static block");
    }

    {
        //3
        System.out.println("super.nonstatic block");
    }

    String classname = "super";
    static int static_field = 123;

    static void staticOverride() {
        System.out.println("super.sfunc");
    }

    void nonstaticOverride() {
        System.out.println(this.getClass().getName() + ": nonstaticOverride");
    }

    public Super() {
        //4
        System.out.println("super.constructor");
    }

    void overload(int a) {

    }

    void overload(Integer a) {

    }

//    static void overload(int a){
//        // overload 只与形参有关
//    }

//    int overload(int a){
//      // overload 只与形参有关
//    }
}

class Sub extends Super {
    static {
        //2
        System.out.println("sub.static block");
    }

//    {
//        //4
//        System.out.println("sub.nonstatic bloc");
//    }

//    static int s = 123;

    String classname = "sub";//字面量“sub”


    static void staticOverride() {
        System.out.println("sub.sfunc");
    }//类方法的 override 没有 override的意义

    @Override
    void nonstaticOverride() {
        System.out.println(this.getClass().getName() + ": nonstaticOverride");
    }

    void nonstaticOverride(int a) {
        //这里并不是 override 只是定义了一个新的方法
    }

//    int nonstaticOverride(){
//      //override 必须保证 return 和 形参都一致
//    }

    public Sub() {
        //6
        System.out.println("sub.constructor");
    }

    {
        //5
        System.out.println("sub.nonstatic bloc");
    }
}


interface Loader {
    void Load();
}


public class ClassLoaderDemo {

    static class ClassInitDemo {

        static class Super {
            static int val;

            static {
                val++;
                System.out.println("super.clinit");

            }

            {
                System.out.println("super.init");
                val--;
            }

            public Super() {
                show();
            }

            void show() {
                System.out.println(this.getClass().getName());
            }

            static void sshow() {
                System.out.println(Super.class);
            }
        }

        static class Sub extends ClassInitDemo.Super {
            static {
                val++;
                System.out.println("sub.clinit");
            }

            {
                System.out.println("sub.init");
                val--;
            }

            //        static int val;
            void show() {

                System.out.println(this.getClass().getName());
            }

            static void sshow() {
                System.out.println(Sub.class);
            }
        }

        public static void main(String[] args) throws ClassNotFoundException {
            ClassInitDemo.Super s = new Sub();
        }
    }

    static class BootstrapClassLoaderDemo {

    }

    static class ExtClassLoaderDemo {

        private String class_name;

        public ExtClassLoaderDemo(String class_name) {
            this.class_name = class_name;
        }

        public static void main(String[] args) {

        }

        public void Load() {

        }
    }

    static void printClassLoader() {
        System.out.println(ClassLoaderDemo.class.getName());
        System.out.println(ClassLoaderDemo.class.getClassLoader());
        System.out.println(Logging.class.getName());
        System.out.println(Logging.class.getClassLoader());
        System.out.println(ArrayList.class.getName());
        System.out.println(ArrayList.class.getClassLoader());

        Class clazz = ClassLoaderDemo.class;
        ClassLoader cl = clazz.getClassLoader();
        while (cl != null) {
            System.out.println(cl);
            cl = cl.getParent();
        }


    }


    static void usingExtCl() {
        ClassLoader cl = Utils.class.getClassLoader();
        System.out.println("class loader of com.sun.javafx.util.Utils:" + cl.getClass());
        Class c = null;
        try {
            c = cl.loadClass("demo.CollectionDemo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (c != null)
            System.out.println("class loader of demo.CollectionDemo: " + c.getClassLoader());


    }


    public static void main(String[] args) {
        usingExtCl();


    }

    static class LoadClassDemo {
        static class MyClassLoader extends ClassLoader implements Loader{

            @Override
            protected Class<?> findClass(String name) throws ClassNotFoundException {
                return super.findClass(name);
            }

            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return super.loadClass(name);
            }

            @Override
            public void Load() {

            }
        }
    }
}


