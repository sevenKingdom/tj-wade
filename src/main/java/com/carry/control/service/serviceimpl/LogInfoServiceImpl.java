package com.carry.control.service.serviceimpl;


import com.carry.control.model.dao.LogInfoMapper;
import com.carry.control.model.po.Loginfo;
import com.carry.control.service.LogInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by songxianying on 17/8/29.
 */
@Service
public class LogInfoServiceImpl implements LogInfoService {
    @Autowired
    private LogInfoMapper logInfoMapper;

    @Override
    public List<Loginfo> getListOneDay(String department) {
        return logInfoMapper.getListOneDay(department);
    }
}
