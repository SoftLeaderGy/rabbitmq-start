package com.yang.rabbitmqstart.fanoutqueue;

import com.rabbitmq.client.*;
import com.yang.rabbitmqstart.util.RabbitMQUtils;

import java.io.IOException;

/**
 * @Description: 广播模型-消费者
 * @Author: Guo.Yang
 * @Date: 2022/07/26/22:12
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {

        // 获取资源
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        // 通道绑定（声名）交换机
        channel.exchangeDeclare("logs","fanout");
        /**
         * 由于使用了交换机的模型，
         * 生产者生成的消息送至交换机
         * 消费者 消费队列中的消息
         *
         * 队列为了节约资源，使用的一般都是临时队列
         */
        // 获取临时队列的名称
        String queueName = channel.queueDeclare().getQueue();

        //绑定交换机和队列
        channel.queueBind(queueName,"logs","");

        // 消费队列中的消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1"+new String(body));
            }
        });
    }
}
