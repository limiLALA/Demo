package demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ConditionDemo implements Runnable{
    private ReentrantLock reentrantLock = new ReentrantLock();
    Condition condition = reentrantLock.newCondition();

    int state = 0;

    public static void main(String[] args) {
        new ConditionDemo().run();
    }

    @Override
    public void run() {
        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    reentrantLock.lockInterruptibly();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try{

                    if (state==0){
                        log.info("state==0");
                        condition.await();
                    }
                    log.info("state--");
                    state--;
                    condition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }

        }).start();

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                try {
                    reentrantLock.lockInterruptibly();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {

                    if (state>0){
                        log.info("state>0");
                        condition.await();
                    }
                    log.info("state++");
                    state++;
                    condition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    reentrantLock.unlock();
                }
            }

        }).start();
    }


}
