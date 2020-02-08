package demo;

import java.io.*;

public class SerializedDemo {

    static class JDKSerializedDemo{

        class B implements Serializable{

        }


        class A implements Serializable {
           // B b = new B();
        }


        void solve() throws IOException {
            File file = new File("tmp");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            A a = new A();
            objectOutputStream.writeObject(a);

        }

        public static void main(String[] args) throws IOException {
            new JDKSerializedDemo().solve();


        }
    }
}
