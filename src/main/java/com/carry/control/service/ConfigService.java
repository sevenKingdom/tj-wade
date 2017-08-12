package com.carry.control.service;

import com.carry.control.model.po.Configdata;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by songxianying on 17/7/30.
 */
public interface ConfigService {

    Configdata getOrganize();

    Configdata getConfig(Integer id) ;
}
