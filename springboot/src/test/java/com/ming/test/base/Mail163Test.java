package com.ming.test.base;

import com.google.common.base.Preconditions;
import com.ming.Start;
import com.ming.core.utils.ResourcesUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * spring boot mail测试
 * 163的password 需要使用授权码
 * 163中 尽量使用中文  避免被认为垃圾邮件
 *
 * @author ming
 * @date 2017-08-28 11点
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Start.class)
@Ignore
public class Mail163Test {
    String mail = "18120580001@163.com";
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 测试简单邮件发送
     *
     * @author ming
     * @date 2017-08-28 11点
     */
    @Test
    public void testSimpleMail() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail);
        message.setTo(mail);
        message.setSubject("主题:简单邮件测试主题 ");
        message.setText("内容:简单测试内容");
        javaMailSender.send(message);
    }

    /**
     * html邮件
     *
     * @author ming
     * @date 2017-08-28 14点
     */
    @Test
    public void testHTMLMail() {
        MimeMessage message;
        message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mail);
            helper.setTo(mail);
            helper.setSubject("主题:html邮件");
            StringBuilder sb = new StringBuilder();
            sb.append("<h1>大大大</h1>")
                    .append("<p style = 'color:#F00'>红色</p>");
            helper.setText(sb.toString(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        javaMailSender.send(Preconditions.checkNotNull(message, "消息不能为空"));
    }

    /**
     * 带附件的 邮件
     * 这个附件 必须是要存在路径下的  传入file类型就行
     *
     * @author ming
     * @date 2017-08-28 14点
     */
    @Test
    @Ignore
    public void sendMailFile() {
        MimeMessage message;
        message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(mail);
            helper.setTo(mail);
            helper.setSubject("主题:带附件的邮件");
            helper.setText("附件附件 附件");
            File file = new File(ResourcesUtils.CLASS_PATH + "banner.txt");
            helper.addAttachment("read文件.txt", file);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        if (message != null) {
            javaMailSender.send(message);
        }
    }
}
