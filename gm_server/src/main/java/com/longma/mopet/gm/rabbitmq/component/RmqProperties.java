package com.longma.mopet.gm.rabbitmq.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 14:37 2018/5/14
 * @Modified By:
 */
@Component
@ConfigurationProperties(prefix = "rabbitmq")
@PropertySource(value ={"classpath:/config/rabbitmq.properties"})
public class RmqProperties {
    private String host;
    private int port;
    private String username;
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
