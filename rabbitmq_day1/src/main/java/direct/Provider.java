package direct;

import java.io.IOException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import util.RabbitmqUtils;

public class Provider {
	public static void main(String[] args) throws IOException {
		//��ȡ���Ӷ���
		Connection connection = RabbitmqUtils.getConnection();
		//��ȡͨ������
		Channel channel = connection.createChannel();
		
		String exchangeName = "logs_direct";
		
		//ͨ��ͨ������������    ����1: ����������       ����2: direct   ·��ģʽ
		channel.exchangeDeclare(exchangeName,"direct");
		//������Ϣ
		String routingkey = "error";
		channel.basicPublish(exchangeName, routingkey, null,("����directģ�ͷ����Ļ���route key: ["+routingkey+"] ���͵���Ϣ").getBytes());
		
		//�ر���Դ
		RabbitmqUtils.closeConnectionAndChannel(channel, connection);
		
		
		
		
		
		
		
		
		
		
	}
}
