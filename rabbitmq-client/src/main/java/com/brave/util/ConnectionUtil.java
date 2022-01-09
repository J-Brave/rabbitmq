package com.brave.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author: J-Brave
 * Date: 2022/1/9
 * Time: 22:58
 * Description:
 */
public class ConnectionUtil {

    /**
    * @Author J-Brave
    * @Description 获得连接对象
    * @Date 23:02 2022/1/9
    * @Param
    * @return
    **/
    public static Connection getConnection() {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("localhost");
            factory.setPort(5672);
            factory.setUsername("brave");
            factory.setPassword("Brave");
            return factory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
