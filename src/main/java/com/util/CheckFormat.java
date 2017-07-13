package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class CheckFormat {
	
	/**
	 * check email format
	 * @param emailStr
	 * @return
	 */
	public static Boolean isEmail(String emailStr) {
		if (StringUtils.isNotBlank(emailStr)) {
			String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
			Pattern p = Pattern.compile(str);
			Matcher m = p.matcher(emailStr);
			return m.matches();
		}
		return false;
	}

	/**
	 * check mobile phone number
	 * @param mobiles
	 * @return
	 */
	public static Boolean isMobileNo(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(17[0-9])|(15[0-9])|(18[0-9]|170)|(147))\\d{8}$"); // 增加17*号段
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	/**
	 * whether contains number
	 * @param str
	 * @return
	 */
	public static Boolean containNum(String str) {
		Boolean result = false;
		if (StringUtils.isNotBlank(str)) {
			char[] charArr = str.toCharArray();
			char charInstance;
			for (int charLength = 0, max = charArr.length; charLength < max; charLength++) {
				charInstance = charArr[charLength];
				if (Character.isDigit(charInstance)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * whether contains lower case
	 * @param str
	 * @return
	 */
	public static Boolean containLowCase(String str) {
		Boolean result = false;
		if (StringUtils.isNotBlank(str)) {
			char[] charArr = str.toCharArray();
			char charInstance;
			for (int charLength = 0, max = charArr.length; charLength < max; charLength++) {
				charInstance = charArr[charLength];
				if (charInstance >= 'a' && charInstance <= 'z') {
					result = true;
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * whether contails upper case
	 * @param str
	 * @return
	 */
	public static Boolean containUpperCase(String str) {
		Boolean result = false;
		if (StringUtils.isNotBlank(str)) {
			char[] charArr = str.toCharArray();
			char charInstance;
			for (int charLength = 0, max = charArr.length; charLength < max; charLength++) {
				charInstance = charArr[charLength];
				if (charInstance >= 'A' && charInstance <= 'Z') {
					result = true;
					break;
				}
			}
		}
		return result;
	}

}
