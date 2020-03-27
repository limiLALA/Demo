/*泛型Demo*/
public class GenericityDemo{
	static class Fruit{}

	static class Apple extends Fruit{}

	static class GoodApple extends Apple{}

	static class Test<E>{
		void print(E o){
			System.out.println(o);
		}
	}

	public static void main(String[] args) {
		Fruit fruit = new Fruit();
		Apple apple = new Apple();
		GoodApple goodApple = new GoodApple();

		/*泛型上限extends：指向的Test对象的泛型只能是本类和子类
			这种方式只能用于获取指定范围内的对象引用，但是无法调用其中要传递泛型参数的方法，如print()
		*/
		// Test<? extends Apple> t;
		// t = new Test<Fruit>();//false
		// t = new Test<Apple>();//true
		// t = new Test<GoodApple>();//true
		// t.print(fruit);//false
		// t.print(apple);//false
		// t.print(goodApple);//false


		/*泛型下限super：指向的Test对象的泛型只能是本类和父类
			如果调用要传递泛型参数的方法，只能传递本类和子类对象的引用。
			父类引用可以指向子类对象，但是不能指向更高的父父类对象。
		*/
		// Test<? super Apple> t;
		// t = new Test<Fruit>();//true
		// t = new Test<Apple>();//true
		// // t = new Test<GoodApple>();//false
		// // t.print(fruit);//false
		// t.print(apple);//true
		// t.print(goodApple);//true
	}
}