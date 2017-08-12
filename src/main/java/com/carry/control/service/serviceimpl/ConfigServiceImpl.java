package com.carry.control.service.serviceimpl;

import com.carry.control.model.dao.ConfigMapper;
import com.carry.control.model.po.Configdata;
import com.carry.control.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by songxianying on 17/7/30.
 */
@Service
public class ConfigServiceImpl implements ConfigService {
    @Autowired
    private ConfigMapper configMapper;
    @Override
    public Configdata getOrganize() {
        return configMapper.getTest();
    }

    @Override
    public Configdata getConfig(Integer id) {
        return configMapper.getConfig(id);
    }
}
