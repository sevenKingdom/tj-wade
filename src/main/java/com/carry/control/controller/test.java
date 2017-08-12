package com.carry.control.controller;

import com.carry.commit.HttpClientUtil;


/**  
 * @Title: http://www.smschinese.cn/api.shtml
 * @date 2011-3-22
 * @version V1.2  
 */
public class test {
	
	//用户名
	private static String Uid = "tj_sms_service";
	
	//接口安全秘钥
	private static String Key = "ee5564a6ade02f0bb227";
	
	//手机号码，多个号码如13800000000,13800000001,13800000002
	private static String smsMob = "18953820540,18202719658,13628669592";
	
	//短信内容
	private static String smsText = "先生需要更多服务么～";
	
	
	public static void main(String[] args) {
		
		HttpClientUtil client = HttpClientUtil.getInstance();
		
		//UTF发送
		int result = client.sendMsgUtf8(Uid, Key, smsText, smsMob);
		if(result>0){
			System.out.println("UTF8成功发送条数=="+result);
		}else{
			System.out.println(client.getErrorMsg(result));
		}
	}
}
