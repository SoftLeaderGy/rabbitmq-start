package com.yang.rabbitmqstart.rabbitmq_springboot.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: direct模型消费者
 * @Author: Guo.Yang
 * @Date: 2022/08/07/17:23
 */
@Component
public class DirectConsumer1 {

    @RabbitListener(
            bindings = @QueueBinding(
                    exchange = @Exchange(value = "directs",type = "direct"),
                    value = @Queue,
                    key = {"info","error"}
            )
    )
    public void messageDispose1(String message){
        System.out.println("message1 : " + message);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue,
            exchange = @Exchange(value = "directs",type = "direct"),
            key = {"error"}
    ))
    public void messageDispose2(String message){
        System.out.println("message2 : " + message);
    }
}
