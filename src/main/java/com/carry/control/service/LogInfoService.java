package com.carry.control.service;



import com.carry.control.model.po.Loginfo;

import java.util.List;

/**
 * Created by songxianying on 17/8/29.
 */
public interface LogInfoService {
    List<Loginfo> getListOneDay(String department);
}
