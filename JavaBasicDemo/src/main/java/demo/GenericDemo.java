package demo;

import java.util.LinkedList;
import java.util.List;

public class GenericDemo {

    static class Base{}

    static class Ext1 extends Base{
        public <T> T func(T t){
            return t;
        }
    }

    static class Ext2 extends Base{

    }

    static class A<T>{
        A(T t){

            System.out.println(t.getClass());
        }
    }



    public static void main(String[] args) {
        A<Integer> a = new A<>(new Integer(1));
        LinkedList<?> list = new LinkedList<>();
        LinkedList objList = new LinkedList();

    }
}
