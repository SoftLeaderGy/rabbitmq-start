package com.yang.rabbitmqstart.rabbitmq_springboot.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: work模型集成springboot 消费者
 * @Author: Guo.Yang
 * @Date: 2022/08/06/20:34
 */
@Component
public class Consumer1 {

    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void cunsume1(String message){
        System.out.println("message1 = " + message);
    }


    @RabbitListener(queuesToDeclare = @Queue(value = "work"))
    public void cunsumer2(String message){
        System.out.println("message2 = " + message);
    }
}
