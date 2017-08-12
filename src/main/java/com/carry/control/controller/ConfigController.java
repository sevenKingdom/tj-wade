package com.carry.control.controller;

import com.carry.commit.CommonResponse;
import com.carry.control.service.ConfigService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * Created by songxianying on 17/7/30.
 */
@RestController
@RequestMapping(value = "/config")
public class ConfigController {
    @Autowired
    ConfigService configService;

    @RequestMapping(value = "/getOrganize",method = RequestMethod.POST)
    public CommonResponse<Map> getOrganize(){
        CommonResponse responseData = new CommonResponse();
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Type type = new TypeToken<Map<Object, Object>>() {}.getType();
        Map<Object, Object> map = gson.fromJson(configService.getOrganize().getData(), type);
        responseData.setData(map);
        responseData.setStatus(200);
        return responseData;
    }

    public void test(){
        configService.getConfig(2).getData();
    }
}
