package com.carry.control.controller;

import com.carry.commit.CommonResponse;
import com.carry.commit.DateUtil;
import com.carry.control.model.po.Configdata;
import com.carry.control.model.po.ConstructionPlan;
import com.carry.control.model.po.InspectionData;
import com.carry.control.model.po.UserCreat;
import com.carry.control.service.InspectorService;
import com.carry.control.service.NoticeService;
import com.carry.control.service.TestService;
import com.carry.control.service.UserService;
import com.carry.util.IdentityVerification;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by songxianying on 17/8/5.
 */
@RestController
@RequestMapping(value = "/inspector")
public class InspectorController {
    @Autowired
    private IdentityVerification identityVerification;

    @Autowired
    private InspectorService inspectorService;

    @Autowired
    private UserService userService;

    @Autowired
    NoticeService noticeService;

    @RequestMapping(value = "/acceptPlan" ,method = RequestMethod.POST)
    public CommonResponse<Map> getTest(@RequestParam("planid") long planid ,
                                       @RequestParam("token") String token, @RequestParam("userid") long userid) {
        CommonResponse responseData = new CommonResponse();
        String userVer = identityVerification.shortVerification(token);
        String authorVer = identityVerification.getPlanAuthor(planid);

        if (userVer != null && userVer.equals(authorVer)) {
            inspectorService.updatePlanInspector(userid,planid);
            responseData.setStatus(200);
        } else {
            responseData.setStatus(400);
            responseData.setErrormessage("请重新登录！");
        }
        return responseData;
    }

    @RequestMapping(value = "/getListByInspectorid")
    public CommonResponse<List> getListByInspectorid(@RequestParam("inspectorid") Long inspectorid,
                                                     @RequestParam("token") String token) {
        CommonResponse responseData = new CommonResponse();
        String userVer = identityVerification.shortVerification(token);
        if (userVer != null ) {
            long startTime = DateUtil.getStartTime().getTimeInMillis();
            long endTime = DateUtil.getEndTime().getTimeInMillis();
            List<ConstructionPlan> data = inspectorService.getListByInspectorid(inspectorid, startTime, endTime);
            responseData.setData(data);
            responseData.setStatus(200);
        } else {
            responseData.setStatus(400);
            responseData.setErrormessage("请重新登录！");
        }
        return  responseData;
    }
    @RequestMapping(value = "/getInspectorData")
    public CommonResponse<Map>  getInspectorData(@RequestParam("planid") Long planid,
                                         @RequestParam("token") String token) {
        CommonResponse responseData = new CommonResponse();
        String userVer = identityVerification.shortVerification(token);
        if (userVer != null ) {
            Map<String ,Object> data = inspectorService.getInspectorData(planid);
            responseData.setData(data);
            responseData.setStatus(200);
        } else {
            responseData.setStatus(400);
            responseData.setErrormessage("请重新登录！");
        }

        return  responseData;
    }

    @RequestMapping(value = "/getEquipmentData")
    public CommonResponse<Map>  getInspectorData(@RequestParam("token") String token) {
        CommonResponse responseData = new CommonResponse();
        String userVer = identityVerification.shortVerification(token);
        if (userVer != null ) {
            Map<Object ,Object> data = inspectorService.getEquipmentData();
            responseData.setData(data);
            responseData.setStatus(200);
        } else {
            responseData.setStatus(400);
            responseData.setErrormessage("请重新登录！");
        }

        return  responseData;
    }

    @RequestMapping(value = "/creatInspectionData" ,method = RequestMethod.POST)
    public CommonResponse<Integer> creatInspectionData (@RequestBody InspectionData inspectionData) {
        CommonResponse responseData = new CommonResponse();
        String userVer = identityVerification.shortVerification(inspectionData.getToken());
        if (userVer != null ) {
            inspectionData.setDepartment(userVer);
            userService.updateUserScore(inspectionData.getClassid(),0,inspectionData.getLowquality());
            responseData.setData(inspectorService.creatInspectionData(inspectionData));
            if (inspectionData.getLowquality() != null) {
                noticeService.sendNotice(inspectionData);
            }
            responseData.setStatus(200);
        } else {
            responseData.setStatus(400);
            responseData.setErrormessage("请重新登录！");
        }

        return  responseData;
    }

}
