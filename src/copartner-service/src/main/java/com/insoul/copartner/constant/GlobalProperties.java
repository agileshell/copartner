package com.insoul.copartner.constant;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.insoul.copartner.util.PropertiesUtil;

public final class GlobalProperties {

    private static final String PROPERTIES_PATH = "/global.properties";

    public static String CDN_PROVIDER = "local";

    public static String CDN_DOMAIN = "";

    public static String CDN_ACCESS_KEY = "";

    public static String CDN_SECRET_KEY = "";

    public static String CDN_BUCKET_NAME = "";

    public static String CDN_LOCAL_PATH = "";

    public static Boolean SMS_ENABLED = true;

    public static Integer SMS_CODE_LENGTH = 6;

    public static Integer SMS_DAILY_LIMIT = 10;

    public static Integer EMAIL_CODE_LENGTH = 32;

    public static String VERIFY_EMAIL_URL;

    public static String RESET_PASSWORD_URL;

    public static String DEFAULT_AVATAR_URL;

    public static String WEIBO_GET_TOKEN_INFO_URL;

    public static String WEIBO_GET_USER_INFO_URL;

    public static String QQ_GET_TOKEN_INFO_URL;

    public static String QQ_GET_USER_INFO_URL;

    public static Integer DEFAULT_LOCATION_ID = 0;

    public static String IM_SERVER;// = "123.57.55.59";

    public static Integer IM_PORT;// = 9100;
    
    public static Long IM_ROBOT_ID;

    static {
        Properties globalProperties = PropertiesUtil.getProperties(PROPERTIES_PATH);
        
        if (PropertiesUtil.get(globalProperties, "im.server.ip") != null) {
            IM_SERVER = StringUtils.defaultString(PropertiesUtil.get(globalProperties, "im.server.ip"), "123.57.55.59");
        }
        if (PropertiesUtil.get(globalProperties, "im.server.port") != null) {
            IM_PORT = NumberUtils.toInt(PropertiesUtil.get(globalProperties, "im.server.port"), 9100);
        }
        if (PropertiesUtil.get(globalProperties, "im.robot.id") != null) {
            IM_ROBOT_ID = NumberUtils.toLong(PropertiesUtil.get(globalProperties, "im.robot.id"), 10L);
        }
        
        if (PropertiesUtil.get(globalProperties, "cdn_provider") != null) {
            CDN_PROVIDER = PropertiesUtil.get(globalProperties, "cdn_provider");
        }
        if (PropertiesUtil.get(globalProperties, "cdn_domain") != null) {
            CDN_DOMAIN = PropertiesUtil.get(globalProperties, "cdn_domain");
        }
        if (PropertiesUtil.get(globalProperties, "cdn_access_key") != null) {
            CDN_ACCESS_KEY = PropertiesUtil.get(globalProperties, "cdn_access_key");
        }
        if (PropertiesUtil.get(globalProperties, "cdn_secret_key") != null) {
            CDN_SECRET_KEY = PropertiesUtil.get(globalProperties, "cdn_secret_key");
        }
        if (PropertiesUtil.get(globalProperties, "cdn_bucket_name") != null) {
            CDN_BUCKET_NAME = PropertiesUtil.get(globalProperties, "cdn_bucket_name");
        }
        if (PropertiesUtil.get(globalProperties, "cdn_local_path") != null) {
            CDN_LOCAL_PATH = PropertiesUtil.get(globalProperties, "cdn_local_path");
        }

        if (PropertiesUtil.get(globalProperties, "sms_enabled") != null) {
            SMS_ENABLED = Boolean.parseBoolean(PropertiesUtil.get(globalProperties, "sms_enabled"));
        }
        if (PropertiesUtil.get(globalProperties, "sms_code_length") != null) {
            SMS_CODE_LENGTH = Integer.parseInt(PropertiesUtil.get(globalProperties, "sms_code_length"));
        }
        if (PropertiesUtil.get(globalProperties, "sms_daily_limit") != null) {
            SMS_DAILY_LIMIT = Integer.parseInt(PropertiesUtil.get(globalProperties, "sms_daily_limit"));
        }

        if (PropertiesUtil.get(globalProperties, "email_code_length") != null) {
            EMAIL_CODE_LENGTH = Integer.parseInt(PropertiesUtil.get(globalProperties, "email_code_length"));
        }
        if (PropertiesUtil.get(globalProperties, "verify_email_url") != null) {
            VERIFY_EMAIL_URL = PropertiesUtil.get(globalProperties, "verify_email_url");
        }
        if (PropertiesUtil.get(globalProperties, "reset_password_url") != null) {
            RESET_PASSWORD_URL = PropertiesUtil.get(globalProperties, "reset_password_url");
        }
        if (PropertiesUtil.get(globalProperties, "default_avatar_url") != null) {
            DEFAULT_AVATAR_URL = PropertiesUtil.get(globalProperties, "default_avatar_url");
        }

        if (PropertiesUtil.get(globalProperties, "weibo_get_token_info_url") != null) {
            WEIBO_GET_TOKEN_INFO_URL = PropertiesUtil.get(globalProperties, "weibo_get_token_info_url");
        }
        if (PropertiesUtil.get(globalProperties, "weibo_get_user_info_url") != null) {
            WEIBO_GET_USER_INFO_URL = PropertiesUtil.get(globalProperties, "weibo_get_user_info_url");
        }
        if (PropertiesUtil.get(globalProperties, "qq_get_token_info_url") != null) {
            QQ_GET_TOKEN_INFO_URL = PropertiesUtil.get(globalProperties, "qq_get_token_info_url");
        }

        if (PropertiesUtil.get(globalProperties, "default_location_id") != null) {
            DEFAULT_LOCATION_ID = Integer.parseInt(PropertiesUtil.get(globalProperties, "default_location_id"));
        }
    }

    private GlobalProperties() {
        // empty
    }
}
