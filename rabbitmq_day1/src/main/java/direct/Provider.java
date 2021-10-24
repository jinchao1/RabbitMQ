package direct;

import java.io.IOException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import util.RabbitmqUtils;

public class Provider {
	public static void main(String[] args) throws IOException {
		//获取连接对象
		Connection connection = RabbitmqUtils.getConnection();
		//获取通道对象
		Channel channel = connection.createChannel();
		
		String exchangeName = "logs_direct";
		
		//通过通道声明交换机    参数1: 交换机名称       参数2: direct   路由模式
		channel.exchangeDeclare(exchangeName,"direct");
		//发送消息
		String routingkey = "error";
		channel.basicPublish(exchangeName, routingkey, null,("这是direct模型发布的基于route key: ["+routingkey+"] 发送的消息").getBytes());
		
		//关闭资源
		RabbitmqUtils.closeConnectionAndChannel(channel, connection);
		
		
		
		
		
		
		
		
		
		
	}
}
