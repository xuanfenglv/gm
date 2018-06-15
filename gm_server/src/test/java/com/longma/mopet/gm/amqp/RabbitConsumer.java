package com.longma.mopet.gm.amqp;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 13:22 2018/5/11
 * @Modified By:
 */
public class RabbitConsumer {
    private static final String QUEUE_NAME= "queue_demo1";
    private static final String IP_ADDRESS= "127.0.0.1";
    private static final int PORT= 5672;

    public static void main(String[] args) throws Exception{
        final Num num = new Num();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection(new Address[]{new Address(IP_ADDRESS, PORT)});
        final Channel channel = connection.createChannel();
        channel.basicQos(10);
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                num.value++;
                System.out.println("receive message"+(num.value)+":" + new String(body));
                if(num.value==6) {
                    channel.basicRecover();
                }
                if (num.value >= 6) {
                   boolean open = channel.getConnection().isOpen();
                    System.out.println(open);
                }
//                try {
////                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(QUEUE_NAME,consumer);

//        channel.close();
//        connection.close();
    }
}
