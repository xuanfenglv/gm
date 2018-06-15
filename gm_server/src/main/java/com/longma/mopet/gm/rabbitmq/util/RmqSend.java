package com.longma.mopet.gm.rabbitmq.util;

import com.alibaba.fastjson.JSON;
import com.longma.mopet.gm.rabbitmq.message.NoticeChangeMessage;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 17:01 2018/5/16
 * @Modified By:
 */
@Component
public class RmqSend {
    @Autowired
    private ConnectionFactory factory;

    public void sendPlainText(String exchange, String routingKey, String message) {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.basicPublish(exchange,routingKey, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        } finally {
            try {
                if (channel != null) {
                    channel.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }

    }
    public void sendMessage(String exchange, String routingKey, NoticeChangeMessage message) {
        String msg = JSON.toJSONString(message);

        sendPlainText(exchange, routingKey, msg);

    }

    public void sendMessageToServers(String exchange, List<Integer> servers, NoticeChangeMessage message) {
        for (Integer server : servers) {
            String rountingKey = server.toString();
            sendMessage(exchange,rountingKey,message);
        }
    }
    public void sendPlainTextToServers(String exchange, List<Integer> servers, String message) {
        for (Integer server : servers) {
            String rountingKey = server.toString();
            sendPlainText(exchange,rountingKey,message);
        }
    }

    public static void main(String[] args) {
        System.out.println("ss" instanceof String);
    }
}
