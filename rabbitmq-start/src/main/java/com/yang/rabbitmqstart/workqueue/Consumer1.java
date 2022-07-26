package com.yang.rabbitmqstart.workqueue;

import com.rabbitmq.client.*;
import com.yang.rabbitmqstart.util.RabbitMQUtils;
import lombok.SneakyThrows;

import java.io.IOException;

/**
 * @Description: 工作队列模型-消息消费者1
 * @Author: Guo.Yang
 * @Date: 2022/07/26/20:27
 */
public class Consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        channel.basicQos(1); // 一次只拿一个队列中的消息
        channel.queueDeclare("work",true,false,false,null);
        // 消费指定队列中的消息
        // 参数1: 消费那个队列中的消息 （队列名称）
        // 参数2：开启消息的确认机制
        // 参数3：消费是回调的接口（需要重写handleDelivery 方法即可）
        /**
         * 开启自动确认机制，会将队列中的消息，一次性的分派到消费者身上
         * rabbitMQ 消息消费策略是循环，也就是说  如果队列中有10个消息 ，有两个消费者  那么13579消息会给消费者1 其他的会给消费者2
         * 并且是一次性都拿到通道里边进行依次执行，
         */
        /**
         * 为了达到能者多劳的目的 实现 哪个消费者处理消息快，谁就多处理的原则
         * 1、设置一次只拿一条消息 // channel.basicQos(1); // 一次只拿一个队列中的消息
         * 2、设置消费者消费的时候不，自动确认消息（手动确认消息） // channel.basicAck(envelope.getDeliveryTag(),false);// 参数1:确认队列中那个具体消息 参数2:是否开启多个消息同时确实
         */
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            @SneakyThrows
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                Thread.sleep(2000);
                System.out.println("消费者 1 ： " + new String(body));
                // ...   业务执行完成以后，请手动确认消息

                // 手动消息确认
                channel.basicAck(envelope.getDeliveryTag(),false);// 参数1:确认队列中那个具体消息 参数2:是否开启多个消息同时确实
            }
        });
    }
}
