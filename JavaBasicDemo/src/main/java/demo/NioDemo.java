package demo;

import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class NioDemo {


    static class NioClient implements Runnable {

        public static void main(String[] args) {
            new NioClient().run();
        }

        @Override
        public void run() {

        }
    }

    static class BlockingNioDemo {
        static class BlockingNioServer {

            public static void main(String[] args) throws IOException {
                new BlockingNioServer().run();
            }

            @Test
            void run() throws IOException {
                ServerSocketChannel serverSocketChannel = null;
                SocketChannel socketChannel = null;
                try {

                    serverSocketChannel = ServerSocketChannel.open();
                    serverSocketChannel.bind(new InetSocketAddress("localhost", 9898));
                    // 回显服务


                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    socketChannel = serverSocketChannel.accept();
                    int len = 0;
                    while ((len = socketChannel.read(buffer)) > 0) {
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.flip();
                        socketChannel.write(buffer);
                        buffer.clear();
                    }

                } catch (Exception e) {

                } finally {
                    socketChannel.close();
                    serverSocketChannel.close();
                    System.out.println("over");
                }

            }


        }

        static class BlockingNioClient {

            public static void main(String[] args) throws IOException {
                new BlockingNioClient().run();
            }

            @Test
            void run() throws IOException {
                SocketChannel socketChannel = null;

                try {
                    socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9898));
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
//                    FileChannel in = FileChannel.open("")
//                buffer.put("hello".getBytes());
//                byte[] line = new byte[1024];
                    int len = 0;
                    String line;
                    Scanner sc = new Scanner(System.in);
                    line = sc.nextLine();
                    buffer.put(line.getBytes());
                    while ((len = socketChannel.write(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
//                    buffer.get(line);
//                    System.out.println(new String(line));

                        buffer.clear();
                        line = sc.nextLine();
                        buffer.put(line.getBytes());
                    }

//                while ((len=socketChannel.read(buffer))>0){
//                    System.out.println(new String(buffer.array()));
//                }

                } catch (Exception e) {
                } finally {
                    System.out.println("over");
                    socketChannel.close();
                }


            }
        }
    }

    // IO 复用 demo
    static class NonBlockingNioDemo {
        static class NonBlockingNioServer implements Runnable {
            public static void main(String[] args) {
                new NonBlockingNioServer().run();
            }

            @Override
            public void run() {
                try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
                     Selector selector = Selector.open();
                ) {
                    serverSocketChannel.bind(new InetSocketAddress("localhost", 9898));
                    serverSocketChannel.configureBlocking(false);
                    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                    while (true) {
                        selector.select();
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        for (Iterator<SelectionKey> iter = selectionKeys.iterator(); iter.hasNext(); ) {
                            SelectionKey key = iter.next();
                            iter.remove();
                            if (key.isAcceptable()) {
                                ServerSocketChannel tmpChannel = (ServerSocketChannel) key.channel();
                                SocketChannel socketChannel = tmpChannel.accept();
                                socketChannel.configureBlocking(false);
                                socketChannel.register(selector, SelectionKey.OP_READ);


                            } else if (key.isReadable()) {
                                System.out.println("read");
                                SocketChannel socketChannel = (SocketChannel) key.channel();
                                ByteBuffer buffer = ByteBuffer.allocate(10);
                                int len = 0;
                                while ((len = socketChannel.read(buffer)) > 0) {
                                    buffer.flip();
                                    System.out.println(new String(buffer.array(), 0, len));
                                    socketChannel.write(buffer);
                                    buffer.clear();


                                }


                            } else if (key.isWritable()) {
//                                SocketChannel socketChannel = (SocketChannel) key.channel();

                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        static class NonBlockingNioClient implements Runnable {
            public static void main(String[] args) throws IOException {
                new NonBlockingNioClient().run();
            }

            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                try (SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 9898))) {
                    String line = scanner.nextLine();
                    buffer.put(line.getBytes());
                    buffer.flip();
                    socketChannel.write(buffer);
                    buffer.clear();
                    int len = 0;
                    while ((len = socketChannel.read(buffer)) > 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                        buffer.clear();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 异步 demo
    static class AsynchronousNioDemo {
        static class AsynchronousNioServer implements Runnable {

            public static void main(String[] args) {
                new AsynchronousNioServer().run();
            }

            @Override
            public void run() {
                try (AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();
                ) {
                    serverSocketChannel.bind(new InetSocketAddress(InetAddress.getLocalHost(), 9898));
                    serverSocketChannel.accept(serverSocketChannel, new CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel>() {
                        @Override
                        public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel attachment) {

                        }

                        @Override
                        public void failed(Throwable exc, AsynchronousServerSocketChannel attachment) {

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        static class AsynchronousNioClient {

        }
    }

    void EchoHandle(SocketChannel socketChannel){
        ByteBuffer buffer = ByteBuffer.allocate(10);
        int len = 0;
        try{
            while ((len=socketChannel.read(buffer))>0){
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

    }

}
