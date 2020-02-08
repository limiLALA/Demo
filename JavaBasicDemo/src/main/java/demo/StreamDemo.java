package demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class StreamDemo {
    static class ArrayListStreamDemo{

        void solve(){
            int[] arr = new int[]{1,5,3,2,8};
            ArrayList<Integer> arrayList = new ArrayList<>(arr.length);
            for (int i = 0; i < arr.length; i++) {
                arrayList.add(arr[i]);
            }
            arrayList.stream().filter(new Predicate<Integer>() {
                @Override
                public boolean test(Integer integer) {
                    return integer>=5;
                }
            }).forEach(integer -> System.out.println(integer));

        }

        public static void main(String[] args) {
            new ArrayListStreamDemo().solve();
        }
    }
}
