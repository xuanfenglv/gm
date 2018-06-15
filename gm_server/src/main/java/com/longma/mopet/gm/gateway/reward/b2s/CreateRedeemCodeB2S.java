package com.longma.mopet.gm.gateway.reward.b2s;

import com.longma.mopet.gm.base.message.b2s.AimAtServerAndChannelB2S;
import com.longma.mopet.gm.bean.Reward;
import com.longma.mopet.gm.result.CheckParamResult;
import com.longma.mopet.gm.util.StringUtil;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:37 2018/5/3
 * @Modified By:
 */
public class CreateRedeemCodeB2S extends AimAtServerAndChannelB2S {
    private long codeId;
    private int num;
    private String name;
    private String desc;
    private long beginTime;
    private long endTime;
    private int times;
    private List<Reward> content;
    private int pause;

    @Override
    protected void doCheck(CheckParamResult result) {
        if (StringUtil.isEmpty(name) || StringUtil.isEmpty(desc)) {
            result.setErrMsg("礼包名或描述不能为空");
        } else if(codeId==0||num==0||times==0) {
            result.setErrMsg("缺少必要的参数");
        } else if (num > 10000) {
            result.setErrMsg("生成兑换码数量不超过1万");

        } else if (name.length() > 15) {
            result.setErrMsg("礼包名长度不超过15");

        } else if (desc.length() > 20) {
            result.setErrMsg("描述长度不超过15");

        }  else if (beginTime > endTime) {
            result.setErrMsg("有效期填错了");

        } else if (times > 10000) {
            result.setErrMsg("兑换码领取次数不超过1万");

        } else {
            if (content==null||content.size() == 0) {
                result.setErrMsg("奖励不能为空");
            } else {
                // 检查资源的有效性

            }
        }

    }

    public long getCodeId() {
        return codeId;
    }

    public void setCodeId(long codeId) {
        this.codeId = codeId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(long beginTime) {
        this.beginTime = beginTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public List<Reward> getContent() {
        return content;
    }

    public void setContent(List<Reward> content) {
        this.content = content;
    }

    public int getPause() {
        return pause;
    }

    public void setPause(int pause) {
        this.pause = pause;
    }
}
