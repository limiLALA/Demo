package demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class VolatileDemo {
    static class MemoryUnitVolatileDemo{
        int r = 2;
        void increase(){
        }
        public static void main(String[] args) {
            new MemoryUnitVolatileDemo().run();
        }

        void run() {
            new Thread(()->{
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                r = 1;
                log.info("{}:r=1",Thread.currentThread());
            }).start();
            new Thread(()->{
                while (r!=1) log.info("{}:r!=1",Thread.currentThread());
                log.info("{}:r==1",Thread.currentThread());
            }).start();
        }
    }

    static class HandlerVolatileDemo{
        static class R{
            int res;
        }
        R r = new R();
        {
            r.res = 2;
        }

        public static void main(String[] args) {
            new HandlerVolatileDemo().run();
        }

        private void run() {
            new Thread(()->{
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                r.res = 1;
            }).start();
            new Thread(()->{
                while (r.res!=1) System.out.println("r.res!=1");
                System.out.println("r.res==1");
            }).start();
        }
    }

    static class ArrayVolatileDemo{


        int[] arr = new int[]{1,2};

        void run(){
            new Thread(()->{
                try {
                    for (int i = 0; i < arr.length; i++) {
                        Thread.sleep(1000);
                        arr[i] = 1;

                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            for (int i = 0; i < arr.length; i++) {
                final int fi = i;
                new Thread(()->{
                    while (true) {
                        if (arr[fi]==1){
                            log.info("{}:arr[{}]==1",Thread.currentThread(),fi);
                            break;
                        }
                        log.info("{}:arr[{}]!=1",Thread.currentThread(),fi);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }).start();
            }

        }

        public static void main(String[] args) {


            new ArrayVolatileDemo().run();

        }
    }

    static class Res{
        volatile int a = 0;
    }

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(100);
        Res res = new Res();
        for (int i = 0; i < 500; i++) {
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
//                    log.info("{}:tmp==0?{}",Thread.currentThread(),res.a==0);
                    res.a++;
                    countDownLatch.countDown();
                    //int tmp = res.a;
                    //log.info("{}:tmp={}",Thread.currentThread(),res.a);
                }
            });
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(res.a);
        threadPool.shutdown();
    }

}
