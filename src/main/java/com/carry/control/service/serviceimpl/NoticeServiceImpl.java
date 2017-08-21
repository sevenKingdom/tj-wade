package com.carry.control.service.serviceimpl;


import com.carry.control.model.dao.InspectorMapper;
import com.carry.control.model.dao.NoticeMapper;
import com.carry.control.model.po.ConstructionPlan;
import com.carry.control.model.po.InspectionData;
import com.carry.control.model.po.Notice;
import com.carry.control.service.ConfigService;
import com.carry.control.service.NoticeService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by songxianying on 17/7/24.
 */
@Service
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeMapper noticeMapper;

    @Autowired
    ConfigService configService;

    @Autowired
    private InspectorMapper inspectorMapper;

    @Override
    public Long updateNotice( long id, String data) {
        return noticeMapper.updateNotice(id, data);
    }

    @Override
    public Map<Object, Object> getNoticeData () {
        // 先写成这样子
        String data =  noticeMapper.getNoticeData(0, "1.2.3");
        Map<Object, Object> mdata = null;
        if(data!=null ){
            mdata = Maps.newHashMap();
            Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
            Type type = new TypeToken<Map<Object, Object>>() {}.getType();
            Map<Object, Object> map = gson.fromJson(data, type);
            mdata = map;
        }
        return mdata;
    }

    @Override
    public String getNoticeMap () {
        /*String data =  configService.getConfig(9).getData();
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Type type = new TypeToken<Map<Object, Object>>() {}.getType();
        Map<Object, Object> map = gson.fromJson(data, type);*/
        return configService.getConfig(9).getData();
    }

    @Override
    public Map<String, Set<String>> getNoticeData (int level,String department) {
        // 先写成这样子
        String data =  noticeMapper.getNoticeData(0, "1.2.3");

        Map<String, Set<String>> mdata = getnamedata (data, department);

        return mdata;
    }
    public Map<String, Set<String>> getnamedata (String data, String department) {
        Map<String, Set<String>> mdata = null;
        if(data!=null ){
            mdata = Maps.newHashMap();
            String[] departments = department.split("\\.");
            Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
            Type type = new TypeToken<Map<Object, Object>>() {}.getType();
            Map<Object, Object> map = gson.fromJson(data, type);
            Set<String> phones = Sets.newHashSet();
            Set<String> mails = Sets.newHashSet();
            Map<Object, Object> mapll = map;
            for (String key : departments) {
                mapll = (Map<Object, Object>)mapll.get(key);
                mails.addAll((List<String>)mapll.get("mail"));
                phones.addAll((List<String>)mapll.get("phone"));
                mapll = (Map<Object, Object>)mapll.get("lowerlevel");
            }
            mdata.put("phones",phones);
            mdata.put("mails",mails);
        }
        return mdata;
    }
    @Override
    public void sendNotice(InspectionData inspectionData){
        getNoticeMap();
        Map<Object,Object> itemdata = (Map<Object,Object>)inspectionData.getLowquality().get("item");
        ConstructionPlan planadata = inspectorMapper.getByid(inspectionData.getPlanid());

        String str = planadata.getBridgeName()+" "+
                planadata.getPierNum()+" "+planadata.getStructure()+" "+planadata.getProcess()+" ";
        List<Map<String, Object>> listdata = Lists.newArrayList();
        for (Object key : itemdata.keySet()) {
            Map<String, Object> data = Maps.newHashMap();
            StringBuffer sb = new StringBuffer();
            sb.append(str+key);
            data.put("content",sb.toString());
            data.put("level",itemdata.get(key));
            listdata.add(data);
        }


    }
}
