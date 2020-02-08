package demo;



public class LoopDependency {
    static class A{
        B b;

        public A(B b) {
            this.b = b;
        }

        public A() {
            B b = new B(this);
        }
    }
    static class B{
        A a;

        public B(A a) {
            this.a = a;
        }

        public B() {
            A a = new A(this);
        }
    }

    public static void main(String[] args) {

    }
}
