package com.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final String TEMPLATE1 = "yyyy-MM-dd HH:mm:ss";
	public static final String TEMPLATE2 = "yyyyMMddHHmmss";
	public static final String TEMPLATE3 = "yyyy-MM-dd";
	
	/**
	 * days between two date
	 * @param smdate
	 * @param bdate
	 * @return
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate,Date bdate) throws ParseException
	{    
		SimpleDateFormat sdf=new SimpleDateFormat(TEMPLATE3);  
		smdate=sdf.parse(sdf.format(smdate));  
		bdate=sdf.parse(sdf.format(bdate));  
		Calendar cal = Calendar.getInstance();    
		cal.setTime(smdate);    
		long time1 = cal.getTimeInMillis();                 
		cal.setTime(bdate);    
		long time2 = cal.getTimeInMillis();         
		long between_days=(time2-time1)/(1000*3600*24);  

		return Integer.parseInt(String.valueOf(between_days));           
	}   

	/**
	 *days between two string date 
	 * 
	 */  
	public static int daysBetween(String smdate,String bdate) throws ParseException{  
		SimpleDateFormat sdf=new SimpleDateFormat(TEMPLATE3);  
		Calendar cal = Calendar.getInstance();    
		cal.setTime(sdf.parse(smdate));    
		long time1 = cal.getTimeInMillis();                 
		cal.setTime(sdf.parse(bdate));    
		long time2 = cal.getTimeInMillis();         
		long between_days=(time2-time1)/(1000*3600*24);  

		return Integer.parseInt(String.valueOf(between_days));     
	}  
	
	/**
	 * add days on date
	 */
	public static Date addDay(Date date, int num) {  
        Calendar startDT = Calendar.getInstance();  
        startDT.setTime(date);  
        startDT.add(Calendar.DAY_OF_MONTH, num);  
        return startDT.getTime();  
    }
	
	/**
	 * 获取 某年某月的天数
	 * @throws ParseException 
	 */
	public static int getDays(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();  
		calendar.setTime(sdf.parse(date));  
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return days;
	}
}
