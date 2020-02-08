package demo;

public class BoxedTypeDemo {
    static class Int2Integer{
        public static void main(String[] args) {
           Integer integer = 1;
        }
    }

    static class Integer2Int{
        public static void main(String[] args) {
            Integer integer = new Integer(555);
            int i = integer;
        }

    }



    public static void main(String[] args) {
        Integer integer = Integer.valueOf("1ff", 16);
        System.out.println(integer);
        System.out.println(integer.byteValue());


    }
}
