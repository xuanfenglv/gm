package com.longma.mopet.gm.rabbitmq.init;

import com.longma.mopet.gm.rabbitmq.component.NoticeProperties;
import com.longma.mopet.gm.rabbitmq.util.RmqUtil;
import com.longma.mopet.gm.util.NumUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author:Lvxingqing
 * @Description: 做rabbitmq的初始化操作
 * @Date:Create in 14:26 2018/5/14
 * @Modified By:
 */
@Component
public class RmqInit {

    @Autowired
    private NoticeProperties noticeProperties;
    @Autowired
    private ConnectionFactory factory;

    public void notice() throws Exception {
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            for (String type : noticeProperties.getList()) {
                String exchange = "notice." + type;
                RmqUtil.genExchange(channel, exchange);
                for (int i = 1; i <= noticeProperties.getServerNum(); i++) {
                    String queue = exchange + "." + NumUtil.getFullNum(i,3);
                    RmqUtil.genQueue(channel, queue);
                    RmqUtil.genBinding(channel, queue, exchange, String.valueOf(i));
                }

            }
        } finally {
            // 一定要先关channel，否则会报 AlreadyClosedException （关闭channel不是必须的，关闭connection会自动关闭此连接下的channel）
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }

        }
    }

}
