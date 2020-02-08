package demo;

import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class TimerDemo {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("run");
            }
        }, 3000, 1000);
    }
}

public class ThreadPoolDemo {
    static class ExecutorServiceDemo {
        public static void main(String[] args) {
            ExecutorService threadPool = Executors.newFixedThreadPool(5);
            Class<ExecutorService> clazz = (Class<ExecutorService>) threadPool.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                try {
                    field.setAccessible(true);
                    System.out.println(field.getName()+":"+field.get(threadPool));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
//            for (int i = 0; i < 5; i++) {
//                threadPool.execute(()->{
//                    Scanner sc = new Scanner(System.in);
//                    System.out.println(sc.nextInt());
//                });
//            }
        }
    }

    class ScheduleExcutorServiceDemo {

    }


}
