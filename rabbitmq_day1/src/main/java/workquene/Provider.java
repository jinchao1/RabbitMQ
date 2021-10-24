package workquene;

import java.io.IOException;

import javax.imageio.event.IIOReadWarningListener;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import util.RabbitmqUtils;

public class Provider {
	public static void main(String[] args) throws IOException {
		//��ȡ���Ӷ���
		Connection connection = RabbitmqUtils.getConnection();
		//��ȡͨ������
		Channel channel = connection.createChannel();
		
		//ͨ��ͨ����������
		channel.queueDeclare("work", true, false, false, null);
		
		for(int i = 1; i <= 20; i++) {
			//������Ϣ
			channel.basicPublish("", "work", null, (i+"hello work quene").getBytes());
		}
		
		//�ر���Դ
		RabbitmqUtils.closeConnectionAndChannel(channel, connection);
	}
}
