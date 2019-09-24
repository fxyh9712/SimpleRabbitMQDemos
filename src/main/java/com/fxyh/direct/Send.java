package com.fxyh.direct;

import com.fxyh.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Send {
    private final static String EXCHANGE_NAME = "direct_exchange_test";

    public static void main(String[] args) throws Exception {
        // 获取到连接
        Connection connection = ConnectionUtil.getConnection();
        //获取通道
        Channel channel = connection.createChannel();
        //声明exchange，指定为direct类型
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        String message = "商品新增了， id = 1000";
        // 发送消息，并指定routing key 为：insert，代表新增商品
        channel.basicPublish(EXCHANGE_NAME, "delete", null, message.getBytes());
        System.out.println("[商品服务：] Sent :" + message);
        channel.close();
        connection.close();
    }
}
