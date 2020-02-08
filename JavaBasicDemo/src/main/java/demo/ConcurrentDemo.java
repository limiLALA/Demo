package demo;


import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

class MyCallable implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread() + ":" + "call");

        Thread.sleep(1000);

        System.out.println(Thread.currentThread() + ":" + "done");
        return Thread.currentThread() + ":finished";
    }
}

class CallableDemo {
    private Callable<? extends Object> callable;

    public CallableDemo(Callable<String> callable) {
        this.callable = callable;

    }

    public void run() {
        try {
            this.callable.call();
        } catch (Exception e) {
            System.out.println(Thread.currentThread() + ":" + e);
        }
    }

    public static void main(String[] args) {
        new CallableDemo(new MyCallable()).run();
    }
}

class ThreadLocalCallable implements Callable<Integer> {

    static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();


    @Override
    public Integer call() throws Exception {
        threadLocal.set(0);

        for (int i = 0; i < 10; i++) {
            Integer integer = threadLocal.get();
            threadLocal.set(integer + 1);
            Thread.sleep(1000);
        }
        Integer integer = threadLocal.get();
        threadLocal.remove();
        return integer;
    }
}

class SubmitDemo {
    private Callable<Integer> callable;
    private ExecutorService thread_pool = Executors.newCachedThreadPool();
    private List<Future<String>> futures = new LinkedList<>();
//    private List<Callable> callables = new LinkedList();

    private Logger logger = Logger.getLogger(this.getClass().getName());

    public SubmitDemo() {
        callable = new ThreadLocalCallable();
        for (int i = 0; i < 10; i++) {

            Future future = this.thread_pool.submit(callable);
            futures.add(future);
        }
    }


    public void run() {
        for (Future future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        if (!this.thread_pool.isShutdown())this.thread_pool.shutdown();
    }


}

@Slf4j
class ExecuteDemo {
    private ExecutorService thread_pool = Executors.newCachedThreadPool();
    private final int thread_num = 10;
    private CountDownLatch countDownLatch = new CountDownLatch(thread_num);
    public void run()  {
        for (int i = 0; i < thread_num; i++) {
            thread_pool.execute(()->{
//                log.info("thread name:{} start",Thread.currentThread().getName());
                try {
                    Thread.sleep(Thread.currentThread().getId()%10*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    log.info("thread name:{} done",Thread.currentThread().getName());
                    countDownLatch.countDown();
                }

            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            thread_pool.shutdown();
        }

    }
}

public class ConcurrentDemo {
    public static void main(String[] args) {
        new ExecuteDemo().run();
    }
}
