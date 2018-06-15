package com.longma.mopet.gm.gateway.reward.handler;

import com.longma.mopet.gm.base.message.b2s.base.B2SMessage;
import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.base.process.OnlyMsgIdHandler;
import com.longma.mopet.gm.bean.ResourcePair;
import com.longma.mopet.gm.gateway.reward.s2b.SearchResourceS2B;
import com.longma.mopet.gm.template.TemplateService;
import com.longma.mopet.gm.template.resource.CardTemplate;
import com.longma.mopet.gm.template.resource.EquipTemplate;
import com.longma.mopet.gm.template.resource.ItemTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 10:38 2018/6/5
 * @Modified By:
 */
public class SearchResourceHandler extends OnlyMsgIdHandler {
    @Autowired
    private TemplateService templateService;
    private SearchResourceS2B send;
    @Override
    protected S2BMessage handle(B2SMessage message) {
        if (send == null) {
            refresh();
        }
        return send;
    }

    /**
     * @Author: Lvxianqing
     * @Description: 当需要刷新资源时，调用此方法（比如后期需要上传更新excel时，在更新后调用一下这个方法）
     * @Date: 2018-06-05 10:59:01
     * @param
     */
    public void refresh() {

        List<ItemTemplate> itemTemplateList = templateService.getAllList(ItemTemplate.class);
        List<EquipTemplate> equipTemplateList = templateService.getAllList(EquipTemplate.class);
        List<CardTemplate> cardTemplateList = templateService.getAllList(CardTemplate.class);

        send = new SearchResourceS2B();
        itemTemplateList.forEach(item -> send.getItem().add(new ResourcePair(item.getId(), item.getName())));
        equipTemplateList.forEach(equip -> send.getEquip().add(new ResourcePair(equip.getId(), equip.getName())));
        cardTemplateList.forEach(card -> send.getCard().add(new ResourcePair(card.getId(), card.getName())));
        this.send = send;
    }
}
