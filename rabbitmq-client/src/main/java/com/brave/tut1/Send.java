package com.brave.tut1;

import com.brave.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

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
            Connection connection = ConnectionUtil.getConnection();
            if (connection == null) {
                return;
            }
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
