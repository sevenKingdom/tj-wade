package com.carry.control.controller;

import com.carry.commit.CommonResponse;
import com.carry.commit.JsonRequest;
import com.carry.control.model.po.RollbackRecord;
import com.carry.control.service.InspectorService;
import com.carry.control.service.RollbackRecordService;
import com.carry.util.IdentityVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by songxianying on 17/8/27.
 */
@RestController
@RequestMapping("/rollback")
public class RollbackRecordController {
    @Autowired
    RollbackRecordService rollbackRecordService;
    @Autowired
    private IdentityVerification identityVerification;
    @Autowired
    private InspectorService inspectorService;

    @RequestMapping(value = "/addRollbackRecord" ,method = RequestMethod.POST)
    public CommonResponse<Long> addRollbackRecord(@RequestBody JsonRequest<RollbackRecord> rollbackRecord) {
        CommonResponse responseData = new CommonResponse();
        String userVer = identityVerification.shortVerification(rollbackRecord.getToken());
        if (userVer != null ) {
            rollbackRecord.getData().setDepartment(userVer);
            Long data =inspectorService.updatePlanInspector(null,rollbackRecord.getData().getPlanid());
            data += rollbackRecordService.addRollbackRecord(rollbackRecord.getData());
            responseData.setData(data);
            responseData.setErrormessage("");
            responseData.setStatus(200);
        } else {
            responseData.setStatus(400);
            responseData.setErrormessage("请重新登录！");
        }
        return responseData;
    }

    @RequestMapping(value = "/findByDepartment" ,method = RequestMethod.POST)
    public CommonResponse<List<RollbackRecord>> findByDepartment (@RequestParam("token") String token, @RequestParam("department") String department) {
        CommonResponse responseData = new CommonResponse();
        String userVer = identityVerification.shortVerification(token);
        if (userVer != null ) {
            List<RollbackRecord> data = rollbackRecordService.findByDepartment(department);
            responseData.setData(data);
            responseData.setErrormessage("");
            responseData.setStatus(200);
            responseData.setTotal_count(data.size());
        } else {
            responseData.setStatus(400);
            responseData.setErrormessage("请重新登录！");
        }
        return responseData;
    }
}
