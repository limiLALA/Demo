package demo;

public class StringDemo {

    static class ValueOfDemo{
        public static void main(String[] args) {
            int i=100;
            String s1=i+"";
            String s2=String.valueOf(i);
            System.out.println(s1==s2);
            System.out.println(s1.equals(s2));
        }
    }

    static class StringBuilderDemo{
        public static void main(String[] args) {
            StringBuilder sb = new StringBuilder();
            sb.append("asd");
            String aa = sb.toString();
            String bb = sb.toString();
            System.out.println(aa==bb);
        }
    }

    static class StringInternDemo{
        public static void main(String[] args) {

//            String a = new String("ab")+new String("c");

            String b = null;
            b = "abc";

            String a = new String("ab")+new String("c");//abc
            a = new String("abc");

            a = "ab"+new String("c");//转化为以下步骤
            // StringBuilder sb = new StringBuilder();
            // c++ 的 StringStream
            // 维护一个缓冲区，假设初始char[] buffer = new char[20]，len=0
            // sb.append("ab");//buffer[len++]='a';buffer[len++]='b';
            // sb.append("c");//buffer[len++]='c';
            // a = sb.toString();// new String(buffer)



            //b = a.intern();
            System.out.println(a==b);
//            String b = "a"+"bc";
//            String c = "abc";
//            String d = "aa";
//            String e = "a";

//            String aa = new StringBuilder().append("aa").append("a").toString();
//            System.out.println(a == aa);
//            System.out.println(a==b);
//            System.out.println(a==c);
//            System.out.println(b==c);
        }
    }

}
