package com.carry.control.controller;

import com.carry.commit.CommonResponse;
import com.carry.control.model.po.Configdata;
import com.carry.control.model.po.UserCreat;
import com.carry.control.service.TestService;
import com.carry.control.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songxianying on 17/7/23.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userServic;
    @RequestMapping(value = "/login" ,method = RequestMethod.POST)
    public CommonResponse<Map<String,Object>> getTest(@RequestParam("name") String name,
                                                     @RequestParam("password") String password ) {
        CommonResponse responseData = new CommonResponse();
        Map<String,Object> data = userServic.login(name,password);
        if (data != null) {
            responseData.setData(data);
            responseData.setStatus(200);
        } else {
            responseData.setStatus(400);
            responseData.setData(data);
            Map<String, Object> errerdata = new HashMap<String, Object>();
            errerdata.put("code",400);
            responseData.setErrorcode(errerdata);
        }

        return responseData;
    }
    @RequestMapping(value = "/logoff" ,method = RequestMethod.POST)
    public CommonResponse<Long> logoff(@RequestParam("id") long id) {
        CommonResponse responseData = new CommonResponse();
        Long num = userServic.logoff(id);
        responseData.setData(num);
        responseData.setStatus(200);
        return responseData;
    }
    @RequestMapping(value = "/createuser" ,method = RequestMethod.POST)
    public Map<String,Object> creatUser(@RequestBody UserCreat userCreat) {
        return userServic.creatUser(userCreat);
    }

}
