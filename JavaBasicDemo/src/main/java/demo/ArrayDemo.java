package demo;


import java.util.Arrays;

public class ArrayDemo {

    static class ArrayDefinitionDemo{
        public static void main(String[] args) {
            int[] arr1 = {1,2,3};
            int[] arr2 = new int[]{1,2,3};
            arr1[0] = 2;
        }
    }

    static class UtilDemo{
        public static void main(String[] args) {
            int[] a = new int[]{1,2,5,6,9,10};
        }
    }

    public static void main(String[] args) {
        int[] a = {};
        int[] b = new int[0];
    }
}
