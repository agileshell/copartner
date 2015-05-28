package com.insoul.copartner.dao.impl;

import java.util.Calendar;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.insoul.copartner.dao.IMessageDao;
import com.insoul.copartner.domain.Message;

@Repository
public class MessageDaoImpl extends BaseDaoImpl<Message, Long> implements IMessageDao {

    @Override
    public Integer getMessageDailyCount(String account, Integer channelId) {
        Query query = getEM().createNamedQuery("Message.getMessageCountByAccountAndChannel");

        Calendar todayStart = Calendar.getInstance();
        todayStart.set(Calendar.HOUR_OF_DAY, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        query.setParameter("account", account).setParameter("channelId", channelId)
                .setParameter("startDateTime", todayStart.getTime()).setParameter("endDateTime", todayEnd.getTime());
        Long count = (Long) query.getSingleResult();

        return count.intValue();
    }

}
