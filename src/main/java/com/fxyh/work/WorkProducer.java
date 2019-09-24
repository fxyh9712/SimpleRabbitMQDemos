package com.fxyh.work;

import com.fxyh.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class WorkProducer {

    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws Exception {
        //建立到代理服务器到连接
        Connection conn = ConnectionUtil.getConnection();
        //获得信道
        Channel channel = conn.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for (int i = 0; i < 50; i++) {
            String message = "Hello World" + i + "!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }

        channel.close();
        conn.close();
    }

}
