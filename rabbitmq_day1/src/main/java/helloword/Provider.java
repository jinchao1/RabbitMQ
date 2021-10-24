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
	//生产消息
	@Test
	public void testSendMessage() throws IOException, TimeoutException {
		/*//创建连接mq的连接工厂对象
		ConnectionFactory connectionFactory = new ConnectionFactory();
		//设置连接rabbitmq的主机
		connectionFactory.setHost("192.168.8.101");
		//设置端口号
		connectionFactory.setPort(5672);
		//设置连接哪个虚拟主机
		connectionFactory.setVirtualHost("/ems");
		//设置访问虚拟主机的用户名和密码
		connectionFactory.setUsername("ems");
		connectionFactory.setPassword("123");
		
		//获取连接对象
		Connection connection = connectionFactory.newConnection();*/
		
		//通过工具类获取连接对象
		Connection connection = RabbitmqUtils.getConnection();
		
		//获取连接中通道
		Channel channel = connection.createChannel();
		
		//通道绑定对应消息队列
		//参数1: queue 队列名称  如果不存在自动创建
		//参数2: durable 用来定义队列特性是否要持久化  true 持久化队列   false 不持久化
		//参数3: exclusive 是否独占队列  true 独占队列   false 不独占
		//参数4: autoDelete 是否在消费完成后自动删除队列   true 自动删除   false 不自动删除
		//参数5: 额外附加参数
		channel.queueDeclare("aa",true,false,true,null);
		
		//发布消息
		//   exchange, routingKey, props, body
		//参数1: exchange  交换机名称
		//参数2: routingKey 队列名称
		//参数3: props 传递消息额外设置
		//参数4： body 消息的具体内容
		channel.basicPublish("", "aa", MessageProperties.PERSISTENT_TEXT_PLAIN, "hello rabbitmq".getBytes());
		
		/*channel.close();
		connection.close();*/
		
		//调用工具类
		RabbitmqUtils.closeConnectionAndChannel(channel, connection);
		
	}
}
















