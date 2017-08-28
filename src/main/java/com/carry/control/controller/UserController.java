package com.carry.control.controller;

import com.carry.commit.CommonResponse;
import com.carry.control.model.po.Configdata;
import com.carry.control.model.po.UserCreat;
import com.carry.control.service.TestService;
import com.carry.control.service.UserService;
import com.carry.util.IdentityVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by songxianying on 17/7/23.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IdentityVerification identityVerification;
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
            responseData.setErrormessage("用户名或密码错误！");
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

    @RequestMapping(value = "/getAllUser" ,method = RequestMethod.POST)
    public CommonResponse<List<Map<String,Object>>> getAllUser() {
        CommonResponse responseData = new CommonResponse();
        List<Map<String,Object>> data = userServic.getAllUser();
        responseData.setData(data);
        responseData.setErrormessage("");
        responseData.setStatus(200);
        responseData.setTotal_count(data.size());
        return responseData;
    }
    @RequestMapping(value = "/updateUserPassword" ,method = RequestMethod.POST)
    public CommonResponse<Integer> updateUserPassword (@RequestParam("token") String token,@RequestParam("id") Long id, @RequestParam("password") String password){
        CommonResponse responseData = new CommonResponse();
        String userVer = identityVerification.shortVerification(token);
        if (userVer != null ) {
            Integer data = userServic.updateUserPassword(id,password);
            responseData.setData(data);
            responseData.setErrormessage("");
            responseData.setStatus(200);
        } else {
            responseData.setStatus(400);
            responseData.setErrormessage("请重新登录！");
        }
        return responseData;
    }

    @RequestMapping(value = "/updateUser" ,method = RequestMethod.POST)
    public CommonResponse<Integer> updateUser (@RequestParam("token") String token,@RequestParam("id") Long id,
                                               @RequestParam("name") String name, @RequestParam("phone") String phone,
                                               @RequestParam("password") String password, @RequestParam("mail") String mail) {
        CommonResponse responseData = new CommonResponse();
        String userVer = identityVerification.shortVerification(token);
        if (userVer != null ) {
            Integer data = userServic.updateUser(id, name, phone, password, mail);
            responseData.setData(data);
            responseData.setErrormessage("");
            responseData.setStatus(200);
        } else {
            responseData.setStatus(400);
            responseData.setErrormessage("请重新登录！");
        }
        return responseData;
    }

    @RequestMapping(value = "/deleteUser" ,method = RequestMethod.POST)
    public CommonResponse<Integer> deleteUser (@RequestParam("id") Long id,@RequestParam("token") String token) {
        CommonResponse responseData = new CommonResponse();
        String userVer = identityVerification.shortVerification(token);
        if (userVer != null ) {
            Integer data = userServic.deleteUser(id);
            responseData.setData(data);
            responseData.setErrormessage("");
            responseData.setStatus(200);
        } else {
            responseData.setStatus(400);
            responseData.setErrormessage("用户已被删除！");
        }
        return responseData;
    }

    @RequestMapping(value = "/findBydepartment" ,method = RequestMethod.POST)
    public CommonResponse<List<Map<String,Object>>> findBydepartment (@RequestParam("token") String token) {
        CommonResponse responseData = new CommonResponse();
        String userVer = identityVerification.shortVerification(token);
        if (userVer != null ) {
            List<Map<String,Object>> data = userServic.findBydepartment(userVer);
            responseData.setData(data);
            responseData.setErrormessage("");
            responseData.setStatus(200);
        } else {
            responseData.setStatus(400);
            responseData.setErrormessage("用户已被删除！");
        }
        return responseData;
    }
}
