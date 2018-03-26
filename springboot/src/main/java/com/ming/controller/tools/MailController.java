package com.ming.controller.tools;


import com.ming.service.tools.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 邮件相关功能控制器
 *
 * @author ming
 * @date 2017-09-03
 */
@Controller
@RequestMapping(value = "mail")
public class MailController {


    @Autowired
    private MailSendService mailSendService;

    @PostMapping(value = "send-author")
    public String sendAuthor(String name, String subject, String text) {
        mailSendService.sendSimpleMail(name + "::" + subject, text + System.currentTimeMillis());
        return "info";
    }
}
