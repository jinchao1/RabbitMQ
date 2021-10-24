package workquene;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.Connection.Tune;

import util.RabbitmqUtils;

public class Customer1 {
	public static void main(String[] args) throws IOException {
		//获取连接对象
		Connection connection = RabbitmqUtils.getConnection();
		
		//获取通道对象
		final Channel channel = connection.createChannel();
		
		channel.basicQos(1);//每次只能消费一个消息
		
		channel.queueDeclare("work", true, false, false, null);
		
		//参数1: 队列名称     参数2: 消息自动确认    true  消费者自动向rabbitmq确认消息消费    false  不会自动确认消息
		channel.basicConsume("work", false, new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("消费者-1:"+ new String(body));
				//参数1: 确认队列中哪个具体消费    参数2: 是否开启多个消息同时确认
				channel.basicAck(envelope.getDeliveryTag(), false);
			}	
		});
		
		
	}
}
