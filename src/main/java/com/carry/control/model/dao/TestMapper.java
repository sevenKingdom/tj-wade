package com.carry.control.model.dao;


import com.carry.control.model.po.Configdata;
import com.carry.control.model.po.Test;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by songxianying on 17/7/12.
 */
@FeignClient(value = "service-model")
public interface TestMapper {
    @GetMapping(value = "/mysql/test")
    public Configdata getTest(@RequestBody Configdata configdata );
}
