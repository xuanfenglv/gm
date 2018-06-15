package com.longma.mopet.gm.gateway.log.handler.base;

import com.longma.mopet.gm.base.message.b2s.GameLogSearchB2S;
import com.longma.mopet.gm.base.message.s2b.RecordSearchS2B;
import com.longma.mopet.gm.base.process.base.IMsgHandler;
import com.longma.mopet.gm.bean.Interval;
import com.longma.mopet.gm.bean.Limit;
import com.longma.mopet.gm.constant.IntervalRelation;
import com.longma.mopet.gm.dao.log.base.BaseLogDao;
import com.longma.mopet.gm.util.CommonUtil;
import com.longma.mopet.gm.util.DateUtil;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 16:30 2018/5/9
 * @Modified By:
 */
public abstract class BaseLogSearchHandler extends IMsgHandler {

    protected RecordSearchS2B paging(GameLogSearchB2S b2S, String condition) {
        RecordSearchS2B send = new RecordSearchS2B();
        // 所有日期
        List<String> dates = DateUtil.getBetweenDate(b2S.getBeginTime(), b2S.getEndTime());
        // 每天日志的总数
        List<Integer> totals = getLogDao().searchTotals(dates, b2S.getServerId(), condition);
        // 符合条件的日志的总数
        int sum = CommonUtil.sum(totals);
        if (sum == 0) {
            return send;
        }

        int offset = b2S.getPageSize() * (b2S.getPageNum() - 1);
        int size = b2S.getPageSize();
        int endset = offset + size;

        // 所有数据的区间
        Interval dataInterval = new Interval(0, sum);
        // 分页查询区间
        Interval searchInterval = new Interval(offset, endset);

        // 判断 所有数据的区间 和 分页查询区间 是否有交集（只有恶意发送数据时才会出现这种情况）
        if (!Interval.isIntersect(dataInterval, searchInterval)) {
            send.setErrorReturn(9, "不要捣乱");
            return send;
        }
        // 存储分页查询所在的数据区间（集合的三种关系了解一下、这里把相交分为左相交和右相交，所以有4种Limit关系）
        LinkedHashMap<String, Limit> position = new LinkedHashMap<>();
        int pointer = 0;
        boolean stop = false;
        for (int i = 0; i < totals.size() && !stop; i++) {
            int total = totals.get(i);
            Interval nowInterval = new Interval(pointer, pointer + total);

            IntervalRelation relation = nowInterval.relation(searchInterval);
            if (relation != IntervalRelation.相离) {
                Limit limit = new Limit();
                limit.setType(relation.getValue());

                if (relation == IntervalRelation.LEFT_INTERSECT) {
                    limit.setOffset(searchInterval.getL() - nowInterval.getL());
                    limit.setLen(nowInterval.getR()-searchInterval.getL());
                } else if (relation == IntervalRelation.RIGHT_INTERSECT) {
                    limit.setOffset(0);
                    limit.setLen(searchInterval.getL() - nowInterval.getL());
                } else if (relation == IntervalRelation.包含) {
                    limit.setOffset(searchInterval.getL() - nowInterval.getL());
                    limit.setLen(size);
                }
                position.put(dates.get(i), limit);
            }
            pointer += total;
        }
        send.setRecordList(new ArrayList());
        for (String date : position.keySet()) {
            String limit = position.get(date).genLimit();
            List list = getLogDao().searchOneDayLog(b2S.getServerId(), date, condition, limit);
            send.getRecordList().addAll(list);
        }

        send.setTotal(sum);
        return send;
    }
    protected RecordSearchS2B getAll(GameLogSearchB2S b2S, String condition) {
        RecordSearchS2B send = new RecordSearchS2B();
        // 所有日期
        List<String> dates = DateUtil.getBetweenDate(b2S.getBeginTime(), b2S.getEndTime());
        List allData = new ArrayList();
        for (String date : dates) {
            List list = getLogDao().searchOneDayLog(b2S.getServerId(), date, condition, null);
            allData.addAll(list);
        }
        send.setRecordList(allData);
        send.setTotal(allData.size());
        return send;
    }

    protected abstract BaseLogDao getLogDao();
}
