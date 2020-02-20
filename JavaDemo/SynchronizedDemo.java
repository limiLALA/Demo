public class SynchronizedDemo{
	static Integer integer = new Integer(0);
	static class CounterThread extends Thread{
		@Override
		public void run(){
			for(int i = 0; i < 1; i++){
				synchronized(integer){
					System.out.println(Thread.currentThread());
					try{
						Thread.sleep(3000);
					}catch(InterruptedException ex){
						String msg = ex.getMessage();
						System.out.println(msg);
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		Thread a = new CounterThread();// 
		Thread b = new CounterThread();
		Thread c = new CounterThread();
		Thread d = new CounterThread();
		Thread e = new CounterThread();
		a.start();
		try{
			Thread.sleep(500);
		}catch(InterruptedException ex){
			String msg = ex.getMessage();
			System.out.println(msg);
		}
		b.start();
		try{
			Thread.sleep(500);
		}catch(InterruptedException ex){
			String msg = ex.getMessage();
			System.out.println(msg);
		}
		c.start();
		try{
			Thread.sleep(500);
		}catch(InterruptedException ex){
			String msg = ex.getMessage();
			System.out.println(msg);
		}
		d.start();
		try{
			Thread.sleep(500);
		}catch(InterruptedException ex){
			String msg = ex.getMessage();
			System.out.println(msg);
		}
		e.start();
	}
}