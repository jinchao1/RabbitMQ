package fanout;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import util.RabbitmqUtils;

public class Provider {
	public static void main(String[] args) throws IOException {
		
		//��ȡ���Ӷ���
		Connection connection = RabbitmqUtils.getConnection();
		Channel channel = connection.createChannel();
		
		//��ͨ������ָ��������     ����1: ����������    ����2: ����������     fanout  �㲥����
		channel.exchangeDeclare("logs", "fanout");
		
		//������Ϣ
		channel.basicPublish("logs", "", null, "fanout type message".getBytes());
		
		//�ͷ���Դ
		RabbitmqUtils.closeConnectionAndChannel(channel, connection);
	}
	
}
