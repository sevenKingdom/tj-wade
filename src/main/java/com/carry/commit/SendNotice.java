package com.carry.commit;

import com.carry.control.model.po.ConstructionPlan;
import com.carry.control.model.po.InspectionData;
import com.carry.control.model.po.MailInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by songxianying on 17/8/21.
 */
public class SendNotice  implements Runnable{

    //短信用户名
    private static String Uid = "tj_sms_service";

    //短信接口安全秘钥
    private static String Key = "ee5564a6ade02f0bb227";

    private ConstructionPlan planadata;

    private InspectionData inspectionData;

    private String noticeMap;

    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");

    public SendNotice (ConstructionPlan planadata , InspectionData inspectionData,String noticeMap) {
        this.planadata = planadata;
        this.inspectionData = inspectionData;
        this.noticeMap = noticeMap;
    }

    @Override
    public void run() {
        List<Map<String, Object>> listdata = getSendPhoneContent();
        Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
        Type type = new TypeToken<Map<Object, Object>>() {}.getType();
        Map<Object, Object> map = gson.fromJson(noticeMap, type);
        for ( Map<String, Object> po : listdata) {
            Map<String, Set<String>> data = getnamedata(map,inspectionData.getDepartment(),(Integer)po.get("level"));
            sendMail("",po.get("content").toString(),data.get("mails"));
            sendPhone(po.get("content").toString(),data.get("phones"));
        }

    }

    public String  sendMail(String title, String content, Set<String> mails) {
        StringBuffer mailstr = new StringBuffer();
        for (String str : mails) {
            mailstr.append(str + ",");
        }
        String mail = mailstr.substring(0,mailstr.length()-1); //发送对象的邮箱
        title = "铁建风险通知";

        content = "<div>"+content+"</div><br/><hr/>" +
                "<div><span style=\"float:right;\">"+df.format(new Date())+"</span></div>";
        MailInfo info = new MailInfo();
        info.setToAddress(mail);
        info.setSubject(title);
        info.setContent(content);
        try {
            MailSendUtil.sendHtmlMail(info);
        } catch (Exception e) {
            return e+title+"'的邮件发送失败！";
        }
        return "ok";
    }
    public String sendPhone(String smsText , Set<String> phones){
        smsText = smsText + df.format(new Date());
        StringBuffer phonestr = new StringBuffer();
        for (String str : phones) {
            phonestr.append(str + ",");
        }
        String smsMob = phonestr.substring(0,phonestr.length()-1); //发送对象的邮箱
        //手机号码，多个号码如13800000000,13800000001,13800000002
        //String smsMob = "18953820540,18202719658,13628669592";

        //短信内容
        //smsText = "test";
        HttpClientUtil client = HttpClientUtil.getInstance();

        //UTF发送
        int result = client.sendMsgUtf8(Uid, Key, smsText, smsMob);

        if(result>0){
            return "ok"+result;
        }else{
            return client.getErrorMsg(result);
        }
    }

    public Map<String, Set<String>> getnamedata (Map<Object, Object> map, String department ,int level) {
        Map<String, Set<String>> mdata = null;
        if(map!=null && map.size() !=0 ){
            mdata = Maps.newHashMap();
            String[] departments = department.split("\\.");

            Set<String> phones = Sets.newHashSet();
            Set<String> mails = Sets.newHashSet();
            Map<Object, Object> mapll = map;
            for (String key : departments) {
                mapll = (Map<Object, Object>)mapll.get(key);
                if (level == 1) {
                    mails.addAll((List<String>)mapll.get("mail"));
                    phones.addAll((List<String>)mapll.get("phone"));
                }
                level--;
                mapll = (Map<Object, Object>)mapll.get("lowerlevel");
            }
            mdata.put("phones",phones);
            mdata.put("mails",mails);
        }
        return mdata;
    }

    public List<Map<String, Object>> getSendPhoneContent (){
        Map<Object,Object> itemdata = (Map<Object,Object>)inspectionData.getLowquality().get("item");
        String str = planadata.getBridgeName()+" "+
                planadata.getPierNum()+" "+planadata.getStructure()+" "+planadata.getProcess()+" ";
        List<Map<String, Object>> listdata = Lists.newArrayList();
        for (Object key : itemdata.keySet()) {
            Map<String, Object> data = Maps.newHashMap();
            StringBuffer sb = new StringBuffer();
            sb.append(str+key+" 发生"+itemdata.get(key)+"级风险");
            data.put("content",sb.toString());
            data.put("level",itemdata.get(key));
            listdata.add(data);
        }
        return listdata;
    }
}
