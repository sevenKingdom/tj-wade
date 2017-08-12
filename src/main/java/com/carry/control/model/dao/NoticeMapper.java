package com.carry.control.model.dao;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by songxianying on 17/7/25.
 */
@FeignClient(value = "service-model")
public interface NoticeMapper {
    @GetMapping(value = "/mysql/getnoticedata")
    public String getNoticeData(@RequestParam("level") int level,
                            @RequestParam("department") String department);
}
