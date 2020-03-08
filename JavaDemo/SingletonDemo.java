import java.util.*;
import java.util.concurrent.*;

class Singleton{
	volatile int val;

/*懒汉式-线程不安全*/
	// private static Singleton singleton;
	// private Singleton(){}
	// public static Singleton getInstance(){
	// 	if(singleton == null){
	// 		System.out.println(Thread.currentThread().getName()+" come here to new Singleton!");
	// 		singleton = new Singleton();
	// 	}
	// 	return singleton;
	// }

/*饿汉式-线程安全*/
	// private static Singleton singleton = new Singleton();
	// private Singleton(){}
	// public static Singleton getInstance(){
	// 	return singleton;
	// }

/*懒汉式-线程安全*/
	// private static Singleton singleton;
	// private Singleton(){}
	// public static synchronized Singleton getInstance(){
	// 	if(singleton == null){
	// 		System.out.println(Thread.currentThread().getName()+" come here to new Singleton!");
	// 		singleton = new Singleton();
	// 	}
	// 	return singleton;
	// }

/*双重校验锁-线程安全*/
	// private static Singleton singleton;
	// private Singleton(){}
	// public static Singleton getInstance(){
	// 	if(singleton == null){
	// 		System.out.println(Thread.currentThread().getName()+" come here first!");
	// 		synchronized(Singleton.class){
	// 			if(singleton == null){
	// 				System.out.println(Thread.currentThread().getName()+" come here second!");
	// 				singleton = new Singleton();
	// 			}
	// 		}
	// 	}
	// 	return singleton;
	// }

/*静态内部类-线程安全*/
	// private Singleton(){}
	// private static class SingletonHolder{
	// 	private static final Singleton INSTANCE = new Singleton();;
	// }
	// public static Singleton getInstance(){
	// 	return SingletonHolder.INSTANCE;
	// }

/*乐观锁-线程安全*/
	// private static Singleton[] singleton = new singleton[1];
	// private static Class<?> ak = Singleton[].class;
	// private static final sun.misc.Unsafe U = sun.misc.Unsafe.getUnsafe();
	// private static final long ABASE = U.arrayBaseOffset(ak);
	// private Singleton(){}
	// public static Singleton getInstance(){
	// 	if(singleton[0] == null){
	// 		System.out.println(Thread.currentThread().getName()+" come here to new Singleton!");
	// 		// 警告: Unsafe是内部专用 API, 可能会在未来发行版中删除
	// 		U.compareAndSwapObject(U.compareAndSwapObject(singleton, ABASE, null, new Singleton()));
	// 	}
	// 	return singleton[0];
	// }
}

/*枚举实现-线程安全*/
enum Singleton{
	INSTANCE;
	volatile int val;
	public static Singleton getInstance(){
		return INSTANCE;
	}
}

public class SingletonDemo{
	static class Task extends Thread{
		@Override
		public void run(){
			Singleton s = Singleton.getInstance();
			// for(int i = 0; i < 1000; i++){
			// 	synchronized(Singleton.class){
					s.val++;
			// 	}
			// }
			System.out.println(Thread.currentThread().getName()+": s.val="+s.val);
			try{
				Thread.sleep(1000);
			}catch(Exception e){}
		}
	}

	public static void main(String[] args) throws Exception{
		int taskNum = 4;
		Task[] task = new Task[taskNum];
		for(int i = 0; i < taskNum; i++){
			task[i] = new Task();
			task[i].start();
			task[i].join();
		}
		Thread.sleep(1000);
		Singleton s = Singleton.getInstance();
		System.out.println(Thread.currentThread().getName()+": s.val="+s.val);
	}
}
