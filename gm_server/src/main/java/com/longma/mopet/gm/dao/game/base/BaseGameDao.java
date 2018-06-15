package com.longma.mopet.gm.dao.game.base;

import com.longma.mopet.gm.manager.DBManager;
import com.longma.mopet.gm.model.log.base.BaseLog;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.List;

/**
 * @Author:Lvxingqing
 * @Description: 游戏数据库的基础dao
 * @Date:Create in 12:03 2018/5/10
 * @Modified By:
 */
public abstract class BaseGameDao{
    @Autowired
    private DBManager dBManager;

    public int searchTotal(int serverId, String condition) {
        Session session = dBManager.getGameDbSession(serverId);
        int total = 0;
        // 判断表存不存在
            try {

                String sql = "select count(1) total from "+getTableName()+" where 1=1 "+condition;
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
        return total;
    }

    public List<? extends BaseLog> search(int serverId, String condition, String limit) {
        Session session = dBManager.getGameDbSession(serverId);
        List logList = null;
        String sqlStr = "select * from "+getTableName()+" where 1=1 ";
        if(condition!=null) {
            sqlStr +=condition;
        }
        if(limit!=null) {
            sqlStr +=" "+limit;
        }
        // 判断表存不存在
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

        return logList;
    }
    protected abstract String getTableName();

}
