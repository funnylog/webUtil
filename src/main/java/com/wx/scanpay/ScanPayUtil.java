package com.wx.scanpay;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jdom.JDOMException;

import com.tencent.common.MD5;
import com.util.StringUtil;
import com.wx.util.HttpPost;
import com.wx.util.XMLUtil;

public class ScanPayUtil {

	public static String getScanPayCodeAddress() throws JDOMException, IOException{
		String orderNum = "1493951424018";
		int    total_fee  = 100; //代表一元钱,但是 以分为单位传
		String corpid     = "wx039ad537c0a70c1b";
		String mch_id     = "1237153102";
		String nonce_str  = StringUtil.getRandomString(11);
		String corpSecret = "asdfjlkjdfsuopriwqeur34579807yur";
		
		String tmpDomain =  "congda.guofeiguo.com";
		String notify_url = "notify_url";
		notify_url = tmpDomain + notify_url;
		System.out.println("notify_url:"+notify_url);
		
		SortedMap<Object, Object> params = new TreeMap<Object, Object>();  
		params.put("appid",  corpid);                     //公众号ID  必填
		params.put("mch_id", mch_id);                     //商户号           必填
		params.put("nonce_str", nonce_str);               //随机字符串,必填,长度要求在32位以内。  
		params.put("body", "yishangkeji");                         //商品简单描述  必填
		params.put("out_trade_no", orderNum);             //商户订单号, 必填
		params.put("total_fee", total_fee );              //订单总金额 必填  ,单位为分，
		
		params.put("spbill_create_ip","192.168.113.55");  //终端IP,必填      APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
		params.put("notify_url", notify_url);             //异步接收微信支付结果通知的回调地址，必填  通知url必须为外网可访问的url，不能携带参数。
		params.put("trade_type","NATIVE");                //交易类型 必填  :JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支付
		params.put("product_id","p"+orderNum);             //rade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
		String sign = createSign(params,corpSecret); //创建签名
		params.put("sign", sign);                               //签名               必填http://weixin//wxpay/bizpayurl?pr=fr5LweJ
		
		String requestXML = getRequestXml(params);
		String returnResult =  HttpPost.doPostXml("https://api.mch.weixin.qq.com/pay/unifiedorder",requestXML,"UTF-8");
		System.out.println("get scan code result :" + returnResult);
		
		Map map = XMLUtil.doXMLParse(returnResult);
		String code_url = String.valueOf(map.get("code_url"));
		System.out.println("code:" + code_url);
		return code_url;
	}
	
	/**
     * @Description：sign签名
     * @param parameters
     *            请求参数
     * @return
	 * @throws UnsupportedEncodingException 
     */  
    public static String createSign( SortedMap<Object, Object> packageParams, String API_KEY) {  
        StringBuffer sb = new StringBuffer();  
        Set es = packageParams.entrySet();  
        Iterator it = es.iterator();  
        while (it.hasNext()) {  
            Map.Entry entry = (Map.Entry) it.next();  
            String k = (String) entry.getKey();  
            String v = "";
            try {
                v = (String) entry.getValue();
            } catch (Exception e) {
                // TODO: handle exception
                v = entry.getValue() + "";
            }
             
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {  
                sb.append(k + "=" + v + "&");  
            }  
        }  
        sb.append("key=" + API_KEY);  
        String sign = MD5.MD5Encode(sb.toString()).toUpperCase();
        return sign;  
    }  
    
    /**
     * @Description：将请求参数转换为xml格式的string
     * @param parameters
     *            请求参数 fda
     * @return
     */  
	public static String getRequestXml(SortedMap<Object, Object> parameters) {  
        StringBuffer sb = new StringBuffer();  
        sb.append("<xml>");  
        Set es = parameters.entrySet();  
        Iterator it = es.iterator();  
        while (it.hasNext()) {  
            Map.Entry entry = (Map.Entry) it.next();  
            String k = (String) entry.getKey();
            String v = "";
            try {
                v = (String) entry.getValue();
            } catch (Exception e) {
                // TODO: handle exception
                v = entry.getValue() + "";
            }
             
            if ("attach".equalsIgnoreCase(k) || "body".equalsIgnoreCase(k) || "sign".equalsIgnoreCase(k)) {  
                sb.append("<" + k + ">" + "<![CDATA[" + v + "]]></" + k + ">");  
            } else {  
                sb.append("<" + k + ">" + v + "</" + k + ">");  
            }  
        }  
        sb.append("</xml>");  
        return sb.toString();  
    }  
	
	
}
