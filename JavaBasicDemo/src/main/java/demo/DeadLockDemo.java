package demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
class Resource {
    synchronized void synchronizedWork1(Resource resource) {
        log.info("{}.synchronized work1", Thread.currentThread().getName());
        //使用对方资源完成第二步骤，死在这里
        resource.synchronizedWork2();
    }

    synchronized void synchronizedWork2() {
        log.info("{}.synchronized work2", Thread.currentThread().getName());
    }
}



public class DeadLockDemo {

    static class SynchronizedDeadLockDemo implements Runner{
        Resource resource1 = new Resource();
        Resource resource2 = new Resource();


        @Override
        public void run() {
            ExecutorService executorService = Executors.newCachedThreadPool();
            executorService.execute(()->{
                resource1.synchronizedWork1(resource2);

//            resource2.synchronizedWork2();
            });

            executorService.execute(()->{
                resource2.synchronizedWork1(resource1);
//            resource1.synchronizedWork1();
            });

            System.out.println("done");
            executorService.shutdown();
        }
    }

    public static void main(String[] args) {
        new SynchronizedDeadLockDemo().run();
    }

}
