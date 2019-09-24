package com.fxyh.work;

import com.fxyh.util.ConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;

public class WorkConsumer2 {
    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws Exception{
        //建立到代理服务器到连接
        Connection conn = ConnectionUtil.getConnection();
        //获得信道
        Channel channel = conn.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 定义队列的消费者

        DefaultConsumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                // body 即消息体
                String msg = new String(body);
                System.out.println(" [消费者2] received : " + msg + "!");
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 监听队列，第二个参数：是否自动进行消息确认。
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
