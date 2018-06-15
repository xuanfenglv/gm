package com.longma.mopet.gm.gateway.reward.s2b;

import com.longma.mopet.gm.base.message.s2b.S2BMessage;
import com.longma.mopet.gm.bean.ResourcePair;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 10:40 2018/6/5
 * @Modified By:
 */
public class SearchResourceS2B extends S2BMessage {
    private List<ResourcePair> item = new ArrayList<>();
    private List<ResourcePair> equip = new ArrayList<>();
    private List<ResourcePair> card = new ArrayList<>();

    public List<ResourcePair> getItem() {
        return item;
    }

    public void setItem(List<ResourcePair> item) {
        this.item = item;
    }

    public List<ResourcePair> getEquip() {
        return equip;
    }

    public void setEquip(List<ResourcePair> equip) {
        this.equip = equip;
    }

    public List<ResourcePair> getCard() {
        return card;
    }

    public void setCard(List<ResourcePair> card) {
        this.card = card;
    }
}
