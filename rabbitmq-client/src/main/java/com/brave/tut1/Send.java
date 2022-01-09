package com.brave.tut1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author: J-Brave
 * Date: 2022/1/9
 * Time: 21:57
 * Description:
 */
public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) {

        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setPort(5672);
            factory.setUsername("brave");
            factory.setPassword("Brave");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            //声明要发送的队列
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("[x] Sent ' " + message + "'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
