package com.carry.control.service.serviceimpl;


import com.carry.control.model.dao.NoticeMapper;
import com.carry.control.model.po.Notice;
import com.carry.control.service.NoticeService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by songxianying on 17/7/24.
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public Map<String, List<String>> getNoticeData (int level,String department) {
        String data =  noticeMapper.getNoticeData(level, department);
        JsonParser jsonParser = new JsonParser();
        Map<String, List<String>> mdata = null;
        if(data!=null ){
            mdata = Maps.newHashMap();
            List<String> phones = Lists.newArrayList();
            List<String> mails = Lists.newArrayList();
            JsonArray jsonlist = (JsonArray) jsonParser.parse(data);
            Iterator<JsonElement> it = jsonlist.iterator();
            while (it.hasNext()) {
                JsonObject ob = (JsonObject) it.next();
                phones.add(ob.get("phone").getAsString());
                mails.add(ob.get("mail").getAsString());
            }
            mdata.put("phones",phones);
            mdata.put("mails",mails);
        }
        return mdata;
    }
}
