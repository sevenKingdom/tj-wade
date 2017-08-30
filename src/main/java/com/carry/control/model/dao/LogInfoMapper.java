package com.carry.control.model.dao;

import com.carry.control.model.po.Loginfo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by songxianying on 17/8/29.
 */
@FeignClient(value = "service-model")
public interface LogInfoMapper {

    @RequestMapping(value = "/mysql/getListOneDay" ,method = RequestMethod.POST)
    public List<Loginfo> getListOneDay(@RequestParam("department") String department);
}
