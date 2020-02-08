package demo;

public class HashEqualDemo {

    public static void main(String[] args) {
        String s1 = "ok";
        String s2 = "o"+"k";

        System.out.println(s1==s2);
        System.out.println(s1.hashCode());
        System.out.println(s2.hashCode());
    }
}
