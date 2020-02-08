package demo;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadDemo {

    static class ThreadSafetyDemo{
        static class ObjHolder{
            int value;
        }



        public static void main(String[] args) {
            ObjHolder objHolder = new ObjHolder();

            new Thread(()->{
                objHolder.value = 2;
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(objHolder.value);
            }).start();

            new Thread(()->{
                objHolder.value = 3;

            }).start();
        }
    }

    static class JoinDemo {

        static class MyThread extends Thread {


            @Override
            public void run() {
                synchronized (this) {
                    try {
                        for (int i = 0; i < 10; i++) {
                            TimeUnit.SECONDS.sleep(1);
                            log.info("{}", i);
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this) {
                    for (int i = 0; i < 10; i++) {
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        log.info("{}", i);
                    }
                }

            }

        }

        static class MultiThreadsCrashDemo {


            public static void main(String[] args) {
                new Thread(() -> {
                    throw new RuntimeException();
                }).start();
                new Thread(()->{
                    for (int i = 0; i < 10; i++) {
                        System.out.println(new Date());
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                }).start();
            }
        }

        public static void main(String[] args) throws InterruptedException {
            Thread thread = new MyThread();
            thread.start();

            thread.start();
        }
    }
}
