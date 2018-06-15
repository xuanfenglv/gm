package com.longma.mopet.gm.rabbitmq.component;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 15:21 2018/5/14
 * @Modified By:
 */
@Component
@ConfigurationProperties(prefix = "notice")
@PropertySource(value ={"classpath:/config/notice.properties"})
public class NoticeProperties {
    // 公告名
    private List<String> list;
    // 服务器数量
    private int serverNum;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public int getServerNum() {
        return serverNum;
    }

    public void setServerNum(int serverNum) {
        this.serverNum = serverNum;
    }
}
