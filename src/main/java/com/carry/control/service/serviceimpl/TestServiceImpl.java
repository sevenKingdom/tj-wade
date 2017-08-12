package com.carry.control.service.serviceimpl;


import com.carry.control.model.dao.TestMapper;
import com.carry.control.model.po.Configdata;
import com.carry.control.model.po.Test;
import com.carry.control.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by songxianying on 17/7/12.
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;
    @Override
    public Configdata findAccountList(Configdata configdata) {
        return testMapper.getTest(configdata);
    }
}
