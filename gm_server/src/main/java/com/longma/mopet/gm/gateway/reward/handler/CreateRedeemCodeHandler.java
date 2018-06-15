package com.longma.mopet.gm.gateway.reward.handler;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.model.RedeemCode;
import com.longma.mopet.gm.model.RedeemCodeRecord;
import com.longma.mopet.gm.gateway.reward.b2s.CreateRedeemCodeB2S;
import com.longma.mopet.gm.gateway.reward.handler.base.RedeemCodeHandler;
import com.longma.mopet.gm.gateway.reward.s2b.CreateRedeemCodeS2B;
import com.longma.mopet.gm.util.UUIDUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:36 2018/5/3
 * @Modified By:
 */
public class CreateRedeemCodeHandler extends RedeemCodeHandler {


    @Override
    protected S2BMessage handle(B2SMessage message) {
        CreateRedeemCodeS2B send = new CreateRedeemCodeS2B();
        CreateRedeemCodeB2S receive = (CreateRedeemCodeB2S)message;
        RedeemCodeRecord record = new RedeemCodeRecord(receive);
        int total = receive.getNum();
        int times = receive.getTimes();
        List<String> redeemCodeList = new ArrayList<>();
        for(int i=0;i<total;i++) {
            // 生成兑换码
            redeemCodeList.add(UUIDUtil.createCodeUUID(12));
        }

        redeemCodeRecordDao.insert(record);

        redeemCodeList.forEach(code -> {
            // 存入
            RedeemCode redeemCode = new RedeemCode(code,receive.getCodeId(),times);
            redeemCodeDao.insert(redeemCode);
        });

        send.setRedeemCodeList(redeemCodeList);
        return send;
    }

    @Override
    protected Class<? extends B2SMessage> getMessageType() {
        return CreateRedeemCodeB2S.class;
    }
}
