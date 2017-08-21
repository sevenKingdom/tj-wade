package com.carry.control.service;

import com.carry.control.model.po.ReviewPlan;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * Created by songxianying on 17/8/20.
 */
public interface ReviewPlanService {
    List<Object> findReviewPlanByToken (String token);
    long updateReviewPlan(long id,long reviewer );
    List<Object> findReviewPlanByreviewer(long reviewer);
    Map<String, Object> findReviewData(long id);
}
