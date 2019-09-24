package com.fxyh.topic;

import com.fxyh.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class Recv2 {
    private final static String QUEUE_NAME = "topic_exchange_queue_2";
    private final static String EXCHANGE_NAME = "topic_exchange_test";

    public static void main(String[] args) throws Exception {
        // 获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 绑定队列到交换机，同时指定需要订阅的routing key。 需要 update，delete，insert
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "item.*");

        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body);
                System.out.println("[消费者2]： received: " + msg + "! routing key:" + envelope.getRoutingKey());
            }
        };
        // 监听队列， 自动ack
        channel.basicConsume(QUEUE_NAME, consumer);
    }
}
