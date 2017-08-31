package com.carry.control.service.serviceimpl;

import com.carry.control.model.dao.InspectorMapper;
import com.carry.control.model.dao.ReviewPlanMapper;
import com.carry.control.model.po.ConstructionPlan;
import com.carry.control.model.po.ReviewPlan;
import com.carry.control.service.ReviewPlanService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by songxianying on 17/8/20.
 */
@Service
public class ReviewPlanServiceImpl implements ReviewPlanService {
    @Autowired
    private ReviewPlanMapper reviewPlanMapper;

    @Autowired
    private InspectorMapper inspectorMapper;
    @Override
    public List<Object> findReviewPlanByToken (String token) {
        List<ReviewPlan> data = reviewPlanMapper.findReviewPlanByToken(token);
        List<Object> newdata = Lists.newArrayList();
        for (ReviewPlan po : data) {
            try {
                ConstructionPlan plan = inspectorMapper.getByid(po.getPlanid());
                plan.setId(po.getId());
                newdata.add(plan);
            } catch (Exception e ) {
                e.printStackTrace();
            }

        }
        return newdata;
    }
    @Override
    public long updateReviewPlan(long id,long reviewer ){
        return reviewPlanMapper.updateReviewPlan(id, reviewer);
    }
    @Override
    public List<Object> findReviewPlanByreviewer(long reviewer){

        List<ReviewPlan> data = reviewPlanMapper.findReviewPlanByreviewer(reviewer);
        List<Object> newdata = Lists.newArrayList();
        for (ReviewPlan po : data) {
            try {
                ConstructionPlan plan = inspectorMapper.getByid(po.getPlanid());
                plan.setId(po.getId());
                newdata.add(plan);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return newdata;
    }

    @Override
    public Map<String, Object> findReviewData(long id) {
        ReviewPlan  rplan = reviewPlanMapper.findReviewPlanById(id);
        ConstructionPlan plan = inspectorMapper.getByid(rplan.getPlanid());
        Map<String, Object> data = Maps.newHashMap();
        data.put("bridgeName",plan.getBridgeName());
        data.put("pierNum",plan.getPierNum());
        data.put("structure",plan.getStructure());
        data.put("process",plan.getProcess());
        String[] strs = plan.getPoint().split(",");
        data.put("longitude",strs[1]);
        data.put("latitude",strs[0]);
        data.put("classid",plan.getClassId());

        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Type type = new TypeToken<Map<Object, Object>>() {}.getType();
        Map<Object, Object> map = gson.fromJson(rplan.getLowquality(), type);
        Map<Object, Object> data1 = Maps.newHashMap();
        String imgss = "";
        if (null != map.get("img")) {
            Map<Object, Object> data2 = (Map<Object, Object>)map.get("img");
            for (Object key : data2.keySet()) {
                List<String> imgs = (List<String>)data2.get(key);
                data1.put(key,imgs.get(0));
                imgss = imgs.get(0);
            }
        }
        data.put("img",imgss);
        data.put("entry",map.get("item"));
        return data;
    }
}
