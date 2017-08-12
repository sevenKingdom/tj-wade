package com.carry.control.service;

import java.util.List;
import java.util.Map;

/**
 * Created by songxianying on 17/7/24.
 */
public interface NoticeService {
    public Map<String, List<String>> getNoticeData(int level, String department);
}
