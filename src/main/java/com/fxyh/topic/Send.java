package com.fxyh.topic;

import com.fxyh.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

public class Send {
    private final static String EXCHANGE_NAME = "topic_exchange_test";

    public static void main(String[] args) throws Exception {
        //获取连接
        Connection connection = ConnectionUtil.getConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 声明exchange，指定类型为topic
        channel.exchangeDeclare(EXCHANGE_NAME, "topic",true);
        String message = "新增商品:id = 999";
        // 发送消息，并指定routing key为：insert，代表新增商品
        channel.basicPublish(EXCHANGE_NAME, "item.delete", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        System.out.println("[商品服务]：Send :" + message);
        channel.close();
        connection.close();
    }
}
