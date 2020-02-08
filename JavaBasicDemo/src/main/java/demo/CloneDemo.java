package demo;


import java.io.Serializable;
import java.util.Arrays;

public class CloneDemo {

    static class Resource {
        String name = "rc";
    }

    static class Copy implements Cloneable {
        @Override
        protected Object clone() throws CloneNotSupportedException {
            Copy copy = new Copy();
            copy.a = new Integer(555);
            copy.s = new String("");
            copy.resource = new Resource();
            return copy;
        }
        Resource resource = new Resource();
        String s = new String("");
        Integer a = new Integer(555);
    }


    static class ShallowCloneDemo {
        static class Base implements Cloneable{
            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }
            ShallowCloneDemo.Resource resource = new Resource();
        }

        static class Resource implements Cloneable{

            @Override
            protected Object clone() throws CloneNotSupportedException {
                System.out.println(this.getClass().getName()+":"+"clone");
                return super.clone();
            }
        }

        static class Ext extends Base implements Cloneable{

            String a = new String();

            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }
        }

        public static void main(String[] args) throws CloneNotSupportedException {
            Ext ext = new Ext();
            Ext ext1 = (Ext) ext.clone();
            System.out.println(ext==ext1);
            System.out.println(ext.a==ext1.a);
            System.out.println(ext.resource==ext1.resource);
        }
    }

    static class DeepCloneDemo{
        static class Base implements Cloneable{
            @Override
            protected Object clone() throws CloneNotSupportedException {
                Base object = (Base) super.clone();
//                object.resource = (Resource) resource.clone();
                return object;
            }
            Resource resource = new Resource();
        }

        static class Resource implements Serializable{

            @Override
            protected Object clone() throws CloneNotSupportedException {
                System.out.println(this.getClass().getName()+":"+"clone");
                return super.clone();
            }
        }

        static class Ext extends Base implements Cloneable{

            String a = new String();

            @Override
            protected Object clone() throws CloneNotSupportedException {
                Ext ext = (Ext) super.clone();
                return ext;
            }
        }

        public static void main(String[] args) throws CloneNotSupportedException {
            Ext ext = new Ext();
            Ext ext1 = (Ext) ext.clone();
            System.out.println(ext==ext1);
            System.out.println(ext.a==ext1.a);
            System.out.println(ext.resource==ext1.resource);
        }
    }



    public static void main(String[] args) throws CloneNotSupportedException {
        Copy copy = new Copy();
        Copy[] copies = new Copy[]{copy};
        Copy[] new_copies = Arrays.copyOf(copies, copies.length);

        Copy new_copy = (Copy) copy.clone();

        System.out.println(copies[0].resource == new_copies[0].resource);
        System.out.println(copies[0].s == new_copies[0].s);


        System.out.println(copy.s==new_copy.s);
        System.out.println(copy.a==new_copy.a);
    }
}
