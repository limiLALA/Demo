package demo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
//import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.tools.json.JSONReader;
import org.codehaus.jackson.map.ObjectMapper;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class Msg {
    String funcName;
    String arguments;

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public Msg() {
    }

    public Msg(String funcName, String arguments) {
        this.funcName = funcName;
        this.arguments = arguments;
    }
}
//
//class Send {
//    //队列名称
//    private final static String QUEUE_NAME = "hello";
//
//    public static void main(String[] argv) throws java.io.IOException {
//        /**
//         * 创建连接连接到MabbitMQ
//         */
//        ConnectionFactory factory = new ConnectionFactory();
//        //设置MabbitMQ所在主机ip或者主机名
//        factory.setHost("localhost");
//        //创建一个连接
//        Connection connection = factory.newConnection();
//        //创建一个频道
//        Channel channel = connection.createChannel();
//
//
//        //指定一个队列
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        //发送的消息
////        String message = "hello world!";
//
//        Msg msg = new Msg();
//        msg.setFuncName("featureExtract");
//        ObjectMapper objectMapper = new ObjectMapper();
//        String message = objectMapper.writeValueAsString(msg);
//
//        //往队列中发出一条消息
//        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
//        System.out.println(" [x] Sent '" + message + "'");
//        //关闭频道和连接
//        channel.close();
//        connection.close();
//    }
//}
//
//class Recv {
//    //队列名称
//    private final static String QUEUE_NAME = "hello";
//
//    public static void main(String[] argv) throws java.io.IOException,
//            java.lang.InterruptedException {
//        //打开连接和创建频道，与发送端一样
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost("localhost");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        //声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//        //创建队列消费者
//        QueueingConsumer consumer = new QueueingConsumer(channel);
//        //指定消费队列
//        channel.basicConsume(QUEUE_NAME, true, consumer);
//        JSONReader jsonReader = new JSONReader();
//        while (true) {
//            //nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
//            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
//            HashMap<String, String> message = (HashMap<String, String>) jsonReader.read(new String(delivery.getBody()));
//            System.out.println(" [x] Received '" + message + "'");
//            for (Iterator<Map.Entry<String, String>> iterator = message.entrySet().iterator(); iterator.hasNext(); ) {
//                Map.Entry<String, String> entry = iterator.next();
//                iterator.remove();
//                System.out.println(entry.getKey()+":"+entry.getValue());
//
//            }
//        }
//
//    }
//}

public class RabbimqDemo {
    public static void main(String[] args) {

    }
}
