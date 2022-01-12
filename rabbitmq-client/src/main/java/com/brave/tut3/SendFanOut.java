package com.brave.tut3;

import com.brave.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;

/**
 * @author: J-Brave
 * Date: 2022/1/12
 * Time: 10:40
 * Description:
 */
public class SendFanOut {

    private static final String EXCHANGE_NAME = "exchange.fan.out";

    private static final String QUEUE_NAME = "queue.fan.out";

    public static void main(String[] args) {

        try {
            Connection connection = ConnectionUtil.getConnection();
            assert connection != null;
            Channel channel = connection.createChannel();
            //声明FanOut类型的交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

            //声明队列 无参数声明, 可获得临时、独占、非持久的队列
//            String queueName = channel.queueDeclare().getQueue();

            //声明队列 开启持久化
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = "Hello World FanOut!";

            //声明队列与交换机的绑定关系
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

            //发送消息到交换机
            channel.basicPublish(EXCHANGE_NAME,"", MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println(" [x] sent: '" + message + "'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
