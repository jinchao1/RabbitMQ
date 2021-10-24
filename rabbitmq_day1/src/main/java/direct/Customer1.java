package direct;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import util.RabbitmqUtils;

public class Customer1 {
	public static void main(String[] args) throws IOException {
		
		Connection connection = RabbitmqUtils.getConnection();
		Channel channel = connection.createChannel();
		
		String exchangeName = "logs_direct";
		
		//通道声明交换机以及交换机类型
		channel.exchangeDeclare(exchangeName, "direct");
		
		//创建一个临时队列
		String queue = channel.queueDeclare().getQueue();
		
		//基于 route key 绑定队列和交换机
		channel.queueBind(queue, exchangeName, "error");
		
		//获取消费的消息
		channel.basicConsume(queue, true, new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("消费者1:"+new String(body));
			}
			
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
