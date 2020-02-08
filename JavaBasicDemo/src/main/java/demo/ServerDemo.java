package demo;


import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


class RequestHandler implements Runnable {
    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        System.out.println(Thread.currentThread() + ":start");
        try {

            this.socket.getOutputStream().write("hello".getBytes());
            Thread.sleep(1000);
        } catch (IOException | InterruptedException e) {
            System.out.println(Thread.currentThread() + ": " + e.toString());
        } finally {
            try {
                this.socket.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println(Thread.currentThread() + ":done");
    }
}


class BIOServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);

        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("ip: " + socket.getInetAddress() + "port: " + socket.getPort());
                new Thread(new RequestHandler(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }


    }
}

class TheadPoolBIOServerDemo {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8088);
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("ip: " + socket.getInetAddress() + "port: " + socket.getPort());
                threadPool.submit(new RequestHandler(socket));
//                threadPool.execute(new RequestHandler(socket));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
        }

    }
}

class EpollServerDemo {

    private static ByteBuffer read_buffer = ByteBuffer.allocate(1024);
    private static ByteBuffer write_buffer = ByteBuffer.allocate(1024);
    private static ByteBuffer buffer = ByteBuffer.allocate(1024);
    private Selector selector;

    private void handleReadable(SelectionKey key) throws IOException {
        System.out.println(Thread.currentThread() + ":" + "read");
        SocketChannel socketChannel = (SocketChannel) key.channel();
        System.out.println("from: " + socketChannel.getRemoteAddress());
        if (!socketChannel.isConnected()){
            socketChannel.close();
            return;
        }
        int count = 0;

        while (true) {
            buffer.clear();
            count = socketChannel.read(buffer);
            if (count <= 0) break;

            String msg = new String(buffer.array(), 0, count);
            System.out.println(msg);


        }

        buffer.flip();
        socketChannel.register(selector, SelectionKey.OP_WRITE);

    }

    private void handleWriteable(SelectionKey key, String s) throws IOException {
        System.out.println(Thread.currentThread() + ":" + "write");
        SocketChannel socketChannel = (SocketChannel) key.channel();
        System.out.println("to: " + socketChannel.getRemoteAddress());

        String msg = "hello " + new Random().nextInt(100);
        System.out.println(msg);
        buffer.clear();
        buffer.put(msg.getBytes());

        socketChannel.write(buffer);
//        buffer.flip();
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void handleAcceptable(SelectionKey key, ServerSocketChannel serverSocketChannel) throws IOException {
        System.out.println(Thread.currentThread() + ":" + "accept");
        SocketChannel socketChannel = serverSocketChannel.accept();
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }


    public void run() throws IOException {
        try (Selector selector = Selector.open();
             ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();) {
            this.selector = selector;
            serverSocketChannel.bind(new InetSocketAddress(8088));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    iter.remove();
                    if (!key.isValid()) continue;
                    if (key.isAcceptable()) {
                        System.out.println("acceptable");
                        handleAcceptable(key, serverSocketChannel);
                    } else if (key.isReadable()) {
                        System.out.println("readable");
                        handleReadable(key);
                    } else if (key.isWritable()) {
                        System.out.println("writable");
                        handleWriteable(key, "");
                    }

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new EpollServerDemo().run();


    }
}

class ClientDemo {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket clientSocket = new Socket("localhost", 8088);
        System.out.println(clientSocket.getLocalAddress()+":"+clientSocket.getLocalPort());
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        InputStream inputStream = clientSocket.getInputStream();
        bufferedWriter.write("asd");

        String msg = "asd";
        byte[] buffer = new byte[1024];
        while (true){
            if (!clientSocket.isConnected()){
                break;
            }
            clientSocket.getOutputStream().write(msg.getBytes());

            Thread.sleep(2000);
            int count = inputStream.read(buffer);
            if (count<=0)
                break;
            System.out.println(new String(buffer));
        }
        clientSocket.close();
//        clientSocket.getOutputStream().write(msg.getBytes());
//        System.out.println("to " + clientSocket.getRemoteSocketAddress() + ":" + msg);
//        System.out.println(bufferedReader.readLine());
//        clientSocket.close();
    }
}

class MultiRequestDemo {
    public static void main(String[] args) throws IOException {
        int n = 11;
        for (int i = 0; i < n; i++) {
            new Thread(() -> {
                try {
                    ClientDemo.main(args);
                } catch (IOException e) {
                    System.out.println(Thread.currentThread() + ":" + e);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}

public class ServerDemo {

    public static void main(String[] args) {
    }


}
