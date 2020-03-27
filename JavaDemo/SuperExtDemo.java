public class SuperExtDemo{
	static class Parent{
		static int sv = 0;
		final int fv = 1;
		int iv = 2;
		private int piv = 3;

		static void sfunc(){
			System.out.print("Parent's static func.");
		}

		final void ffunc(){
			System.out.print("Parent's final func.");
		}

		void dfunc(){
			System.out.print("Parent's dynamic func.");
		}

		private void pfunc(){
			System.out.print("Parent's private func.");
		}

		int getPiv(){
			return piv;
		}

		void setPiv(int v){
			piv = v;
		}
	}

	static class Child extends Parent{
		// static int sv;
		// final int fv;
		// int iv;
		// private int piv;

		// static void sfunc(){
		// 	System.out.print("Child's static func.");
		// }

		// final void ffunc(){
		// 	System.out.print("Child's final func.");
		// }

		// void dfunc(){
		// 	System.out.print("Child's dynamic func.");
		// }

		// private void pfunc(){
		// 	System.out.print("Child's private func.");
		// }
	}

	public static void main(String[] args) {
		Child c = new Child();
		c.setPiv(5);
		System.out.println(c.getPiv());
		// Parent p = c;
		// p.piv = 4;
		
	}
}