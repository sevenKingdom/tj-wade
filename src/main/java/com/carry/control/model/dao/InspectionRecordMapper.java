package com.carry.control.model.dao;

import com.carry.control.model.po.InspectionRecord;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by songxianying on 17/8/13.
 */
@FeignClient(value = "service-model")
public interface InspectionRecordMapper {
    @RequestMapping(value = "/mysql/addInspectionRecord" ,method = RequestMethod.POST)
    public Long addInspectionRecord(@RequestBody InspectionRecord inspectionRecord);
}
