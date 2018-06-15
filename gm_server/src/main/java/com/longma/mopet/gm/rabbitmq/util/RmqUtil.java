package com.longma.mopet.gm.rabbitmq.util;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 15:36 2018/5/14
 * @Modified By:
 */
public class RmqUtil {
    /**
     * @Author: Lvxianqing
     * @Description: 生成一个默认的交换器
     * @Date: 2018-05-14 15:38:25
     * @param channel
     * @param exchange
     */
    public static void genExchange(Channel channel,String exchange) throws IOException {

        channel.exchangeDeclare(exchange, BuiltinExchangeType.DIRECT, true, false, null);
    }
    /**
     * @Author: Lvxianqing
     * @Description: 生成一个默认的队列
     * @Date: 2018-05-14 15:42:11
     * @param channel
     * @param queue
     */
    public static void genQueue(Channel channel,String queue) throws IOException {

        channel.queueDeclare(queue,true,false,false,null);
    }
    /**
     * @Author: Lvxianqing
     * @Description: 生成一个默认的绑定
     * @Date: 2018-05-14 15:49:37
     * @param channel
     * @param queue
     * @param exchange
     * @param routingKey
     */
    public static void genBinding(Channel channel,String queue,String exchange,String routingKey) throws IOException {

        channel.queueBind(queue,exchange,routingKey,null);
    }
    public static void delQueue(Channel channel,String queue) throws IOException {

        channel.queueDelete(queue);
    }
}
