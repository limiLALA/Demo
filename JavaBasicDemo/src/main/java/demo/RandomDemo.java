package demo;

import java.util.Arrays;
import java.util.Random;

public class RandomDemo {
    static class RandomArrayDemo {

        static void printArray(int[] arr) {
            for (Integer integer : arr) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }

        static int[] shuffle(int[] arr) {
            // return a randomized array of the copy from the original one
            int[] result = Arrays.copyOf(arr, arr.length);
            Random random = new Random();
            for (int i = 0; i < result.length; i++) {
                int tmp = result[i];
                int next = random.nextInt(arr.length);
                result[i] = result[next];
                result[next] = tmp;

            }
            return result;
        }

        public static void main(String[] args) {
            int[] arr = new int[10];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = i + 1;
            }
            printArray(arr);
            printArray(shuffle(arr));
            printArray(arr);
            printArray(shuffle(arr));

        }
    }
}
