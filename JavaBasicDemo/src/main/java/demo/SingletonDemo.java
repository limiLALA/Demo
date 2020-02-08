package demo;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;


@Slf4j
public class SingletonDemo {

    static class SingletonNotThreadSafe {

        private static SingletonNotThreadSafe singletonNotThreadSafe;

        private SingletonNotThreadSafe() {
            log.info(this.getClass().getName() + " constructor");
        }

        static SingletonNotThreadSafe getInstance() {
            if (singletonNotThreadSafe == null)
                singletonNotThreadSafe = new SingletonNotThreadSafe();
            return singletonNotThreadSafe;
        }
    }

    static class SingletonUsingStaticInnerClass {
        //使用静态内部类实现懒汉模式
        private SingletonUsingStaticInnerClass() {
            log.info("SingletonUsingStaticInnerClass construct");
        }


        static SingletonUsingStaticInnerClass getInstance() {
            return SingletonHolder.INSTANCE;
        }

        static class SingletonHolder {
            private static final SingletonUsingStaticInnerClass INSTANCE = new SingletonUsingStaticInnerClass();
            static {
                log.info("SingletonHolder construct");
            }
        }

        static int whatever;
    }

    static class SingletonUsingEnum{
        enum SingletonHolder{
            INSTANCE{
            };
            final SingletonUsingEnum singletonUsingEnum  = new SingletonUsingEnum();
            SingletonHolder() {
            }
        }


        private SingletonUsingEnum() {
            log.info("construct:{}",this.getClass().getName());
        }
        static SingletonUsingEnum getInstance(){
            return SingletonHolder.INSTANCE.singletonUsingEnum;
        }
        static int whatever = 0;
    }


    final static int REQUEST_NUM = 200;
    final static int CONCURRENT_NUM = 200;

    public static void main(String[] args) throws InterruptedException {
//        int a = SingletonUsingEnum.whatever;
//        Object object = SingletonUsingEnum.SingletonHolder.INSTANCE;
        CountDownLatch countDownLatch = new CountDownLatch(REQUEST_NUM);
        Semaphore semaphore = new Semaphore(CONCURRENT_NUM);
        ExecutorService threadPool = java.util.concurrent.Executors.newCachedThreadPool();
        for (int i = 0; i < REQUEST_NUM; i++) {
            threadPool.execute(() -> {
                try {
                    semaphore.acquire();
                    SingletonUsingStaticInnerClass singletonUsingStaticInnerClass = SingletonUsingStaticInnerClass.getInstance();
//                    SingletonNotThreadSafe singletonNotThreadSafe = SingletonNotThreadSafe.getInstance();
//                    SingletonUsingEnum singletonUsingEnum = SingletonUsingEnum.getInstance();
                    countDownLatch.countDown();
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await();
        threadPool.shutdown();

    }
}
