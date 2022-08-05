package com.yang.rabbitmqstart.rabbitmq_springboot;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: boot rabbitmq消费者
 * @Author: Guo.Yang
 * @Date: 2022/08/03/22:55
 */
// 需要交给spring管理 @Component
// 添加 @RabbitListener 注解表示该类为mq消费者（监听者），queuesToDeclare 表示队列的申明或创建（没有队列就会创建，有队列的话 就声名）
    // @Queue表示具体的队列 value 表示队列的名称
@Component
@RabbitListener(queuesToDeclare = @Queue(value = "hello"))
public class BootConsumer1 {

    // 在创建的消费者类上随意创建一个方法，方法名称随意，方法上添加¥@RabbitHandler注解 表示 消费执行的方法 方法里的参数就是获取队列中的消息
    @RabbitHandler
    public void  consumerHandler(String message){
        System.out.println(message);
    }
}
