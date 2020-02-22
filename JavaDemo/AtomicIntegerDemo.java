import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSynchronizationDemo{
	static Integer integer = new Integer(0);
	static AtomicInteger ai = new AtomicInteger(0);
	public static void main(String[] args) {
		final int N = 10;
		ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(N);
		Runnable task1 = new Runnable(){
			@Override
			public void run(){
				integer++;
				//System.out.println(Thread.currentThread());
			}
		};
		Runnable task2 = new Runnable(){
			@Override
			public void run(){
				ai.incrementAndGet();
				//System.out.println(Thread.currentThread());
			}
		};
		for(int i = 0; i < 10000; i++){
			executor.execute(task1);
			executor.execute(task2);
		}

		executor.shutdown();
		while(executor.isTerminated() == false);

		System.out.println("integer=" + integer);
		System.out.println("ai=" + ai);
	}
}