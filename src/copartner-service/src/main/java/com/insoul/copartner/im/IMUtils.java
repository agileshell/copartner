package com.insoul.copartner.im;

import org.apache.commons.lang3.StringUtils;

import com.insoul.copartner.constant.CommonConstant;
import com.insoul.copartner.im.packet.InRegister;
import com.insoul.copartner.im.packet.OutRegister;
import com.insoul.copartner.util.JsonUtil;

public class IMUtils {
    
    public static Long register(Long userId, String nick, String avatar) {
        Long imId = 0L;

        InRegister inregister = new InRegister();
        inregister.setParty_id(userId + "");
        inregister.setNick(nick);
        inregister.setPasswd(CommonConstant.IM_DEFAULT_PWD);
        inregister.setAvatar(avatar);

        String msg = JsonUtil.serialize(inregister);
        String returnMsg = IMSocketClient.sendMsg(msg);
        if (StringUtils.isNotEmpty(returnMsg)) {
            OutRegister obj = JsonUtil.deserialize(returnMsg, OutRegister.class);
            if (null != obj) {
                imId = obj.getMid();
            }
        }

        return imId;
    }
}
