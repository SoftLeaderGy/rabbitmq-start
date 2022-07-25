package com.yang.rabbitmqstart.module1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.yang.rabbitmqstart.util.RabbitMQUtils;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * @Description: 消息生产者
 * @Author: Guo.Yang
 * @Date: 2022/07/24/11:12
 */
public class Producer {
    /**
     * 生产消息
     */
    @Test
    @SneakyThrows
    public void testSendMsg(){
        /*
        // 创建连接mq的连接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 设置连接RabbitMQ的主机地址
        connectionFactory.setHost("localhost");
        // 设置连接mq的端口
        connectionFactory.setPort(5672);
        // 设置连接哪个虚拟主机
        connectionFactory.setVirtualHost("/msg");
        // 设置mq连接的用户名密码
        connectionFactory.setUsername("msg");
        connectionFactory.setPassword("123");
        // 通过连接工厂 获取连接对象
        Connection connection = connectionFactory.newConnection();
         */

        /**
         * 使用rabbitMQ工具类获取MQ连接对象
         */
        Connection connection = RabbitMQUtils.getConnection();
        // 通过连接对象获取连接通道
        Channel channel = connection.createChannel();
        // 连接通道绑定对应的消息队列
        //参数1:  队列名称 如果队列不存在自动创建
        //参数2:  用来定义队列特性是否要持久化 true 持久化队列   false 不持久化
        //参数3:  exclusive 是否独占队列  true 独占队列   false  不独占
        //参数4:  autoDelete: 是否在消费完成后自动删除队列  true 自动删除  false 不自动删除
        //参数5:  额外附加参数
        channel.queueDeclare("hello",false,false,false,null);

        //发布消息
        //参数1: 交换机名称 参数2:队列名称  参数3:传递消息额外设置  参数4:消息的具体内容
        // 由于本次的队列模型是生产者直接发送消息给队列，并没有涉及到交换机，所以本次发送消息的 参数一（交换机名称）为空
        channel.basicPublish("","hello",null,"Hello RabbitMQ".getBytes());

        /*
        // 关闭通道
        channel.close();
        // 关闭连接
        connection.close();
         */
        /**
         * 使用rabbitMQ工具类获取关闭资源
         */
        RabbitMQUtils.closeConAndChanel(connection,channel);
    }
}
