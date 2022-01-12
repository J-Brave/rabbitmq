package com.brave.tut3;

import com.brave.util.ConnectionUtil;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author: J-Brave
 * Date: 2022/1/12
 * Time: 11:01
 * Description:
 */
public class RecvFanOut {

    private static final String EXCHANGE_NAME = "exchange.fan.out";

    private static final String QUEUE_NAME = "queue.fan.out";

    public static void main(String[] args) {

        try {
            Channel channel = ConnectionUtil.getConnection().createChannel();
            //声明交换机
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);

            //声明队列
            channel.queueDeclare(QUEUE_NAME,true, false, false, null);

            //声明绑定关系
            channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

            //接收消息
            DeliverCallback deliverCallback = (customerTag, deliver) -> {
                String message = new String(deliver.getBody(), StandardCharsets.UTF_8);
                System.out.println(" [x] Received: '" + message + "'");
            };

            //自动Ack
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTab ->{});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
