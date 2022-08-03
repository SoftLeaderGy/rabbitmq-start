package com.yang.rabbitmqstart.directqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yang.rabbitmqstart.util.RabbitMQUtils;

import java.io.IOException;

/**
 * @Description: 订阅模型生产者
 * @Author: Guo.Yang
 * @Date: 2022/08/02/21:40
 */
public class Producer {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "logs_direct";
        // 声名交换机
        channel.exchangeDeclare(exchangeName,"direct");
        String routeKey = "info";
        // 发送消息 到交换机、需要携带路由键
        // 参数1：交换机名称、消息携带的路由键、消息的基础配置、消息的具体内容
        channel.basicPublish(exchangeName,routeKey,null,("这个是direct模型生产的消息，路由键为 「"+ routeKey + "」").getBytes());
        RabbitMQUtils.closeConAndChanel(connection,channel);
    }
}
