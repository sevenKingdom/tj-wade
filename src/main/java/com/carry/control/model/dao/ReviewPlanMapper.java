package com.carry.control.model.dao;

import com.carry.control.model.po.ReviewPlan;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by songxianying on 17/8/13.
 */
@FeignClient(value = "service-model")
public interface ReviewPlanMapper {
    @RequestMapping(value = "/mysql/addReviewPlan" ,method = RequestMethod.POST)
    public Long addReviewPlan(@RequestBody ReviewPlan reviewPlan);

    @RequestMapping(value = "/mysql/findReviewPlanByToken" ,method = RequestMethod.POST)
    public List<ReviewPlan> findReviewPlanByToken (@RequestParam("token") String token);

    @RequestMapping(value = "/mysql/updateReviewPlan" ,method = RequestMethod.POST)
    public long updateReviewPlan(@RequestParam("id") long id,@RequestParam("reviewer") long reviewer );

    @RequestMapping(value = "/mysql/findReviewPlanByreviewer" ,method = RequestMethod.POST)
    public List<ReviewPlan> findReviewPlanByreviewer(@RequestParam("reviewer") long reviewer);

    @RequestMapping(value = "/mysql/findReviewPlanById" ,method = RequestMethod.POST)
    public ReviewPlan findReviewPlanById(@RequestParam("id") long id);

}
