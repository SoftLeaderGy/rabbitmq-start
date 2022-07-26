package com.yang.rabbitmqstart.module1;

import com.rabbitmq.client.*;
import com.yang.rabbitmqstart.util.RabbitMQUtils;
import lombok.SneakyThrows;
import org.junit.Test;
import org.springframework.scheduling.annotation.Async;

import java.io.IOException;

/**
 * @Description: 开发消费者
 * @Author: Guo.Yang
 * @Date: 2022/07/24/11:15
 */
public class Consumer {

    /**
     *
     * @param args
     */
    @SneakyThrows
    public static void main(String[] args) {
        /*
        // 创建mq连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/msg");
        connectionFactory.setUsername("msg");
        connectionFactory.setPassword("123");
        Connection connection = connectionFactory.newConnection();
         */
        /**
         * 使用RabbitMQ工具类获取连接对象
         */
        Connection connection = RabbitMQUtils.getConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare("hello",false,false,true,null);

        // 消费指定队列中的消息
        // 参数1: 消费那个队列中的消息 （队列名称）
        // 参数2：开启消息的确认机制
        // 参数3：消费是回调的接口（需要重写handleDelivery 方法即可）
        channel.basicConsume("hello",true,new DefaultConsumer(channel){
            @Override // 最后一个参数为消息的具体内容
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(Thread.currentThread().getName() + "==========================" + new String(body));
            }
        });
        /**
         * 消费着在 消费消息的时候是异步进行的，重新开启了个线程进行消费处理，主线程会继续执行，如果直接关闭的话 ，
         * 会出现还没等消息处理呢，连接已经关闭了，但是消息已经被消费了
         */
//            channel.close();
//            connection.close();
    }
}
