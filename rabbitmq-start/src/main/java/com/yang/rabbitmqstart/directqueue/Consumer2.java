package com.yang.rabbitmqstart.directqueue;

import com.rabbitmq.client.*;
import com.yang.rabbitmqstart.util.RabbitMQUtils;

import java.io.IOException;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/08/02/22:57
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs_direct","direct");
        // 获取临时队列名称
        String queue = channel.queueDeclare().getQueue();
        // 绑定队列和交换机，和要消费的路由键
        channel.queueBind(queue,"logs_direct","info");
        channel.queueBind(queue,"logs_direct","error");
        // 消费
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
    }
}
