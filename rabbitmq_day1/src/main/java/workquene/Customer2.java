package workquene;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.Connection.Tune;

import util.RabbitmqUtils;

public class Customer2 {
	public static void main(String[] args) throws IOException {
		//获取连接对象
		Connection connection = RabbitmqUtils.getConnection();
		
		//获取通道对象
		final Channel channel = connection.createChannel();
		
		channel.basicQos(1);//每次只能消费一个消息
		
		channel.queueDeclare("work", true, false, false, null);
		
		channel.basicConsume("work", false, new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("消费者-2:"+ new String(body));
				//手动确认   参数1: 手动确认消息标识     参数2: false 每次确认一个
				channel.basicAck(envelope.getDeliveryTag(), false);
			}	
		});
		
		
	}
}
