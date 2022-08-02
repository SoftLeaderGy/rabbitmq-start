package com.yang.rabbitmqstart.fanoutqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.yang.rabbitmqstart.util.RabbitMQUtils;

import java.io.IOException;

/**
 * @Description: 广播模型-生产者
 * @Author: Guo.Yang
 * @Date: 2022/07/26/22:03
 */
public class Producer {
    public static void main(String[] args) throws IOException {
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();
        /**
         * 声名交换机
         */
        //将通道声明指定交换机   //参数1: 交换机名称    参数2: 交换机类型  fanout 广播类型
        channel.exchangeDeclare("logs","fanout"); // 广播 一条消息会同时被多个消费者消费

        // 向名为logs的交换机中发送消息
        //参数1: 交换机名称 参数2:队列名称  参数3:传递消息额外设置  参数4:消息的具体内容
        channel.basicPublish("logs","",null,"hello fanout queue".getBytes());

        // 释放资源
        RabbitMQUtils.closeConAndChanel(connection,channel);
    }
}
