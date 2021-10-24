package util;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitmqUtils {
	
	private static ConnectionFactory connectionFactory;
	
	static {
		//��������Դ     �����ִ�� ִֻ��һ��
		connectionFactory = new ConnectionFactory();
		connectionFactory.setHost("192.168.8.101");
		connectionFactory.setPort(5672);
		connectionFactory.setVirtualHost("/ems");
		connectionFactory.setUsername("ems");
		connectionFactory.setPassword("123");
	}
	
	//�����ṩ���Ӷ���ķ���
	public static Connection getConnection() {
		try {
			return connectionFactory.newConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//�ر�ͨ���͹ر����ӹ��߷���
	public static void closeConnectionAndChannel(Channel channel,Connection conn) {
		try {
			if(channel != null) {
				channel.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
