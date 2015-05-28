package com.insoul.copartner.constant;

public interface SettingConstant {

    String GROUP_TYPE_GENERAL = "config";

    String GROUP_TYPE_SMTP = "smtp";

    String GROUP_TYPE_IMAGE = "image";

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
    String FILE_TYPE_LIMIT = "file_type_limit";

    String FILE_MAX_SIZE = "file_max_size";

    String FILE_MIN_SIZE = "file_min_size";

    String FILE_DIMENSION_MAX_WIDTH = "file_dimension_max_width";

    String FILE_DIMENSION_MAX_HEIGHT = "file_dimension_max_height";

    String FILE_DIMENSION_MIN_WIDTH = "file_dimension_min_width";

    String FILE_DIMENSION_MIN_HEIGHT = "file_dimension_min_height";
}
