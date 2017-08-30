package com.carry.control.controller;

import com.carry.commit.CommonResponse;
import com.carry.control.model.po.Loginfo;
import com.carry.control.service.ConfigService;
import com.carry.control.service.LogInfoService;
import com.carry.util.IdentityVerification;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by songxianying on 17/8/29.
 */
@RestController
@RequestMapping(value = "/log")
public class LogInfoController {
    @Autowired
    private IdentityVerification identityVerification;

    @Autowired
    LogInfoService logInfoService;

    @Autowired
    ConfigService configService;

    @RequestMapping(value = "/getListOneDay" ,method = RequestMethod.POST)
    public CommonResponse<List> getListOneDay(@RequestParam("token") String token) {
        CommonResponse responseData = new CommonResponse();
        String userVer = identityVerification.shortVerification(token);
        if (userVer != null ) {
            List<Loginfo> data = logInfoService.getListOneDay(userVer);
            for (Loginfo po : data) {
                String str = findBydepartment(po.getDepartment());
                po.setDepartment(str);
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String dateString = formatter.format(po.getTime());
                po.setDate(dateString);
            }
            responseData.setData(data);
            responseData.setStatus(200);
            responseData.setTotal_count(data.size());
        } else {
            responseData.setStatus(400);
            responseData.setErrormessage("请重新登录！");
        }
        return  responseData;
    }
    public String findBydepartment (String department) {
        StringBuffer sb = new StringBuffer();
        String[] departments = department.split("\\.");
        String data = configService.getOrganize().getData();
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Type type = new TypeToken<Map<Object, Object>>() {}.getType();
        Map<Object, Object> map = gson.fromJson(data, type);
        Map<Object, Object> mapll = map;
        for (String key : departments) {
            mapll = (Map<Object, Object>)mapll.get(key);
            sb.append(mapll.get("name")+" ");
            mapll = (Map<Object, Object>)mapll.get("lowerlevel");

        }

        return sb.toString();
    }
}
