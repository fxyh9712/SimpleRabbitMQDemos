package com.fxyh.helloworld;

import com.fxyh.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class HelloWorldProducer {

    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws Exception {
        //建立到代理服务器到连接
        Connection conn = ConnectionUtil.getConnection();
        //获得信道
        Channel channel = conn.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //声明交换器
//        String exchangeName = "hello-exchange";
//        channel.exchangeDeclare(exchangeName, "direct", true);
//
//        String routingKey = "hola";
        for (int i = 0; i < 1; i++) {
            String message = "Hello World" + i + "!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }
        //发布消息
//        byte[] messageBodyBytes = "quit1".getBytes();
//        channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);

        channel.close();
        conn.close();
    }
}
