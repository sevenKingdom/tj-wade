package com.carry.control.service;

import com.carry.control.model.po.ConstructionPlan;
import com.carry.control.model.po.InspectionData;

import java.util.List;
import java.util.Map;

/**
 * Created by songxianying on 17/8/5.
 */
public interface InspectorService {
    long updatePlanInspector ( Long inspectorid,  Long id);

    List<ConstructionPlan> getListByInspectorid(Long inspectorid, Long startTime, Long endTime);

    Map<String, Object> getInspectorData(Long planid);

    Map<Object, Object> getEquipmentData();

    int creatInspectionData (InspectionData inspectionData);
}
