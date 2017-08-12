package com.carry.control.model.dao;

import com.carry.control.model.po.Configdata;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by songxianying on 17/7/30.
 */
@FeignClient(value = "service-model")
public interface ConfigMapper {
    @PostMapping(value = "/mysql/getOrganize")
    public Configdata getTest( );

    @RequestMapping(value = "/mysql/getConfig",method = RequestMethod.POST)
    public Configdata getConfig(@RequestParam("id") Integer id) ;
}
