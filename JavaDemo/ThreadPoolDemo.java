import java.util.concurrent.*;

public class ThreadPoolDemo{
	public static void main(String[] args) {
		ThreadPoolExecutor threadPool = (ThreadPoolExecutor)Executors.newFixedThreadPool(2);
		for (int i=0; i<100; i++) {
			threadPool.execute(()->{System.out.println(Thread.currentThread());});
			
		}
		threadPool.shutdown();
	}
}