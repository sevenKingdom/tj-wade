package com.carry.control.controller;

import com.carry.commit.CommonResponse;
import com.carry.control.model.po.ReviewPlan;
import com.carry.control.service.ReviewPlanService;
import com.carry.util.IdentityVerification;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by songxianying on 17/8/20.
 */
@RestController
@RequestMapping(value = "/review")
public class ReviewPlanController {
    
    @Autowired
    private ReviewPlanService reviewPlanService;

    @Autowired
    private IdentityVerification identityVerification;
    
    @RequestMapping(value = "/findReviewPlanByToken" ,method = RequestMethod.POST)
    public CommonResponse<List<Object>> findReviewPlanByToken (@RequestParam("token") String token) {
        CommonResponse responseData = new CommonResponse();
        String token1 = identityVerification.shortVerification(token);
        if (token1 != null ) {
           responseData.setData(reviewPlanService.findReviewPlanByToken(token1));
           responseData.setStatus(200);
        } else {
            responseData.setStatus(400);
            responseData.setErrormessage("请重新登录！");
        }


        return responseData;
    }
    @RequestMapping(value = "/updateReviewPlan" ,method = RequestMethod.POST)
    public CommonResponse<Object> updateReviewPlan(@RequestParam("id") long id,@RequestParam("reviewer") long reviewer,
                                 @RequestParam("token") String token){
        CommonResponse responseData = new CommonResponse();
        String token1 = identityVerification.shortVerification(token);
        if (token1 != null ) {
            responseData.setData(reviewPlanService.updateReviewPlan(id, reviewer));
            responseData.setStatus(200);
        } else {
            responseData.setStatus(400);
            responseData.setErrormessage("请重新登录！");
        }
       return responseData;
    }
    @RequestMapping(value = "/findReviewPlanByreviewer" ,method = RequestMethod.POST)
    public CommonResponse<List<Object>> findReviewPlanByreviewer (@RequestParam("id") Long id) {
        CommonResponse responseData = new CommonResponse();
        responseData.setData(reviewPlanService.findReviewPlanByreviewer(id));
        responseData.setStatus(200);
        return responseData;
    }

    @RequestMapping(value = "/getReviewData")
    public CommonResponse<Map>  getReviewData(@RequestParam("planid") Long planid,
                                                 @RequestParam("token") String token) {
        CommonResponse responseData = new CommonResponse();
        String userVer = identityVerification.shortVerification(token);
        if (userVer != null ) {
            Map<String ,Object> data = reviewPlanService.findReviewData(planid);
            responseData.setData(data);
            responseData.setStatus(200);
        } else {
            responseData.setStatus(400);
            responseData.setErrormessage("请重新登录！");
        }

        return  responseData;
    }



}