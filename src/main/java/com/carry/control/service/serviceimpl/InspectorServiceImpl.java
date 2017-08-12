package com.carry.control.service.serviceimpl;

import com.carry.control.model.dao.ConfigMapper;
import com.carry.control.model.dao.InspectorMapper;
import com.carry.control.model.po.ConstructionPlan;
import com.carry.control.service.InspectorService;
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
 * Created by songxianying on 17/8/5.
 */
@Service
public class InspectorServiceImpl  implements InspectorService {
    @Autowired
    private InspectorMapper inspectorMapper;
    @Autowired
    private ConfigMapper configMapper;
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
        data.put("entry",map);
        return data;
    }
}
