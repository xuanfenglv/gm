package com.longma.mopet.gm.dao.log.base;

import com.longma.mopet.gm.manager.DBManager;
import com.longma.mopet.gm.model.log.base.BaseLog;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description:
 * @Date:Create in 13:28 2018/5/9
 * @Modified By:
 */
public abstract class BaseLogDao {
    @Autowired
    private DBManager dBManager;

    private int searchOneDayTotal(int serverId, String date, String condition) {
        Session session = dBManager.getLogDbSession(serverId);
        int total = 0;
        // 判断表存不存在
        boolean exist = existTable(serverId, date);
        if(exist) {
            try {

                String sql = "select count(1) total from "+getWholeTableName(date)+" where 1=1 "+condition+" order by logTime ";
                NativeQuery query = session.createSQLQuery(sql);
//            query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                List<BigInteger> list= query.list();
                if(list!=null&&list.size()==1) {
                    total = Integer.parseInt(list.get(0).toString());
                }
            } catch (HibernateException e) {
                return 0;
//            e.printStackTrace();
            } finally {
                if(session!=null) {
                    session.close();
                }
            }
        }
        return total;
    }

    public boolean existTable(int serverId, String date) {
        Session session = dBManager.getLogDbSession(serverId);
        int total = 0;
        try {
            String sqlStr = "SELECT count(*) total FROM information_schema.TABLES WHERE table_name='"+getWholeTableName(date)+"'";
            NativeQuery query = session.createSQLQuery(sqlStr);
            List<BigInteger> list= query.list();
            if(list!=null&&list.size()==1) {
                total = Integer.parseInt(list.get(0).toString());
            }
        } catch (HibernateException e) {
            return false;
//            e.printStackTrace();
        } finally {
            if(session!=null) {
                session.close();
            }
        }
        if(total==1) {
            return true;
        } else {
            return false;
        }
    }
    public List<? extends BaseLog> searchOneDayLog(int serverId, String date, String condition,String limit) {
        Session session = dBManager.getLogDbSession(serverId);
        List<? extends BaseLog> logList = null;
        // 判断表存不存在
        boolean exist = existTable(serverId, date);
        if(exist) {
            String sqlStr = "select * from "+getWholeTableName(date)+" where 1=1 "+condition+" order by logTime ";
            if(limit!=null) {
                sqlStr += limit;
            }
            try {
                NativeQuery query = session.createSQLQuery(sqlStr);
                query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
                logList= query.list();
            } catch (HibernateException e) {
                e.printStackTrace();
            } finally {
                if(session!=null) {
                    session.close();
                }
            }
        }

        return logList;
    }
    public List<Integer> searchTotals(List<String> dates,int serverId, String condition) {
        List<Integer> totalList = new ArrayList<>();
        for(String date:dates) {
            int total = searchOneDayTotal(serverId,date,condition);
            totalList.add(total);
        }
        return totalList;
    }
    protected abstract String getTableName();

    private String getWholeTableName(String date) {
        return String.format("%s_%s", getTableName(), date);
    }
}
