package com.carry.util;

import com.carry.control.model.dao.UserMapper;
import com.carry.control.model.po.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by songxianying on 17/8/4.
 */
@Service
public class IdentityVerification {
    @Autowired
    private UserMapper userMapper;

    public UserData verification (String token) {
        return userMapper.verification(token);
    }

    public String shortVerification( String token) {
        return userMapper.shortVerification(token);
    }

    public String getPlanAuthor(Long id) {
        return userMapper.getPlanAuthor(id);
    }
}
