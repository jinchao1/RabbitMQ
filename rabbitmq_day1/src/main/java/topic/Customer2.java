package topic;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import util.RabbitmqUtils;

public class Customer2 {
	public static void main(String[] args) throws IOException {
		
		//获取连接对象
		Connection connection = RabbitmqUtils.getConnection();
		Channel channel = connection.createChannel();
		
		//声明交换机以及交换机类型
		channel.exchangeDeclare("topics", "topic");
		
		//创建一个临时队列
		String queue = channel.queueDeclare().getQueue();
		
		//绑定队列和交换机    动态通配符形式  route key
		channel.queueBind(queue, "topics", "user.#");
		
		//消费消息
		channel.basicConsume(queue, true, new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("消费者2:"+new String(body));
			}
			
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
