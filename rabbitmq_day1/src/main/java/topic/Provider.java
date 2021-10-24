package topic;

import java.io.IOException;

import org.slf4j.helpers.MarkerIgnoringBase;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import util.RabbitmqUtils;

public class Provider {
	public static void main(String[] args) throws IOException {
		//��ȡ���Ӷ���
		Connection connection = RabbitmqUtils.getConnection();
		Channel channel = connection.createChannel();
		
		//�����������Լ�����������
		channel.exchangeDeclare("topics", "topic");
		
		//������Ϣ
		String routekey = "user.save.hello";
		
		channel.basicPublish("topics", routekey, null, ("������topic��̬·��ģ�ͣ�routekey :[ "+routekey+" ]").getBytes());
		
		//�ر���Դ
		RabbitmqUtils.closeConnectionAndChannel(channel, connection);
		
		
		
		
		
		
		
	}
}
