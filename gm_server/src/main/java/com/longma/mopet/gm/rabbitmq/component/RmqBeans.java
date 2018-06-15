package com.longma.mopet.gm.rabbitmq.component;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 15:02 2018/5/14
 * @Modified By:
 */
@Configuration
public class RmqBeans {
    @Autowired
    private RmqProperties properties;

    @Bean
    public ConnectionFactory getConnectionFactory() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(properties.getHost());
        factory.setPort(properties.getPort());
        factory.setUsername(properties.getUsername());
        factory.setPort(properties.getPort());
        return factory;
    }
}
