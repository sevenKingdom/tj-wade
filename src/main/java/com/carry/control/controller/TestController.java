package com.carry.control.controller;

import com.carry.commit.MailSendUtil;
import com.carry.control.model.po.Configdata;
import com.carry.control.model.po.MailInfo;
import com.carry.control.model.po.Test;
import com.carry.control.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by songxianying on 17/7/15.
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    TestService testServic;
    @RequestMapping(value = "/test" ,method = RequestMethod.POST)
    public Configdata getTest(@RequestBody  Configdata configdata) {

        return testServic.findAccountList(configdata);
    }
    @RequestMapping(value = "/mail")
    public String  getTestMail() {
        String mail = "songxianying@baidu.com"; //发送对象的邮箱
        String title = "test";
        String content = "<div>dasdsadasdas</div><br/><hr/><div>dsadasdasdasdas</div>";
        MailInfo info = new MailInfo();
        info.setToAddress(mail);
        info.setSubject(title);
        info.setContent(content);
        try {
            MailSendUtil.sendHtmlMail(info);
        } catch (Exception e) {
            return e+title+"'的邮件发送失败！";
        }
        return "ok";
    }
}
