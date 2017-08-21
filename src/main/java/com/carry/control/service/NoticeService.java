package com.carry.control.service;

import com.carry.control.model.po.InspectionData;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by songxianying on 17/7/24.
 */
public interface NoticeService {
    Map<String, Set<String>> getNoticeData(int level, String department);
    Map<Object, Object> getNoticeData ();
    Long updateNotice( long id, String data) ;
    String getNoticeMap ();
    void sendNotice(InspectionData inspectionData);
}
