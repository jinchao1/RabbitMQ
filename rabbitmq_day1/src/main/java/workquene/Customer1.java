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
		//��ȡ���Ӷ���
		Connection connection = RabbitmqUtils.getConnection();
		
		//��ȡͨ������
		final Channel channel = connection.createChannel();
		
		channel.basicQos(1);//ÿ��ֻ������һ����Ϣ
		
		channel.queueDeclare("work", true, false, false, null);
		
		//����1: ��������     ����2: ��Ϣ�Զ�ȷ��    true  �������Զ���rabbitmqȷ����Ϣ����    false  �����Զ�ȷ����Ϣ
		channel.basicConsume("work", false, new DefaultConsumer(channel) {

			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println("������-1:"+ new String(body));
				//����1: ȷ�϶������ĸ���������    ����2: �Ƿ��������Ϣͬʱȷ��
				channel.basicAck(envelope.getDeliveryTag(), false);
			}	
		});
		
		
	}
}
