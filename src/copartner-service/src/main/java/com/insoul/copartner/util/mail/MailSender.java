package com.insoul.copartner.util.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {

    private Session session;

    private Properties javaMailProperties = new Properties();

    private String fromAddress;

    private String username;

    private String password;

    private String nickname;

    private boolean isValidateAuth = true;

    public boolean send(final String subject, final String content, final String[] mailTo) {
        try {
            Message mailMessage = new MimeMessage(getSession());

            try {
                String nick = javax.mail.internet.MimeUtility.encodeText(this.getNickname());
                mailMessage.setFrom(new InternetAddress(this.getFromAddress(), nick));
            } catch (Exception e) {
                mailMessage.setFrom(new InternetAddress(this.getFromAddress()));
            }

            for (int i = 0; i < mailTo.length; i++) {
                mailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo[i]));
            }
            mailMessage.setSubject(subject);
            mailMessage.setSentDate(new Date());

            Multipart mainPart = new MimeMultipart();
            BodyPart html = new MimeBodyPart();
            html.setContent(content, "text/html; charset=UTF-8");
            mainPart.addBodyPart(html);
            mailMessage.setContent(mainPart);

            Transport.send(mailMessage);
            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Authenticator getAuthenticator() {
        Authenticator authenticator = null;
        if (this.isValidateAuth()) {
            authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(getUsername(), getPassword());
                }
            };
        }
        return authenticator;
    }

    public synchronized Session getSession() {
        if (this.session == null) {
            this.session = Session.getDefaultInstance(this.javaMailProperties, getAuthenticator());
        }
        return this.session;
    }

    public Properties getJavaMailProperties() {
        return javaMailProperties;
    }

    public void setJavaMailProperties(Properties javaMailProperties) {
        this.javaMailProperties = javaMailProperties;
        synchronized (this) {
            this.session = null;
        }
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isValidateAuth() {
        return isValidateAuth;
    }

    public void setValidateAuth(boolean isValidateAuth) {
        this.isValidateAuth = isValidateAuth;
    }

}
