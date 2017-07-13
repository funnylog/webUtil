package com.util;

public class CalculationUtil {

	/**
	 * 四舍五入保留小数点后两位
	 * Rounded to keep two decimal places
	 * @param d
	 * @return
	 */
	public static double getSecondBits(double d) {
		return (double) Math.round(d * 100) / 100;
	}
}
