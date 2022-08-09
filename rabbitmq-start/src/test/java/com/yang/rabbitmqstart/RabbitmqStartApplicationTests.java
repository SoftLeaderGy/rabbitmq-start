package com.yang.rabbitmqstart;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqStartApplicationTests {

	// rabbitmq集成springboot后，我们操作rabbitmq时，直接注入RabbitTemplate 即可
	@Autowired
	private RabbitTemplate rabbitTemplate;

	// hello world
	@Test
	void contextLoads() {
		// 通过rabbitTemplate 里的 convertAndSend 方法发送消息
		// convertAndSend(转换和发送) => 直接传入发送的对象即可，因为rabbitmq发送消息底层需要的是 字节数组,该方法封装后 直接传入对象
		// 参数一：路由键（队列名称） 参数二：发送的消息
		rabbitTemplate.convertAndSend("hello","hello world");
	}
	// work
	@Test
	void testWork(){
		for (int i = 0; i < 10; i++) {
			rabbitTemplate.convertAndSend("work","work 模型" + i);
		}
	}

	// fanout
	@Test
	void testFanout(){
		rabbitTemplate.convertAndSend("logs","","fanout模型");
	}

	// direct
	@Test
	void testRoute(){
		rabbitTemplate.convertAndSend("directs","error","route模型");
	}

	@Test
	void testTopic(){
		rabbitTemplate.convertAndSend("topics","order","topic模型");
	}
}
