package fanout;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import util.RabbitmqUtils;

public class Customer2 {
	public static void main(String[] args) throws IOException {
		
		//��ȡ���Ӷ���
		Connection connection = RabbitmqUtils.getConnection();
		Channel channel = connection.createChannel();
		
		//ͨ���󶨽�����
		channel.exchangeDeclare("logs", "fanout");
		
		//��ʱ����
		String queueName = channel.queueDeclare().getQueue();
		
		//�󶨽������Ͷ���
		channel.queueBind(queueName, "logs", "");
		
		//������Ϣ
		channel.basicConsume(queueName, true, new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("������2: " + new String(body));
			}
			
		});
		
		
	}
}
