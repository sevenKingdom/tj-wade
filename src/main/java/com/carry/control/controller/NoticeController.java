package com.carry.control.controller;

import com.carry.commit.CommonResponse;
import com.carry.commit.HttpClientUtil;
import com.carry.commit.MailSendUtil;
import com.carry.control.model.po.MailInfo;
import com.carry.control.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Created by songxianying on 17/7/25.
 */
@RestController
@RequestMapping(value = "/notice")
public class NoticeController {
    //短信用户名
    private static String Uid = "tj_sms_service";

    //短信接口安全秘钥
    private static String Key = "ee5564a6ade02f0bb227";

    @Autowired
    NoticeService noticeService;

    @RequestMapping(value = "/getNoticedata" ,method = RequestMethod.POST)
    public CommonResponse<Map<Object, Object>> getNoticeData () {
        CommonResponse responseData = new CommonResponse();
        Map<Object,Object> data = noticeService.getNoticeData();
        responseData.setData(data);
        responseData.setStatus(200);
        return responseData;
    }

    @RequestMapping(value = "/updateNotice" ,method = RequestMethod.POST)
    public CommonResponse<Long> updateNotice (@RequestParam("id") Long id,
                                                              @RequestParam("data") String data) {
        CommonResponse responseData = new CommonResponse();
        responseData.setData(noticeService.updateNotice(id,data));
        responseData.setStatus(200);
        return responseData;
    }

    @RequestMapping(value = "/notice" ,method = RequestMethod.POST)
    public CommonResponse<Map<String,List<String>>> getData () {
        CommonResponse responseData = new CommonResponse();
        Map<String,Set<String>> data = noticeService.getNoticeData(0, "1.1.1");
        sendMail("","",data.get("mails"));
        sendPhone("",data.get("phones"));
        responseData.setData(data);
        responseData.setStatus(200);
        return responseData;
    }
    public String  sendMail(String title, String content, Set<String> mails) {
        StringBuffer mailstr = new StringBuffer();
        for (String str : mails) {
            mailstr.append(str + ",");
        }
        String mail = mailstr.substring(0,mailstr.length()-1); //发送对象的邮箱
        title = "实验邮件";
        content = "<div>全球委屈2饿却无</div><br/><hr/><div>恶趣味期望</div>";
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
    public String sendPhone(String smsText , Set<String> phones){

        StringBuffer phonestr = new StringBuffer();
        for (String str : phones) {
            phonestr.append(str + ",");
        }
        String smsMob = phonestr.substring(0,phonestr.length()-1); //发送对象的邮箱
        //手机号码，多个号码如13800000000,13800000001,13800000002
        //String smsMob = "18953820540,18202719658,13628669592";

        //短信内容
        smsText = "test";
        HttpClientUtil client = HttpClientUtil.getInstance();

        //UTF发送
        int result = client.sendMsgUtf8(Uid, Key, smsText, smsMob);

        if(result>0){
            return "ok"+result;
        }else{
            return client.getErrorMsg(result);
        }
    }
}
