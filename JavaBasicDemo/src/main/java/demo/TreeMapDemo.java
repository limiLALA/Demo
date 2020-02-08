package demo;

import java.util.TreeMap;
import java.util.TreeSet;


public class TreeMapDemo {

    static class P implements Comparable<P>{

        int a;
        int b;
        P(int a, int b){
            this.a = a;
            this.b = b;

        }

        @Override
        public int compareTo(P o) {
            if (a==o.a){
                return o.b-b;
            }
            return a-o.a;
        }

        @Override
        public String toString() {
            return this.a+" "+this.b;
        }
    }

    public static void main(String[] args) {

        int[] a = new int[]{1,1,2,2};
        int[] b = new int[]{2,1,4,2};
        TreeSet<P> ts = new TreeSet<>();
        for(int i=0; i<a.length; i++){
            P p = new P(a[i], b[i]);
            ts.add(p);
        }
        for (P p: ts){
            System.out.println(p);
        }
    }
}
