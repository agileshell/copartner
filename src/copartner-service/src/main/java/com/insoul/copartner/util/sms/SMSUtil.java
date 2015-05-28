package com.insoul.copartner.util.sms;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.lang.StringUtils;

import com.insoul.copartner.constant.SettingConstant;
import com.insoul.copartner.util.PropertiesUtil;
import com.insoul.copartner.util.SystemUtil;

public final class SMSUtil {

    private static Properties messageProperties;

    static {
        messageProperties = PropertiesUtil.getProperties("/messageConfig.properties");
    }

    public static boolean sendSMS(final String to, final String content) {
        Map<String, String> settings = SystemUtil.getSettings(SettingConstant.GROUP_TYPE_GENERAL);
        String uid = settings.containsKey(SettingConstant.KEY_CONFIG_SMS_UID) ? settings
                .get(SettingConstant.KEY_CONFIG_SMS_UID) : "";
        String secretKey = settings.containsKey(SettingConstant.KEY_CONFIG_SMS_KEY) ? settings
                .get(SettingConstant.KEY_CONFIG_SMS_KEY) : "";

        try {
            HttpClient client = new HttpClient();
            PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn");
            post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=gbk");
            NameValuePair[] data = { new NameValuePair("Uid", uid), new NameValuePair("Key", secretKey),
                    new NameValuePair("smsMob", to), new NameValuePair("smsText", content) };
            post.setRequestBody(data);

            client.executeMethod(post);
            String result = new String(post.getResponseBodyAsString().getBytes("gbk"));
            post.releaseConnection();

            return Integer.parseInt(result) > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getSubject(String messageType) {
        return PropertiesUtil.get(messageProperties, "sms_" + messageType + "_subject");
    }

    public static String getContent(String messageType, final Map<String, Object> params) {
        String message = PropertiesUtil.get(messageProperties, "sms_" + messageType + "_content");
        if (StringUtils.isNotBlank(message) && null != params) {
            Set<Entry<String, Object>> entrySets = params.entrySet();
            for (Map.Entry<String, Object> memoPara : entrySets) {
                message = StringUtils.replace(message, memoPara.getKey(), memoPara.getValue().toString());
            }
        }
        return message;
    }
}
