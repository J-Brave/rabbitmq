package com.brave.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {

    /**
     * 连接工具类
     *
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        //创建连接对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //指定连接地址
        connectionFactory.setHost("localhost");
        //指定端口号
        connectionFactory.setPort(5672);
        //设置虚拟机 一个MQ可以设置多个虚拟机，每个虚拟机相当于一个独立的MQ
        connectionFactory.setVirtualHost("/brave");
        //设置账号
        connectionFactory.setUsername("brave");
        //设置密码
        connectionFactory.setPassword("root");
        //创建连接
        return connectionFactory.newConnection();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = getConnection();
        System.out.println("获取连接: " + connection.toString());
    }

}
