package helloword;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.Connection.Tune;

import util.RabbitmqUtils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class Customer {

	public static void main(String[] args) throws IOException, TimeoutException {
		
		/*//创建连接工厂
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("192.168.8.101");
		connectionFactory.setPort(5672);
		connectionFactory.setVirtualHost("/ems");
		connectionFactory.setUsername("ems");
		connectionFactory.setPassword("123");
		
		//创建连接对象
		Connection connection = connectionFactory.newConnection();*/
		
		//通过工具类获取连接对象
		Connection connection = RabbitmqUtils.getConnection();
		
		//创建通道
		Channel channel = connection.createChannel();
		
		//通道绑定对象
		channel.queueDeclare("aa", true, false, true, null);
		
		//消费消息
		//参数1: 消费哪个队列的消息  队列名称
		//参数2: 开始消息的自动确认机制
		//参数3: 消费时的回调
		channel.basicConsume("aa", true, new DefaultConsumer(channel) {

			@Override  //最后一个参数: 消息队列中取出的消息
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("new String(body) = " + new String(body));
			}
		});
		
		//channel.close();
		//connection.close();
		
	}
}
























































