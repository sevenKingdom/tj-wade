package com.carry.control.service.serviceimpl;

import com.carry.control.model.dao.UserMapper;
import com.carry.control.model.po.UserCreat;
import com.carry.control.model.po.UserData;
import com.carry.control.model.po.UserInfo;
import com.carry.control.service.UserService;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Type;
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

    @Override
    public Long updateUserScore(Long id, Integer score,String data) {
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Type type = new TypeToken<Map<Object, Object>>() {}.getType();
        Map<Object, Object> map = gson.fromJson(data, type);
        Map<Object, Object> mapdata = (Map<Object, Object>)map.get("item");
        score  = 0;
        for (Object key : mapdata.keySet()) {
            Integer integer = (Integer) mapdata.get(key);
            switch(integer) {
                case 1:
                    score += -40;
                    break;
                case 2:
                    score += -30;
                    break;
                case 3:
                    score += -20;
                    break;
                case 4:
                    score += -10;
                    break;
                default:
                    break;

            }
        }
        return userMapper.updateUserScore(id,score);
    }
}
