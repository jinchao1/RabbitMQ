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
		
		//ͨ�������������Լ�����������
		channel.exchangeDeclare(exchangeName, "direct");
		
		//����һ����ʱ����
		String queue = channel.queueDeclare().getQueue();
		
		//���� route key �󶨶��кͽ�����
		channel.queueBind(queue, exchangeName, "error");
		
		//��ȡ���ѵ���Ϣ
		channel.basicConsume(queue, true, new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("������1:"+new String(body));
			}
			
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
