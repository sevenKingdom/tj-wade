package com.carry.control.service.serviceimpl;

import com.carry.control.model.dao.UserMapper;
import com.carry.control.model.po.UserCreat;
import com.carry.control.model.po.UserData;
import com.carry.control.model.po.UserInfo;
import com.carry.control.service.UserService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by songxianying on 17/7/23.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String,Object> login(String name , String password){
        Map<String,Object> data = null;
        UserCreat userdata =  userMapper.login(name,password);
        if ( userdata != null ) {
            data = Maps.newHashMap();
            data.put("id",userdata.getUserData().getId());
            data.put("token",userdata.getUserData().getToken());
            data.put("role",userdata.getUserData().getRole());
            data.put("name",userdata.getUserInfo().getName());
            data.put("phone",userdata.getUserInfo().getPhone());
            data.put("mail",userdata.getUserInfo().getMail());
        }
        return data;
    }
    @Override
    public long logoff( long id) {
        return userMapper.logoff(id);
    }
    @Override
    public Map<String,Object> creatUser (UserCreat userCreat) {
       return  userMapper.creatUser(userCreat);
    }
}
