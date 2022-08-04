package com.yang.rabbitmqstart.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yang.rabbitmqstart.util.RabbitMQUtils;

import java.io.IOException;

/**
 * @Description: topic模型的生产者
 * @Author: Guo.Yang
 * @Date: 2022/08/03/21:15
 */
public class Producer {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        String exchangeName = "topics";
        String routeKey = "user.save.qwe";
        channel.exchangeDeclare(exchangeName,"topic");
        channel.basicPublish(exchangeName,routeKey,null,("这是topic模型发布的消息，路由键为：「" + routeKey + "」").getBytes());
        RabbitMQUtils.closeConAndChanel(connection,channel);
    }
}
