package com.longma.mopet.gm.amqp;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.concurrent.TimeUnit;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:16 2018/5/11
 * @Modified By:
 */
public class T2 {
    private static final String EXCHANGE_NAME = "exchange_auto_del2";
    private static final String ROUTING_KEY= "routingkey_demo22";
    private static final String QUEUE_NAME= "queue_demo1";
    private static final String IP_ADDRESS= "127.0.0.1";
    private static final int PORT= 5672;

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(IP_ADDRESS);
        factory.setPort(PORT);
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

//        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "aaa");
//        channel.queueUnbind(QUEUE_NAME, EXCHANGE_NAME, "aaa");
//        AMQP.Exchange.DeclareOk ok = channel.exchangeDeclare(EXCHANGE_NAME, "direct", false  , true, null);

//        try{
//            AMQP.Exchange.DeclareOk ok = channel.exchangeDeclarePassive(EXCHANGE_NAME);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        String queue = channel.queueDeclare().getQueue();
        AMQP.Queue.DeclareOk ok =  channel.queueDeclare(QUEUE_NAME, true, false, false, null);
//        channel.queueBind(queue, EXCHANGE_NAME, ROUTING_KEY);
//        String message = "Hello World";
//        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
        channel.close();
        TimeUnit.HOURS.sleep(1);
        connection.close();

    }
}
