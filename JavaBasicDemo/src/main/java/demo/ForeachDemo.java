package demo;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

public class ForeachDemo {

    static class ArrayListForeachDemo {
        public static void main(String[] args) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            arrayList.add(4);
            for (Integer i : arrayList) {

            }
        }

    }

    static class COWArrayListForeachDemo {
        public static void main(String[] args) {
            CopyOnWriteArrayList<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
            copyOnWriteArrayList.add(1);
            for (Integer integer : copyOnWriteArrayList) {
                System.out.println(integer);
            }
        }
    }

    static class ConcuurentHashMapForeachDemo {
        public static void main(String[] args) throws InterruptedException {
            ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
            concurrentHashMap.put(1, 2);
            concurrentHashMap.put(5,3);
            concurrentHashMap.put(4,5);
            new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                concurrentHashMap.remove(4);
            }).start();
            for (Map.Entry<Integer, Integer> entry : concurrentHashMap.entrySet()) {
                Thread.sleep(1000);
                concurrentHashMap.remove(entry.getKey());
                System.out.println(entry.getKey());
                System.out.println(entry.getValue());

            }

        }
    }

    static class ArrayForeachDemo {
        public static void main(String[] args) {
            int[] arr = new int[]{1, 5, 9, 6, 3};
            for (Integer i : arr) {

            }
        }
    }
}
