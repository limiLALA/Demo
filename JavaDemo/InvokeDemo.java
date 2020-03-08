public class InvokeDemo{
	static int a = 3;
	static final int b = 2;
	static{
		a = 1;
	}
	private int aa = 3;
	private Object o;
	public static void main(String[] args) {
		a = 2;
	}

	public void print(String arg){
		System.out.println(arg);
	}
}