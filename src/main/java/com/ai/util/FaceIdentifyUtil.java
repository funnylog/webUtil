package com.ai.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;


public class FaceIdentifyUtil {
	/**
	 * 人脸识别 1:1
	 */
	public static HttpResponse identifyFaces(String faceId1,String faceId2){
		String host = StaticsUtil.API_HOST;//"http://facerecog.market.alicloudapi.com";
		String path = "/v2/recognition/compare_face";
		String method = StaticsUtil.METHOD;
		String appcode = StaticsUtil.API_CODE;
		Map<String, String> headers = new HashMap<String, String>();
		//最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		headers.put("Authorization", "APPCODE " + appcode);
		//根据API的要求，定义相对应的Content-Type
		headers.put("Content-Type", "application/json; charset=UTF-8");
		headers.put("Content-Type", "application/json");
		Map<String, String> querys = new HashMap<String, String>();
		String bodys = "{" + 
				"\"face_id1\":\""+faceId1 + "\"," +//字符串，要比较的第一个脸的id
				"\"face_id2\":\""+faceId2 + "\"" + //字符串，要比较的第二个脸的id
				"}";
		HttpResponse response = null;
		try {
			/**
			 * 重要提示如下:
			 * HttpUtils请从
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
			 * 下载
			 *
			 * 相应的依赖请参照
			 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
			 */
			response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//			System.out.println(response.toString());
			//获取response的body
			//System.out.println(EntityUtils.toString(response.getEntity()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	@SuppressWarnings("unchecked")
	public static String createFaceSet(){
		String host = "http://faceset.market.alicloudapi.com";
	    String path = "/v2/faceset/create";
	    String method = "POST";
	    String appcode = StaticsUtil.API_CODE;
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    //根据API的要求，定义相对应的Content-Type
	    headers.put("Content-Type", "application/json; charset=UTF-8");
	    headers.put("Content-Type", "application/json");
	    Map<String, String> querys = new HashMap<String, String>();
	    String bodys = "{" +
                       "\"faceset_name\":\"ysFaceSet\"" + //新建的Faceset的名字
                       "}";
	    String faceSetId = "";
	    try {
	    	/**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*/
	    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
	    	System.out.println(response.toString());
	    	String str = EntityUtils.toString(response.getEntity());
	    	JSONObject jo = JSONObject.fromObject(str);
	    	Map<String,Object> map = jo;
	    	faceSetId = map.get("id")+"";
	    	//获取response的body
	    	//System.out.println(EntityUtils.toString(response.getEntity()));
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	return faceSetId;
	    }
	    return faceSetId;
	}
	
	public static void trainingFaceSet(String faceSetId){
		 String host = "http://faceset.market.alicloudapi.com";
		    String path = "/v2/faceset/train";
		    String method = "POST";
		    String appcode = StaticsUtil.API_CODE;
		    Map<String, String> headers = new HashMap<String, String>();
		    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		    headers.put("Authorization", "APPCODE " + appcode);
		    //根据API的要求，定义相对应的Content-Type
		    headers.put("Content-Type", "application/json; charset=UTF-8");
		    headers.put("Content-Type", "application/json");
		    Map<String, String> querys = new HashMap<String, String>();
		    String bodys = "{"+
	                       "\"faceset_id\":\"" + faceSetId + "\"" +  //要训练的FaceSet的id
	                       "\"type\":\"life\"" +       //faceSet里feature的类型，分为life和ID
	                       "\"async\":\"true\"" +      //字符串，可选。当值为true时会不等训练完成就直接返回，随后可以手动调用get_info并根据结果中的status字段判断FaceSet是否已经训练完毕
	                       "}";


		    try {
		    	/**
		    	* 重要提示如下:
		    	* HttpUtils请从
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
		    	* 下载
		    	*
		    	* 相应的依赖请参照
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
		    	*/
		    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
		    	System.out.println(response.toString());
		    	//获取response的body
		    	//System.out.println(EntityUtils.toString(response.getEntity()));
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}
	
	/**
	 * imageUrl 和  imgBaseCode 二选一 只需传一个参数就可以了
	 * @param imageUrl
	 * @param imgBaseCode
	 * @return HttpResponse
	 */
	public static HttpResponse imgresponse(String imageUrl,String imgBaseCode){
		    String host = StaticsUtil.API_HOST;
		    String path = StaticsUtil.API_PATH;
		    String method = StaticsUtil.METHOD;
		    String appcode = StaticsUtil.API_CODE;
		    Map<String, String> headers = new HashMap<String, String>();
		    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
		    headers.put("Authorization", "APPCODE " + appcode);
		    //根据API的要求，定义相对应的Content-Type
		    headers.put("Content-Type", "application/json; charset=UTF-8");
		    headers.put("Content-Type", "application/json");
		    Map<String, String> querys = new HashMap<String, String>();
		    String bodys = "{" ;
		    if(StringUtils.isNotBlank(imageUrl)){
		    	 bodys += "\"img_url\":\"" + imageUrl + "\"";
		    }else if(StringUtils.isNotBlank(imgBaseCode)){
		    	 bodys += "\"img_base64\":\""+ imgBaseCode + "\"";
		    }else {
		    	return null;
		    }
//		    bodys += "}";
	        bodys += ",\"attributes\":\"true\"}";
	        
	        HttpResponse response = null;
		    try {
		    	/**
		    	* 重要提示如下:
		    	* HttpUtils请从
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
		    	* 下载
		    	*
		    	* 相应的依赖请参照
		    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
		    	*/
		    	response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
		    	System.out.println(response.toString());
		    	//获取response的body
//		    	System.out.println(EntityUtils.toString(response.getEntity()));
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		    return response;
	}
	/**
	 * 根据人脸识别接口返回的response  获取imageId
	 * @param response
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static String getFaceId(HttpResponse response) throws ParseException, IOException{
		String result = "";
		try{
			String str = EntityUtils.toString(response.getEntity());
			if(StringUtils.isBlank(str)){
				return "";
			}
			JSONObject jo = JSONObject.fromObject(str);
			System.out.println("jo:"+jo);
			Map<String,Object> map = jo;
			if("1006".equals(map.get("code"))){
				System.out.println("错误信息：" + map.get("msg"));
				return "";
			}
			str = map.get("faces")+ "";
			System.out.println("faces参数字符串:"+str);
//			JSONArray arr = JSONArray.fromObject(str);
//			List<Map<String,Object>> list = arr;
//			for(int i=0;i<list.size();i++){
//				Map<String,Object> temp = list.get(i);
//				result = temp.get("id")+"";
//				if(StringUtils.isNotBlank(result)){
//					break;
//				}
//				
//			}
			
			if("null".equals(str)){
				System.out.println("str=null  字符串");
				return "";
			}
			if("[]".equals(str)){
				System.out.println("str=[]  字符串");
				return "";
			}
			if(StringUtils.isNotBlank(str) && !str.equals("null") && !str.equals("[]")){
//				if("null".equals(str)){
//					System.out.println("str=null  字符串");
//					return "";
//				}
//				if("[]".equals(str)){
//					System.out.println("str=[]  字符串");
//					return "";
//				}
				str = str.substring(1,str.length()-1);
			    System.out.println("sub str2:"+str);
				jo = JSONObject.fromObject(str);
				map = jo;
				result = map.get("id") + "";
			}
		}catch(Exception e){
			System.out.println("捕捉到异常");
			e.printStackTrace();
			return result;
		}
		System.out.println("获取到的faceId = " + result);
		return result;
	}

}
