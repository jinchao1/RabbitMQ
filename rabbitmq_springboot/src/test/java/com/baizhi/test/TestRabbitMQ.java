package com.baizhi.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jca.work.WorkManagerTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import com.baizhi.RabbitmqSpringbootApplication;

@SpringBootTest(classes = RabbitmqSpringbootApplication.class)
@RunWith(SpringRunner.class)
public class TestRabbitMQ {
	
	//注入 RabbitTemplate
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	
	//topic   动态路由    订阅模式
	@Test
	public void testTopic() {
		rabbitTemplate.convertAndSend("topics", "product.sava.add", "product.save.add   路由消息");
	}
	
	//route  路由模式
	@Test
	public void testRoute() {
		rabbitTemplate.convertAndSend("directs", "error", "发送Info的key的路由信息");
	}
	
	//fanout  广播
	@Test
	public void testFanout() {
		rabbitTemplate.convertAndSend("logs", "", "fanout模型发送的消息");
	}
	
	
	//work
	@Test
	public void testWork() {
		for(int i = 0;i < 10;i++) {
			rabbitTemplate.convertAndSend("work", "work 模型" + i);
		}
	}
		
	// hello world
	@Test
	public void testHello() {
		rabbitTemplate.convertAndSend("hello", "hello world");
	}
	
	
	
	
}
