package com.carry.control.service.serviceimpl;

import com.carry.control.model.dao.*;
import com.carry.control.model.po.*;
import com.carry.control.service.InspectorService;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by songxianying on 17/8/5.
 */
@Service
public class InspectorServiceImpl  implements InspectorService {
    private static final String SHOW_URL = "http://192.168.10.163:8763/file/";
    @Autowired
    private InspectorMapper inspectorMapper;
    @Autowired
    private ConfigMapper configMapper;
    @Autowired
    private ClassReviewRecordMapper classReviewRecordMapper;
    @Autowired
    private InspectionRecordMapper inspectionRecordMapper;
    @Autowired
    private ReviewPlanMapper reviewPlanMapper;

    @Override
    public long updatePlanInspector ( Long inspectorid,  Long id){
        return inspectorMapper.updatePlanInspector(inspectorid, id);
    }
    @Override
    public List<ConstructionPlan> getListByInspectorid(Long inspectorid, Long startTime, Long endTime) {
        return inspectorMapper.getListByInspectorid(inspectorid, startTime, endTime);
    }

    @Override
    public Map<String, Object> getInspectorData(Long planid) {
        ConstructionPlan planadata = inspectorMapper.getByid(planid);
        Map<String, Object> data = Maps.newHashMap();
        data.put("bridgeName",planadata.getBridgeName());
        data.put("whether",planadata.getWhether());
        data.put("pierNum",planadata.getPierNum());
        data.put("structure",planadata.getStructure());
        data.put("process",planadata.getProcess());
        String[] strs = planadata.getPoint().split(",");
        data.put("longitude",strs[1]);
        data.put("latitude",strs[0]);

        JsonObject jsondata = configMapper.getConfig(2).getConfigdata();
        JsonObject prosessdata = jsondata.get("桥梁").getAsJsonObject().
                get(planadata.getStructure()).getAsJsonObject().
                get(planadata.getProcess()).getAsJsonObject();
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Type type = new TypeToken<Map<Object, Object>>() {}.getType();
        Map<Object, Object> map = gson.fromJson(prosessdata, type);
        JsonObject jsonimgdata = configMapper.getConfig(5).getConfigdata();
        String prosessimgdata = jsonimgdata.get("桥梁").getAsJsonObject().
                get(planadata.getStructure()).getAsJsonObject().
                get(planadata.getProcess()).getAsString();
        data.put("img", SHOW_URL+prosessimgdata);
        data.put("entry",map);
        return data;
    }

    @Override
    public Map<Object, Object> getEquipmentData() {
        JsonObject jsondata = configMapper.getConfig(3).getConfigdata();
        JsonObject jsonimgdata = configMapper.getConfig(5).getConfigdata();
        JsonObject prosessdata = jsonimgdata.get("设备").getAsJsonObject();
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Type type = new TypeToken<Map<Object, Object>>() {}.getType();
        Map<Object, Object> map = gson.fromJson(jsondata, type);
        Map<Object, Object> imgmap = gson.fromJson(prosessdata, type);
        map.put("img",imgmap);
        map.put("imgpath",SHOW_URL);
        return map;
    }
    @Override
    public int creatInspectionData (InspectionData inspectionData) {
        ClassReviewRecord classReviewRecord = new ClassReviewRecord();
        InspectionRecord inspectionRecord = new InspectionRecord();
        ReviewPlan reviewPlan = new ReviewPlan();
        int t = 0 ;
        changeData(classReviewRecord,inspectionRecord,reviewPlan,inspectionData);
        t += classReviewRecordMapper.addClassReviewRecord(classReviewRecord);
        t += inspectionRecordMapper.addInspectionRecord(inspectionRecord);
        t += reviewPlanMapper.addReviewPlan(reviewPlan);
        return t;
    }
    public void changeData (ClassReviewRecord classReviewRecord,InspectionRecord inspectionRecord,
                            ReviewPlan reviewPlan, InspectionData inspectionData) {
        long times = new Date().getTime();
        classReviewRecord.setClassid(inspectionData.getClassid());
        classReviewRecord.setPlanid(inspectionData.getPlanid());
        classReviewRecord.setLowquality(inspectionData.getLowquality());
        classReviewRecord.setStyle(inspectionData.getStyle());
        classReviewRecord.setCreatedat(times);
        inspectionRecord.setInspectorid(inspectionData.getInspectorid());
        inspectionRecord.setLowquality(inspectionData.getLowquality());
        inspectionRecord.setQuality(inspectionData.getQuality());
        inspectionRecord.setStyle(inspectionData.getStyle());
        inspectionRecord.setPlanid(inspectionData.getPlanid());
        inspectionRecord.setCreatedat(times);
        reviewPlan.setFirstman(inspectionData.getInspectorid());
        reviewPlan.setLowquality(inspectionData.getLowquality());
        reviewPlan.setPower(inspectionData.getDepartment());
        reviewPlan.setPlanid(inspectionData.getPlanid());
        reviewPlan.setCreatedat(times);
    }
}
