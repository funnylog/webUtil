package com.util;

import java.util.List;

public class ListToString {

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
}
