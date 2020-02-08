package demo;

import java.util.LinkedList;

public class OOMDemo {
    public static void main(String[] args) throws InterruptedException {
        LinkedList<int[]> list = new LinkedList<>();
        for (int i = 0; ; i++) {

//            list.add(new int[1000]);
            System.out.println("new tmp "+i);
            int[] tmp = new int[20000];
            Thread.sleep(1000);
        }
    }
}
