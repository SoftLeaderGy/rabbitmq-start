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
     * 实现ConnectionFactory 对象的单例模式，在类加载的时候生成ConnectionFactory对象
     * 并且将对应的连接信息设置好
     */
    private static ConnectionFactory connectionFactory;

    static {
        // 因为ConnectionFactory 属于重量级资源，在整个项目中出现一个对象就够了（实现ConnectionFactory 对象的单例）
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/msg");
        connectionFactory.setUsername("msg");
        connectionFactory.setPassword("123");
    }

    /**
     * 获取MQ连接的工具方法
     * @return
     */
    public static Connection getConnection(){
        try {
            return connectionFactory.newConnection();
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
