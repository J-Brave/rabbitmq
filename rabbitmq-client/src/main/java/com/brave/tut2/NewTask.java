package com.brave.tut2;

import com.brave.util.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author: J-Brave
 * Date: 2022/1/11
 * Time: 21:57
 * Description:
 */
public class NewTask {

    private static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) {

        try {
            Connection connection = ConnectionUtil.getConnection();
            assert connection != null;
            Channel channel = connection.createChannel();
            //声明队列持久化
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
            String message = String.join(" ", args);
            //声明消息持久化
            channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] sent '" + message + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
