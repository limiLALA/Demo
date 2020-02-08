package demo;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.*;

class Obj implements Serializable {

}

class FileIODemo {


    public static void main(String[] args) throws IOException {
//        FileInputStream fileInputStream = new FileInputStream("./in.txt");
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("./out.txt");
                PrintWriter printWriter = new PrintWriter(fileOutputStream);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        ) {
            objectOutputStream.writeObject(new Obj());
            objectOutputStream.flush();
        }

        try (
                FileWriter fileWriter = new FileWriter("./out.txt");
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        ) {
        }

    }
}

class BufferedReaderDemo {
    private BufferedReader bufferedReader;

    public BufferedReaderDemo() {
        //default for system standard input stream
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public BufferedReaderDemo(InputStream inputStream) {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }


    public BufferedReaderDemo(InputStreamReader inputStreamReader) {
        this.bufferedReader = new BufferedReader(inputStreamReader);
    }

    public void run() {
        System.out.println(Thread.currentThread() + ":start");
        try {
            this.bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println(Thread.currentThread() + ":" + e);
        }
        System.out.println(Thread.currentThread() + ":done");
    }

    public static void main(String[] args) {
        new Thread(
                () -> {
                    new BufferedReaderDemo().run();
                }
        ).start();

        new Thread(
                () -> {
                    try {
                        new BufferedReaderDemo(new FileReader("in.txt")).run();
                    } catch (FileNotFoundException e) {
                        System.out.println(Thread.currentThread() + ":" + e);
                    }
                }
        ).start();
    }
}


public class IODemo {

    static class BufferedByteStreamDemo{

    }

    static class ByteStreamDemo{
        public static void main(String[] args) throws IOException {
            byte[] in = new byte[128];
            for (int i = 0; i < in.length; i++) {
                in[i] = (byte) i;
            }
//            byte[] out = new byte[1024];
            InputStream inputStream = new ByteInputStream(in, in.length);
            OutputStream outputStream = new ByteOutputStream(in.length);
            byte[] tmp = new byte[32];
            int ret = 0;
            while ((ret=inputStream.read(tmp))>0){
                outputStream.write(tmp);
            }
            System.out.println(outputStream.toString());

        }
    }

    static class CharacterStreamDemo{

    }

    static class BufferedCharacterStreamDemo{

    }


    public static void main(String[] args) {


    }
}
