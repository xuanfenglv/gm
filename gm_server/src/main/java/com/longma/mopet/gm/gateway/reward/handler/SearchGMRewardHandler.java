package com.longma.mopet.gm.gateway.reward.handler;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.RecordSearchS2B;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.gateway.reward.b2s.SearchRewardB2S;
import com.longma.mopet.gm.gateway.reward.handler.base.GMRewardHandler;
import com.longma.mopet.gm.model.GMReward;
import com.longma.mopet.gm.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 15:06 2018/5/7
 * @Modified By:
 */
public class SearchGMRewardHandler extends GMRewardHandler {
    @Override
    protected S2BMessage handle(B2SMessage message) {
        RecordSearchS2B send = new RecordSearchS2B();
        SearchRewardB2S b2S = (SearchRewardB2S)message;
        if(b2S.getRewardId()!=0) {
            GMReward record = gmRewardDao.selectRecordByRewardId(b2S.getRewardId());
            if(record!=null) {
                send.setTotal(1);
                List recordList = new ArrayList();
                recordList.add(record);
                send.setRecordList(recordList);
            }

        } else {
            StringBuilder _condition = new StringBuilder();
            if(!StringUtil.isEmpty(b2S.getName())) {
                _condition.append(" and ").append("`name` like ").append("'%").append(b2S.getName()).append("%'");
            }
            if(b2S.getReason()!=0) {
                _condition.append(" and ").append("`reason` = ").append(b2S.getReason());
            }
            if(b2S.getBeginTime()!=0||b2S.getEndTime()!=0) {
                if(b2S.getBeginTime()==0) {
                    _condition.append(" and ").append("deadline<=").append(b2S.getEndTime());
                } else if(b2S.getEndTime()==0) {
                    _condition.append(" and ").append("deadline>=").append(b2S.getBeginTime());
                } else {
                    _condition.append(" and ").append("deadline<=").append(b2S.getEndTime());
                    _condition.append(" and ").append("deadline>=").append(b2S.getBeginTime());
                }
            }

            int total = gmRewardDao.selectTotal(_condition.toString());
            if(total>0) {
                send.setTotal(total);
                if(b2S.getType()==1) {
                    _condition.append(" limit ").append((b2S.getPageNum()-1)*b2S.getPageSize()).append(",").append(b2S.getPageSize());
                }
                List<GMReward> recordList = gmRewardDao.selectByCondition(_condition.toString());
                send.setRecordList(recordList);
            }
        }
        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return SearchRewardB2S.class;
    }
}
