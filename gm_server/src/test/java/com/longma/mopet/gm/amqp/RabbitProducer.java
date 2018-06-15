package com.longma.mopet.gm.amqp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 11:00 2018/5/11
 * @Modified By:
 */
public class RabbitProducer {

    private static final String EXCHANGE_NAME = "exchange1";
    private static final String ROUTING_KEY= "routingkey_demo";
    private static final String QUEUE_NAME= "queue_demo";
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
//        channel.txSelect();
//        for(int i=1;i<=10;i++) {
            String message = "Hello World";
//        try {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
//            channel.txCommit();
//        } catch (IOException e) {
//            e.printStackTrace();
////            channel.txRollback();
//        }
//        }

        channel.close();
        connection.close();

    }
}
