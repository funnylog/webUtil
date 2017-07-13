package com.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Random;

public class StringUtil {

	/**
	 * get random number and letter by customized length
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) { //length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";   
		Random random = new Random();
		StringBuffer sb = new StringBuffer();   
		for (int i = 0; i < length; i++) {   
			int number = random.nextInt(base.length());   
			sb.append(base.charAt(number));   
		}
		return sb.toString();   
	}
	
	/**
	 * 不以0开头的 纯数字
	 * @param length
	 * @return
	 */
	public static String numberStrNotStartZero(int length){
		String checkWords="";
		Random random = new Random();
		for(int i=0;i<length;i++){
			if(i==0){
				int x = random.nextInt(10);
				while(x==0){
					x = random.nextInt(10);
				}
				checkWords += x;
			}
			checkWords += random.nextInt(10);
		}
		return checkWords;
	}

	/**
	 * 可以0开头的纯数字
	 * get number string by customized length
	 */
	public static String normalCheckwords(int length){
		String checkWords="";
		Random random = new Random();
		for(int i=0;i<length;i++){
			checkWords += random.nextInt(10);
		}
		return checkWords;
	}

	/**
	 * encoding string then return ;
	 * @param str
	 * @return
	 */
	public static String getMD5Str(String str) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			// messageDigest.update(str.getBytes("UTF-8"));
			messageDigest.update(str.getBytes("GBK"));
		}
		catch (NoSuchAlgorithmException e) {
			System.out.println("NoSuchAlgorithmException caught!");
			System.exit(-1);
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		byte[] byteArray = messageDigest.digest();

		StringBuffer md5StrBuff = new StringBuffer();

		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			else
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
		}
		return md5StrBuff.toString();
	}

	/**
	 *  将list转换为string 用于sql查询，返回元素和参数元素都  没有单引号
	 */
	public static String listToStr(List list) {
		String returnStr = "";
		if (!list.isEmpty()) {
			returnStr = list.toString();
			returnStr = returnStr.replace("[", "(");
			returnStr = returnStr.replace("]", ")");
		}
		return returnStr;
	}
	/**
	 * 如果没有apache  StringUtils 类 ，判断字符串是否为空
	 * true 为空
	 */
	public static Boolean whetherBlank(String str){
		if(str == null || "".equals(str.trim())){
			return true;
		}
		return false;
	}

	/**
	 * 打印list
	 * @param list
	 */
	public static void printList(List list){
		for(int i=0;i<list.size();i++){
			Object[] obj = (Object[]) list.get(i);
			for(int j=0;j<obj.length;j++){
				System.out.print(obj[j]+" ");
			}
			System.out.println();
		}
	}


}
