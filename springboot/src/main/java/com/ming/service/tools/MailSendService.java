package com.ming.service.tools;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.ming.core.utils.SystemParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * 邮件发送服务
 *
 * @author ming
 * @date 2017-11-06 11:58
 */
@Service
@Slf4j
public class MailSendService {

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送简单文字邮件方法
     *
     * @param subject
     * @param text
     * @author ming
     * @date 2017-08-28 15点
     */
    public void sendSimpleMail(String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(SystemParam.MAIL_FORM_USER);
        message.setTo(SystemParam.MAIL_SEND_USER_GROUP);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    /**
     * 发送html 类型邮件
     *
     * @param subject
     * @param htmlText
     * @author ming
     * @date 2017-08-28 15点
     */
    public void sendHtmlMail(String subject, String htmlText) {
        MimeMessage message;
        message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(SystemParam.MAIL_FORM_USER);
            helper.setTo(SystemParam.MAIL_SEND_USER_GROUP);
            helper.setSubject(subject);
            helper.setText(htmlText, true);
        } catch (MessagingException e) {
            log.error("发送html邮件失败！参数:" + JSON.toJSONString(message));
            e.printStackTrace();
        }
        javaMailSender.send(Preconditions.checkNotNull(message, "消息不能为空"));
    }


    /**
     * 带附件的 邮件
     *
     * @param subject
     * @param text
     * @param file
     * @author ming
     * @date 2017-08-28 14点
     */
    public void sendFileMail(String subject, String text, File file) {
        MimeMessage message;
        message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(SystemParam.MAIL_FORM_USER);
            helper.setTo(SystemParam.MAIL_SEND_USER_GROUP);
            helper.setSubject(subject);
            helper.setText(text);
            helper.addAttachment(file.getName(), file);
        } catch (MessagingException e) {
            log.error("发送带附件邮件失败！参数:" + JSON.toJSONString(message));
            e.printStackTrace();
        }
        javaMailSender.send(Preconditions.checkNotNull(message, "消息不能为空"));
    }
}
