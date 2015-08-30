package com.insoul.copartner.constant;

public interface SettingConstant {

    String GROUP_TYPE_GENERAL = "config";

    String GROUP_TYPE_SMTP = "smtp";

    String GROUP_TYPE_IMAGE = "image";

    String GROUP_TYPE_VEDIO = "vedio";

    String GROUP_TYPE_APP_INFO = "app_info";

    // general config
    String KEY_CONFIG_SMS_UID = "config_sms_uid";

    String KEY_CONFIG_SMS_KEY = "config_sms_key";

    // smtp
    String MAIL_SMTP_HOST = "smtp_host";

    String MAIL_SMTP_PORT = "smtp_port";

    String SMTP_SSL = "smtp_ssl";

    String MAIL_SMTP_USERNAME = "smtp_username";

    String MAIL_SMTP_PASSWORD = "smtp_password";

    String MAIL_FROM_EMAIL = "from_email";

    String MAIL_FROM_NAME = "from_name";

    // image
    String IMAGE_TYPE_LIMIT = "image_type_limit";

    String IMAGE_MAX_SIZE = "image_max_size";

    String IMAGE_MIN_SIZE = "image_min_size";

    String IMAGE_DIMENSION_MAX_WIDTH = "image_dimension_max_width";

    String IMAGE_DIMENSION_MAX_HEIGHT = "image_dimension_max_height";

    String IMAGE_DIMENSION_MIN_WIDTH = "image_dimension_min_width";

    String IMAGE_DIMENSION_MIN_HEIGHT = "image_dimension_min_height";

    // vedio
    String VEDIO_TYPE_LIMIT = "vedio_type_limit";

    String VEDIO_MAX_SIZE = "vedio_max_size";

    String VEDIO_MIN_SIZE = "vedio_min_size";
}
