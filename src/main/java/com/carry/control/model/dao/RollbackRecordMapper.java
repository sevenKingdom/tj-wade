package com.carry.control.model.dao;

import com.carry.control.model.po.RollbackRecord;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by songxianying on 17/8/27.
 */
@FeignClient(value = "service-model")
public interface RollbackRecordMapper {

    @RequestMapping(value = "/mysql/addRollbackRecord" ,method = RequestMethod.POST)
    public int addRollbackRecord(@RequestBody RollbackRecord rollbackRecord) ;

    @RequestMapping(value = "/mysql/findByDepartment" ,method = RequestMethod.POST)
    public List<RollbackRecord> findByDepartment (@RequestParam("department") String department) ;
}
