import java.util.*;
import java.util.concurrent.locks.*;
import java.lang.*;

public class ReentrantLockDemo{
	static Lock lock = new ReentrantLock(true);
	final static int N = 10;
	static class CounterThread extends Thread{
		@Override
		public void run(){
			for (int i=0; i<N; i++) {
				lock.lock();
				try{
					Thread.sleep(i*1000);
				}catch(InterruptedException e){
					String msg = e.getMessage();
					System.out.println(msg);
				}
				
				System.out.println(Thread.currentThread());
				lock.unlock();	
			}
			
		}
	}
	public static void main(String[] args) {
		Thread a = new CounterThread();
		Thread b = new CounterThread();
		a.start();
		b.start();
	}
}