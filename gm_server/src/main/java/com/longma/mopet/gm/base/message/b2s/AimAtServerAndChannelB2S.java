package com.longma.mopet.gm.base.message.b2s;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.result.CheckParamResult;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description: 针对服务器和渠道的消息
 * @Date:Create in 17:04 2018/5/10
 * @Modified By:
 */
public abstract class AimAtServerAndChannelB2S extends B2SMessage {
    private List<Integer> servers;
    private List<Integer> channels;
    @Override
    public CheckParamResult checkParams() {
        CheckParamResult result = super.checkParams();
        if(result.isValid()) {
            // 核对服务器和渠道是否存在
            if (servers==null||servers.size() == 0) {
                result.setErrMsg("没有选中的服务器");
            } else if (channels==null||channels.size() == 0) {
                result.setErrMsg("没有选中的渠道");
            } else {
                // 判断是否有效

            }

        }
        return result;
    }

    public List<Integer> getServers() {
        return servers;
    }

    public void setServers(List<Integer> servers) {
        this.servers = servers;
    }

    public List<Integer> getChannels() {
        return channels;
    }

    public void setChannels(List<Integer> channels) {
        this.channels = channels;
    }
}
