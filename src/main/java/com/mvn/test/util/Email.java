/*package com.mvn.test.util;

import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeUtility;


public class Email {

    *//**
     * 
     * @param map
     *      username: 用户名
     *      password: 密码<授权码>
     *      fromUser: 发件人
     *      toUser: 收件人
     *      title: 标题
     *      content: 内容
     *      
     * @return ajaxResult 
     *      
     *//*
    public static void sendEmail(HashMap<String, String> map) {

        try {
            if (null != map && !map.isEmpty()) {

                final String username = map.get("username");
                final String password = map.get("password");
                String fromUser = map.get("fromUser");
                String toUser = map.get("toUser");
                String title = map.get("title");
                String content = map.get("content");
                String nick = map.get("nick");

                if (null != username && null != password && null != fromUser && null != toUser && null != content) {
                    if (!"".equals(username.trim()) && !"".equals(password.trim()) && !"".equals(fromUser.trim())
                            && !"".equals(toUser.trim()) && !"".equals(content.trim())) {

                        Properties props = new Properties();
                        // 设置服务器主机名
                        props.setProperty("mail.smtp.host", "smtp.mxhichina.com");
                        // 设置为认证
                        props.setProperty("mail.smtp.auth", "true");
                        
                         * Authenticator是一个接口,表示认证器,即校验客户端的身份
                         * 实现这个接口需要使用账户和密码<授权码>
                         
                        Authenticator auth = new Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                // 用户名和密码, 密码为授权码
                                return new PasswordAuthentication(username, password);
                            }
                        };
                        
                         * 创建session
                         
                        Session session = Session.getInstance(props, auth);
                        
                         * 创建MimeMessage 
                         *      RecipientType.TO 正常
                         *      RecipientType.CC 抄送
                         *      RecipientType.BCC 暗送
                         
                        MimeMessage msg = new MimeMessage(session);
                        // 设置发件人
                        nick = MimeUtility.encodeText(nick);
                        msg.setFrom(new InternetAddress(nick + "<" + fromUser + ">"));
                        // 设置收件人
                        msg.setRecipient(RecipientType.TO, new InternetAddress(toUser));
                        // 邮件标题
                        msg.setSubject(title);
                        // 设置邮件内容
                        msg.setContent(content, "text/html;charset=utf-8");
                        
                         * 发邮件
                         
                        try {
                            Transport.send(msg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    
}
*/