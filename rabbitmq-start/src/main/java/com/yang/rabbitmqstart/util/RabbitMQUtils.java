package com.yang.rabbitmqstart.util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Description: RabbitMQ工具类
 * @Author: Guo.Yang
 * @Date: 2022/07/24/16:23
 */
public class RabbitMQUtils {

    /**
     * 获取MQ连接的工具方法
     * @return
     */
    public static Connection getConnection(){
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            connectionFactory.setHost("localhost");
            connectionFactory.setPort(5672);
            connectionFactory.setVirtualHost("/msg");
            connectionFactory.setUsername("msg");
            connectionFactory.setPassword("123");
            Connection connection = connectionFactory.newConnection();
            return connection;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 关闭连接、通道的工具方法
     * @param connection
     * @param channel
     */
    public static void closeConAndChanel(Connection connection, Channel channel){
        try {
            if(channel != null) channel.close();
            if(connection != null) connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
