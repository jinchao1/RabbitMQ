package topic;

import java.io.IOException;

import org.slf4j.helpers.MarkerIgnoringBase;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import util.RabbitmqUtils;

public class Provider {
	public static void main(String[] args) throws IOException {
		//获取连接对象
		Connection connection = RabbitmqUtils.getConnection();
		Channel channel = connection.createChannel();
		
		//声明交换机以及交换机类型
		channel.exchangeDeclare("topics", "topic");
		
		//发布消息
		String routekey = "user.save.hello";
		
		channel.basicPublish("topics", routekey, null, ("这里是topic动态路由模型，routekey :[ "+routekey+" ]").getBytes());
		
		//关闭资源
		RabbitmqUtils.closeConnectionAndChannel(channel, connection);
		
		
		
		
		
		
		
	}
}
