package com.carry.control.service.serviceimpl;

import com.carry.control.model.dao.ConfigMapper;
import com.carry.control.model.dao.UserMapper;
import com.carry.control.model.po.UserCreat;
import com.carry.control.model.po.UserData;
import com.carry.control.model.po.UserInfo;
import com.carry.control.service.UserService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Created by songxianying on 17/7/23.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public Map<String,Object> login(String name , String password){
        Map<String,Object> data = null;
        UserCreat userdata =  userMapper.login(name,password);
        if ( userdata != null ) {
            data = Maps.newHashMap();
            data.put("id",userdata.getUserData().getId());
            data.put("token",userdata.getUserData().getToken());
            data.put("role",userdata.getUserData().getRole());
            data.put("name",userdata.getUserInfo().getName());
            data.put("phone",userdata.getUserInfo().getPhone());
            data.put("mail",userdata.getUserInfo().getMail());
        }
        return data;
    }
    @Override
    public long logoff( long id) {
        return userMapper.logoff(id);
    }
    @Override
    public Map<String,Object> creatUser (UserCreat userCreat) {
       return  userMapper.creatUser(userCreat);
    }

    @Override
    public Long updateUserScore(Long id, Integer score,Map<Object, Object> data) {
        Map<Object, Object> mapdata = (Map<Object, Object>)data.get("item");
        score  = 0;
        for (Object key : mapdata.keySet()) {
            Integer integer = (Integer) mapdata.get(key);
            switch(integer) {
                case 1:
                    score += -40;
                    break;
                case 2:
                    score += -30;
                    break;
                case 3:
                    score += -20;
                    break;
                case 4:
                    score += -10;
                    break;
                default:
                    break;

            }
        }
        return userMapper.updateUserScore(id,score);
    }
    @Override
    public List<Map<String,Object>> getAllUser(){
        List<UserCreat> userCreats = userMapper.getAllUser();
        List<Map<String,Object>> returndata = Lists.newArrayList();
        for ( UserCreat userCreat : userCreats) {
            int role = userCreat.getUserData().getRole();
            if (role == 0) {
                continue;
            }
            Map<String,Object> map = Maps.newHashMap();
            map.put("name",userCreat.getUserInfo().getName());
            map.put("phone",userCreat.getUserInfo().getPhone());
            map.put("mail",userCreat.getUserInfo().getMail());
            int sex = userCreat.getUserInfo().getSex();
            String sexs = "男";
            if (sex == 0 ) {
                sexs = "女";
            }
            map.put("sex",sexs);
            map.put("id",userCreat.getUserData().getId());
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            map.put("birthdate",df.format(userCreat.getUserInfo().getBirthdate()));
            List<String> depatment = getDepartment(userCreat.getUserData().getDepartment(),
                    userCreat.getUserData().getDutyid(),userCreat.getUserData().getPost());
            map.put("department",depatment.get(0));
            map.put("duty",depatment.get(2));
            map.put("post",depatment.get(1));
            // map.put("password",userCreat.getUserData().getPassword());
            returndata.add(map);
        }
        return returndata;
    }

    public List<String> getDepartment (String depa , Long dutyid , Integer post) {
        String data = configMapper.getTest().getData();
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Type type = new TypeToken<Map<Object, Object>>() {}.getType();
        Map<Object, Object> map = gson.fromJson(data, type);
        String[] departments = depa.split("\\.");
        StringBuffer sb = new StringBuffer();
        String duty = "";
        String poststr = "";
        Map<Object, Object> mapll = map;
        int i = departments.length;
        for (String key : departments) {
            mapll = (Map<Object, Object>)mapll.get(key);
            sb.append(mapll.get("name")+" ");
            i--;
            if (i != 0)
                mapll = (Map<Object, Object>)mapll.get("lowerlevel");

        }
        if (post == 0) {
            Map<Object, Object> map1 = (Map<Object, Object>)mapll.get("duty");
            duty = map1.get(dutyid+"").toString();
            poststr = "";
        } else {
            Map<Object, Object> maplll = ((Map<Object, Object>)mapll.get("department"));
            Map<Object, Object> mapllll = (Map<Object, Object>)maplll.get(post+"");
            poststr = mapllll.get("name").toString();
            Map<Object, Object> map1 = (Map<Object, Object>)mapllll.get("duty");
            duty = map1.get(dutyid+"").toString();
        }
        List<String> depatment = Lists.newArrayList();
        depatment.add(sb.toString());
        depatment.add(poststr);
        depatment.add(duty);
        return depatment;
    }

    @Override
    public int deleteUser (Long id) {
        return userMapper.deleteUser(id);
    }


    @Override
    public int updateUser (Long id, String name, String phone,String password, String mail) {
        return userMapper.updateUser(id,name,phone,password,mail);
    }


    @Override
    public int updateUserPassword (Long id, String password) {
        return userMapper.updateUserPassword(id, password);
    }
    @Override
    public List<Map<String,Object>> findBydepartment (String department) {
        String[] departments = department.split("\\.");
        String data = configMapper.getTest().getData();
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Type type = new TypeToken<Map<Object, Object>>() {}.getType();
        Map<Object, Object> map = gson.fromJson(data, type);
        Map<Object, Object> mapll = map;
        for (String key : departments) {
            mapll = (Map<Object, Object>)mapll.get(key);
            mapll = (Map<Object, Object>)mapll.get("lowerlevel");

        }
        List<Map<String,Object>> returndata = Lists.newArrayList();
        List<UserCreat>  datas = userMapper.findBydepartment(department);
        for (UserCreat po : datas) {
            Map<String,Object> poo = Maps.newHashMap();
            String[] dps = po.getUserData().getDepartment().split("\\.");
            String de = ((Map<Object, Object>)mapll.get(dps[dps.length-1])).get("name").toString();
            de += " " + po.getUserInfo().getName();
            poo.put("id",po.getUserData().getId());
            poo.put("name",de);
            returndata.add(poo);
        }
        return returndata;
    }
}
