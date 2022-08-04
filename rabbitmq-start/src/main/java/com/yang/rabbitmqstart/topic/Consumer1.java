package com.yang.rabbitmqstart.topic;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.yang.rabbitmqstart.util.RabbitMQUtils;

import java.io.IOException;

/**
 * @Description: topic模型的消费者
 * @Author: Guo.Yang
 * @Date: 2022/08/03/21:22
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtils.getConnection().createChannel();
        String exchangeName = "topics";
        String routeKey = "user.*";
        channel.exchangeDeclare(exchangeName,"topic");
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,exchangeName,routeKey);
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });
    }
}
