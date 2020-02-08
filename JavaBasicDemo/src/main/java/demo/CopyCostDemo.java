package demo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

interface Runner {
    void run();
}


public class CopyCostDemo {

    static class TimerAop{
        Runner runner;

        public TimerAop(Runner runner) {
            this.runner = runner;
        }

        public void run(){
            long before = System.currentTimeMillis();
            runner.run();
            System.out.println("cost: "+ (System.currentTimeMillis()-before));
        }
    }


    static class ForCopy implements Runner {

        int from[];
        int to[];

        public ForCopy(int[] from) {
            this.from = from;
            this.to = new int[this.from.length];
        }



        public void run(){
            System.out.println(this.getClass().getName());
            for (int i = 0; i < this.from.length; i++) {
                this.to[i] = this.from[i];
            }
        }

    }

    static class SystemArrayCopyDemo implements Runner {

        int from[];
        int to[];



        public SystemArrayCopyDemo(int[] from) {
            this.from = from;
            this.to = new int[this.from.length];
        }

        @Override
        public void run() {
            System.out.println(this.getClass().getName());
            System.arraycopy(from, 0, to, 0, from.length);
//        System.arraycopy(this.data.getFrom(),0,this.data.getTo(),0,this.data.length());
        }
    }

    static class ArrayCopyOfDemo implements Runner {
        int from[];
        int to[];
        //
        public ArrayCopyOfDemo(int[] from) {
            this.from = from;
            this.to = new int[from.length];
        }



        @Override
        public void run() {
            System.out.println(this.getClass().getName());
            this.to = Arrays.copyOf(from, from.length);
//        this.data.getTo()
        }
    }

    static class CloneCopyDemo implements Runner {

        int from[];
        int to[];

        public CloneCopyDemo(int[] from) {
            this.from = from;
            this.to = new int[from.length];
        }

        @Override
        public void run() {
            System.out.println(this.getClass().getName());
            to = from.clone();
        }
    }
    public static void main(String[] args) {
        int[] from = new int[10000000];
        for (int i = 0; i < from.length; i++) {
            from[i] = i;
        }
        new TimerAop(new ForCopy(from)).run();
        new TimerAop(new SystemArrayCopyDemo(from)).run();
        new TimerAop(new ArrayCopyOfDemo(from)).run();
        new TimerAop(new CloneCopyDemo(from)).run();

    }

}
