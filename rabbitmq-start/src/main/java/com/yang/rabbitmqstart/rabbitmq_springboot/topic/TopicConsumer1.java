package com.yang.rabbitmqstart.rabbitmq_springboot.topic;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: topic模型（订阅模型）消费者
 * @Author: Guo.Yang
 * @Date: 2022/08/07/20:26
 */
@Component
public class TopicConsumer1 {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "topics",type = "topic"),
            key = {"user.*"}
    ))
    public void receive(String message){
        System.out.println("message1 ：" + message);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "topics",type = "topic"),
            key = {"order.#"}
    ))
    public void receive1(String message){
        System.out.println("message2 ：" + message);
    }
}
