package com.carry.control.service;

import com.carry.control.model.po.UserCreat;


import java.util.Map;

/**
 * Created by songxianying on 17/7/23.
 */
public interface UserService {
    Map<String,Object> login(String name , String password);
    long logoff( long id);
    Map<String,Object> creatUser (UserCreat userCreat) ;
    Long updateUserScore(Long id, Integer score,String data);
}
