package demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class SemaphoreDemo {
    private static final int concurrent_num = 20;



    public static void main(String[] args) {
        int permit = 3;
        final Semaphore semaphore = new Semaphore(permit);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < concurrent_num; i++) {
            executorService.execute(()->{
                try {
                    semaphore.acquire();
                    log.info("{}:run",Thread.currentThread().getName());
                    Thread.sleep(2000);
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
        }
        executorService.shutdown();

    }
}
