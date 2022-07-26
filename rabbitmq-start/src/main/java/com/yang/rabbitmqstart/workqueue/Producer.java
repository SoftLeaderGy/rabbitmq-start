package com.yang.rabbitmqstart.workqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yang.rabbitmqstart.util.RabbitMQUtils;

import java.io.IOException;

/**
 * @Description: 工作队列模型-消息生产者
 * @Author: Guo.Yang
 * @Date: 2022/07/26/20:17
 */
public class Producer {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("work",true,false,false,null);

        // 生产消息
        for (int i = 0; i < 10; i++) {
            channel.basicPublish("","work",null, (i +"hello work queue").getBytes());
        }
        // 关闭连接
        RabbitMQUtils.closeConAndChanel(connection,channel);
    }
}
