package helloword;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import util.RabbitmqUtils;

public class Provider {
	//������Ϣ
	@Test
	public void testSendMessage() throws IOException, TimeoutException {
		/*//��������mq�����ӹ�������
		ConnectionFactory connectionFactory = new ConnectionFactory();
		//��������rabbitmq������
		connectionFactory.setHost("192.168.8.101");
		//���ö˿ں�
		connectionFactory.setPort(5672);
		//���������ĸ���������
		connectionFactory.setVirtualHost("/ems");
		//���÷��������������û���������
		connectionFactory.setUsername("ems");
		connectionFactory.setPassword("123");
		
		//��ȡ���Ӷ���
		Connection connection = connectionFactory.newConnection();*/
		
		//ͨ���������ȡ���Ӷ���
		Connection connection = RabbitmqUtils.getConnection();
		
		//��ȡ������ͨ��
		Channel channel = connection.createChannel();
		
		//ͨ���󶨶�Ӧ��Ϣ����
		//����1: queue ��������  ����������Զ�����
		//����2: durable ����������������Ƿ�Ҫ�־û�  true �־û�����   false ���־û�
		//����3: exclusive �Ƿ��ռ����  true ��ռ����   false ����ռ
		//����4: autoDelete �Ƿ���������ɺ��Զ�ɾ������   true �Զ�ɾ��   false ���Զ�ɾ��
		//����5: ���⸽�Ӳ���
		channel.queueDeclare("aa",true,false,true,null);
		
		//������Ϣ
		//   exchange, routingKey, props, body
		//����1: exchange  ����������
		//����2: routingKey ��������
		//����3: props ������Ϣ��������
		//����4�� body ��Ϣ�ľ�������
		channel.basicPublish("", "aa", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitmq".getBytes());
		
		/*channel.close();
		connection.close();*/
		
		//���ù�����
		RabbitmqUtils.closeConnectionAndChannel(channel, connection);
		
	}
}
















