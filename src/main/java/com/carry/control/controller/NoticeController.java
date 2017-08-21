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

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by songxianying on 17/7/25.
 */
@RestController
@RequestMapping(value = "/notice")
public class NoticeController {


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
        //sendMail("","",data.get("mails"));
        //sendPhone("",data.get("phones"));
        responseData.setData(data);
        responseData.setStatus(200);
        return responseData;
    }

}
