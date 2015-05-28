package com.insoul.copartner.dao;

import com.insoul.copartner.domain.Message;

public interface IMessageDao extends IBaseDao<Message, Long> {

    Integer getMessageDailyCount(String account, Integer channelId);
}
