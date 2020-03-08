
import java.lang.reflect.*;

interface face {
	void fake();
}

abstract class aface implements face{
	public abstract void fake();
}

class MyAface extends aface{
	public void fake(){
		System.out.println("MyAface");
	}
}

public class ReflectDemo{
	public static void main(String[] args) throws Exception {
		InvokeDemo invokeDemo1 = new InvokeDemo();
		InvokeDemo invokeDemo2 = new InvokeDemo();
		//invokeDemo2.aa = 666;
		Class cla = invokeDemo1.getClass();
		Class cla2 = invokeDemo2.getClass();
		System.out.println(cla==cla2);

		//System.out.println("invokeDemo1.aa:"+invokeDemo1.aa);
		// System.out.println("InvokeDemo.b:"+InvokeDemo.b);
		// System.out.println("--------------");
		// Field faa = cla.getDeclaredField("aa");
		Field fa = cla.getDeclaredField("a");
		try{
			// System.out.println("before: faa.get(invokeDemo2):"+faa.get(invokeDemo2));
			System.out.println("before: fa.get(invokeDemo1):"+fa.get(invokeDemo1));
		}catch(Exception e){}
		invokeDemo1.a = 777;
		// faa.setAccessible(true);
		// System.out.println("after: faa.get(invokeDemo2):"+faa.get(invokeDemo2));
		System.out.println("after: fa.get(invokeDemo2):"+fa.get(invokeDemo2));
		
		// Class cla_face = aface.class;
		// Method m_face = cla_face.getDeclaredMethod("fake");
		// // System.out.println(m);
		// // Object f = cla_face.newInstance();
		// //m.invoke(f);

		// Class cla = MyAface.class;
		// Method m = cla.getDeclaredMethod("fake");

		// System.out.println("m_face == m? "+(m_face == m));
		// System.out.println(m_face);
		// System.out.println(m);
		// Object o = cla.newInstance();
		// MyAface myAface = new MyAface();
		// myAface.fake();
		// System.out.println("Is o an instance of MyAface? "+cla.isInstance(o));
		// System.out.println("Is myAface an instance of MyAface? "+cla.isInstance(myAface));
	}
}