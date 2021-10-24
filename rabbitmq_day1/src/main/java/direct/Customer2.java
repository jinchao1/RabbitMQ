package direct;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import util.RabbitmqUtils;

public class Customer2 {
	public static void main(String[] args) throws IOException {
		Connection connection = RabbitmqUtils.getConnection();
		Channel channel = connection.createChannel();
		
		String exchangeName = "logs_direct";
		
		channel.exchangeDeclare(exchangeName, "direct");
		
		String queue = channel.queueDeclare().getQueue();
		
		channel.queueBind(queue, exchangeName, "info");
		channel.queueBind(queue, exchangeName, "error");
		channel.queueBind(queue, exchangeName, "warning");
		
		channel.basicConsume(queue, true, new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("Ïû·ÑÕß2:"+new String(body));
			}
			
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
