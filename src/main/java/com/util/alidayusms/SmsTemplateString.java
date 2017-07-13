package com.util.alidayusms;
/*
 * 此文件修改时 请仔细核对 字符串的值
 */
public interface SmsTemplateString {
	
	/**
	 * app_key
	 */
	public static final String APP_KEY = "23737818";
	
	/**
	 * app_secret
	 */
	
	public static final String APP_SECRET = "3b284dad32d4da56661d52f2acaf17eb";
	/**
	 * 签名
	 */
	public static final String SIGNNAME = "变更验证";
	
	/**
	 * 用户注册
	 * 修改密码
	 * 用户银行卡 添加 或  修改
	 * 模板:
	 *  【五洲】亲爱的用户,您的验证码是:${code},请尽快输入验证!
	 */
	public static final String SMS_61070005 = "SMS_61070005";
	
	/**
	 * 支持先发货后付款，每个订单的付款截止日期有个默认日期，同时也可以手动修改，在到期前xx天可以消息提醒用户
	 * 模板:
	 * 	【五洲】亲爱的用户,您的订单号为 ${orderNum} 的订单,截止付款日期还有 ${day} 天,请尽快支付!
	 */
	public static final String SMS_61065005 = "SMS_61065005";
	
	/**
	 * 当有订单已付款未发货时，需要短信提醒客服人员 
	 * 模板:
	 *  【五洲】亲爱的用户,有新订单生成,订单号为 ${orderNum} ,请尽快发货!
	 */
	public static final String SMS_61000003 = "SMS_61000003";
	
	
	
}
