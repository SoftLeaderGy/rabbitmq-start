package com.yang.rabbitmqstart.directqueue;

import com.rabbitmq.client.*;
import com.yang.rabbitmqstart.util.RabbitMQUtils;

import java.io.IOException;

/**
 * @Description: 订阅模型消费者
 * @Author: Guo.Yang
 * @Date: 2022/08/02/22:39
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare("logs_direct","direct");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,"logs_direct","error");
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
    }
}
