import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadSynchronizationDemo{
	static int v = 0;
	public static void main(String[] args) {
		final int N = 10;
		ThreadPoolExecutor executor = (ThreadPoolExecutor)Executors.newFixedThreadPool(N);
		Runnable task = new Runnable(){
			@Override
			public void run(){
				v++;
				//System.out.println(Thread.currentThread());
			}
		};
		for(int i = 0; i < 10000; i++)
			executor.execute(task);

		executor.shutdown();
		while(executor.isTerminated() == false);

		System.out.println("v=" + v);
	}
}