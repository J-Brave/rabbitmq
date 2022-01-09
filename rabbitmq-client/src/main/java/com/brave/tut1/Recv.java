package com.brave.tut1;

import com.brave.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;


/**
 * @author: J-Brave
 * Date: 2022/1/9
 * Time: 21:57
 * Description:
 */
public class Recv {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {

        try {
            Connection connection = ConnectionUtil.getConnection();
            if (connection == null) {
                return;
            }
            Channel channel = connection.createChannel();
            //消费者同样需要声明队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("[x] Received ' " + message + "'");
            };
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> {});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
