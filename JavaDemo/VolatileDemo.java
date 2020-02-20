

public class VolatileDemo{
	static int v = 0;
	static class CounterThread extends Thread{
		@Override
		public void run(){
			for(int i = 0; i < 1000; i++){
				//System.out.println(Thread.currentThread());
				v++;
			}
		}
	}
	public static void main(String[] args) {
		final int N = 3;
		Thread[] thread = new Thread[N];
		for(int i = 0; i < N; i++){
			thread[i] = new CounterThread();
			thread[i].start();
		}
		System.out.println("v=" + v);
	}
}