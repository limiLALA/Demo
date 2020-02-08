package demo;

import java.lang.reflect.Method;

public class ReflectDemo {

    interface MyInterface {
        void solve();
    }

    public static void main(String[] args) {
        Class<MyInterface> clazz = MyInterface.class;
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(method);
        }
    }


}
