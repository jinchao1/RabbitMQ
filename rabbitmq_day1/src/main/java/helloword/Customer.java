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
		
		/*//�������ӹ���
		ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("192.168.8.101");
		connectionFactory.setPort(5672);
		connectionFactory.setVirtualHost("/ems");
		connectionFactory.setUsername("ems");
		connectionFactory.setPassword("123");
		
		//�������Ӷ���
		Connection connection = connectionFactory.newConnection();*/
		
		//ͨ���������ȡ���Ӷ���
		Connection connection = RabbitmqUtils.getConnection();
		
		//����ͨ��
		Channel channel = connection.createChannel();
		
		//ͨ���󶨶���
		channel.queueDeclare("aa", true, false, true, null);
		
		//������Ϣ
		//����1: �����ĸ����е���Ϣ  ��������
		//����2: ��ʼ��Ϣ���Զ�ȷ�ϻ���
		//����3: ����ʱ�Ļص�
		channel.basicConsume("aa", true, new DefaultConsumer(channel) {

			@Override  //���һ������: ��Ϣ������ȡ������Ϣ
			public void handleDelivery(String consumerTag, Envelope envelope, BasicProperties properties, byte[] body)
					throws IOException {
				System.out.println("new String(body) = " + new String(body));
			}
		});
		
		//channel.close();
		//connection.close();
		
	}
}
























































