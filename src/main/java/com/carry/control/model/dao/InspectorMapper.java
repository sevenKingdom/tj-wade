package com.carry.control.model.dao;

import com.carry.control.model.po.ConstructionPlan;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by songxianying on 17/8/5.
 */
@FeignClient(value = "service-model")
public interface InspectorMapper {
    @RequestMapping(value = "/construction-plan/updatePlanInspector")
    long updatePlanInspector (@RequestParam("inspectorid") Long inspectorid, @RequestParam("id") Long id);

    @RequestMapping(value = "/construction-plan/getListByInspectorid")
    public List<ConstructionPlan> getListByInspectorid(@RequestParam("inspectorid") Long inspectorid,
                                                       @RequestParam("startTime") Long startTime,
                                                       @RequestParam("endTime") Long endTime);

    @RequestMapping(value = "/construction-plan/getPLanById")
    public ConstructionPlan getByid(@RequestParam("id") Long id) ;

}
