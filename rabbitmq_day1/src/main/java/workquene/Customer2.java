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
		//��ȡ���Ӷ���
		Connection connection = RabbitmqUtils.getConnection();
		
		//��ȡͨ������
		final Channel channel = connection.createChannel();
		
		channel.basicQos(1);//ÿ��ֻ������һ����Ϣ
		
		channel.queueDeclare("work", true, false, false, null);
		
		channel.basicConsume("work", false, new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("������-2:"+ new String(body));
				//�ֶ�ȷ��   ����1: �ֶ�ȷ����Ϣ��ʶ     ����2: false ÿ��ȷ��һ��
				channel.basicAck(envelope.getDeliveryTag(), false);
			}	
		});
		
		
	}
}
