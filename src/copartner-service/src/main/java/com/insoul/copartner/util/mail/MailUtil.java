package com.insoul.copartner.util.mail;

import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.insoul.copartner.constant.SettingConstant;
import com.insoul.copartner.util.PropertiesUtil;
import com.insoul.copartner.util.SystemUtil;

public final class MailUtil {

    private static MailSender instance = null;

    private static VelocityEngine velocityEngine = null;

    private static Properties messageProperties;

    static {
        messageProperties = PropertiesUtil.getProperties("/messageConfig.properties");
    }

    public static MailSender getMailSenderInstance() {
        if (null == instance) {
            synchronized (JavaMailSenderImpl.class) {
                if (null == instance) {
                    Map<String, String> settings = SystemUtil.getSettings(SettingConstant.GROUP_TYPE_SMTP);
                    String host = settings.containsKey(SettingConstant.MAIL_SMTP_HOST) ? settings
                            .get(SettingConstant.MAIL_SMTP_HOST) : null;
                    String port = settings.containsKey(SettingConstant.MAIL_SMTP_PORT) ? settings
                            .get(SettingConstant.MAIL_SMTP_PORT) : null;
                    String username = settings.containsKey(SettingConstant.MAIL_SMTP_USERNAME) ? settings
                            .get(SettingConstant.MAIL_SMTP_USERNAME) : null;
                    String password = settings.containsKey(SettingConstant.MAIL_SMTP_PASSWORD) ? settings
                            .get(SettingConstant.MAIL_SMTP_PASSWORD) : null;
                    String nickname = settings.containsKey(SettingConstant.MAIL_FROM_NAME) ? settings
                            .get(SettingConstant.MAIL_FROM_NAME) : null;
                    String fromEmail = settings.containsKey(SettingConstant.MAIL_FROM_EMAIL) ? settings
                            .get(SettingConstant.MAIL_FROM_EMAIL) : null;
                    String smtpSSL = settings.containsKey(SettingConstant.SMTP_SSL) ? settings
                            .get(SettingConstant.SMTP_SSL) : null;

                    if (StringUtils.isNotEmpty(host) && StringUtils.isNotEmpty(port)
                            && StringUtils.isNotEmpty(username) && StringUtils.isNotEmpty(password)
                            && StringUtils.isNotEmpty(nickname)) {
                        instance = new MailSender();
                        instance.setUsername(username);
                        instance.setPassword(password);
                        instance.setFromAddress(fromEmail);
                        instance.setNickname(nickname);
                        instance.setValidateAuth(true);

                        Properties properties = new Properties();
                        properties.put("mail.smtp.host", host);
                        properties.put("mail.smtp.port", port);
                        properties.put("mail.smtp.auth", true);
                        if (smtpSSL.equals("true")) {
                            properties.put("mail.smtp.starttls.enable", true);
                        }
                        if (host.indexOf("smtp.gmail.com") >= 0) {
                            properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
                            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
                            properties.setProperty("mail.smtp.port", "465");
                            properties.setProperty("mail.smtp.socketFactory.port", "465");
                        }
                        instance.setJavaMailProperties(properties);
                    }
                }
            }
        }

        return instance;
    }

    public static boolean sendMail(final String to, final String subject, final String content) {
        try {
            return MailUtil.getMailSenderInstance().send(subject, content, new String[] { to });
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean sendMail(final String[] to, final String subject, final String content) {
        try {
            return MailUtil.getMailSenderInstance().send(subject, content, to);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean sendMail(final String to, final String messageType, final Map<String, Object> params) {
        try {
            return sendMail(new String[] { to }, messageType, params);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean sendMail(final String[] to, final String messageType, final Map<String, Object> params) {
        try {
            return MailUtil.getMailSenderInstance().send(getMailSubject(messageType),
                    getMailContent(messageType, params), to);
        } catch (Exception e) {
            return false;
        }
    }

    public static String getMailSubject(String messageType) {
        return PropertiesUtil.get(messageProperties, "email_" + messageType + "_subject");
    }

    public static String getMailContent(String messageType, final Map<String, Object> params) {
        VelocityContext context = new VelocityContext();
        if (null != params) {
            Set<Entry<String, Object>> entrySets = params.entrySet();
            for (Map.Entry<String, Object> memoPara : entrySets) {
                context.put(memoPara.getKey(), memoPara.getValue().toString());
            }
        }
        StringWriter writer = new StringWriter();
        String vmfile = PropertiesUtil.get(messageProperties, "email_" + messageType + "_vmfile");
        getVelocityEngineInstance().mergeTemplate(vmfile, "UTF-8", context, writer);

        return writer.toString();
    }

    public static VelocityEngine getVelocityEngineInstance() {
        if (null == velocityEngine) {
            synchronized (VelocityEngine.class) {
                if (null == velocityEngine) {
                    Properties properties = new Properties();
                    properties.setProperty("resource.loader", "class");
                    properties.setProperty("class.resource.loader.class",
                            "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

                    properties.setProperty("runtime.log.logsystem.class",
                            "org.apache.velocity.runtime.log.Log4JLogChute");
                    properties.setProperty("runtime.log.logsystem.log4j.logger", "velocity");

                    velocityEngine = new VelocityEngine(properties);
                }
            }
        }

        return velocityEngine;
    }
}
