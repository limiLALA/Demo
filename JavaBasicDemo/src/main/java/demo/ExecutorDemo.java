package demo;

import com.sun.javafx.scene.web.Debugger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorDemo {

    static class ExecutorSubmitDemo{
        public static void main(String[] args) throws ExecutionException, InterruptedException {
            ExecutorService threadPoolExecutor =  Executors.newSingleThreadExecutor();
            Future<?> future = threadPoolExecutor.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    return "done";
                }
            });
            Object object = future.get();
            System.out.println(object.toString());
        }
    }

    static class DaemonTheadPoolExecutorDemo{

        final Logger logger = LoggerFactory.getLogger(this.getClass());

        final static PrintStream out = System.out;

        void demo(){
            ExecutorService executorService = Executors.newFixedThreadPool(10, new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    Thread worker = new Thread(r);
                    worker.setDaemon(true);
                    return worker;
                }
            });

            for (int i = 0; i < 100; i++) {
                final int tmpi = i;
                executorService.execute(new Runnable() {

                    @Override
                    public void run() {
//                        logger.info(Thread.currentThread().toString());

                        out.println(Thread.currentThread()+":"+tmpi);
                    }
                });
            }
        }

        public static void main(String[] args) {
            new DaemonTheadPoolExecutorDemo().demo();
        }
    }


    public static void main(String[] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);

        List<String> data = new ArrayList<>();
        data.add("a");
        data.add("b");
        data.add("c");
        data.add("d");

 /*       executorService.execute(() -> {
            data.forEach(d -> {
                System.out.println(d);
                if ("b".equals(d)) {
                    throw new RuntimeException("业务出错");
                }
            });
        });*/
        data.forEach(d -> {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread()+":"+d);
                if ("b".equals(d)) {
                    throw new RuntimeException("业务出错"+d);
                }
            });
        });

    }
}
