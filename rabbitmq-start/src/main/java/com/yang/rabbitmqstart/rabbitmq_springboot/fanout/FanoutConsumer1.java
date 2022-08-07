package com.yang.rabbitmqstart.rabbitmq_springboot.fanout;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: fanout集成springboot模型 消费者
 * @Author: Guo.Yang
 * @Date: 2022/08/06/21:15
 */
@Component
public class FanoutConsumer1 {

    @RabbitListener(
            // 绑定
            bindings = @QueueBinding(
                    value = @Queue, // 创建绑定临时队列
                    exchange = @Exchange(value = "logs",type = "fanout") // 绑定交换机
            )
    )
    public void dispose(String message){
        System.out.println("message1 : " + message);
    }

    @RabbitListener(
            // 绑定
            bindings = @QueueBinding(
                    value = @Queue, // 创建绑定临时队列
                    exchange = @Exchange(value = "logs",type = "fanout") // 绑定交换机
            )
    )
    public void dispose1(String message){
        System.out.println("message2 : " + message);
    }

}
