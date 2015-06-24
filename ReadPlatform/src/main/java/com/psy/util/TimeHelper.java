/**
 * TimeHelper.java
 * com.xiao.util
 * author      date      	
 * ──────────────────────────────────
 * xiao    2015年4月17日 		
 * Copyright (c)2015, All Rights Reserved.
 * Java源代码,未经许可禁止任何人、任何组织通过任何
 * 渠道使用、修改源代码.
*/
package com.psy.util;

import java.text.*;
import java.util.*;

/**
 * ClassName:TimeHelper
 *
 * TODO(日期类)
 *
 * @project psy
 *
 * @author xiao
 *
 * @date   2015年4月17日 下午2:10:11	
 *
 * @class com.xiao.util.TimeHelper
 *
 */ 
public class TimeHelper {
	private static String CurrentTime;

	private static String CurrentDate;

	public static String getCurrentYear() {
		java.util.Date NowDate = new java.util.Date();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return formatter.format(NowDate);
	}

	public static String getCurrentMonth() {
		java.util.Date NowDate = new java.util.Date();

		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		return formatter.format(NowDate);
	}

	public static String getCurrentDay() {
		java.util.Date NowDate = new java.util.Date();

		SimpleDateFormat formatter = new SimpleDateFormat("dd");
		return formatter.format(NowDate);
	}
	public static String getCurrentTime() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CurrentTime = formatter.format(NowDate);
		return CurrentTime;
	}

	public static String getCurrentCompactTime() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		CurrentTime = formatter.format(NowDate);
		return CurrentTime;
	}
	public static String getYesterdayCompactTime() {
		Calendar cal = Calendar.getInstance(); 
		cal.add(cal.DATE, -1); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		CurrentTime = formatter.format(cal.getTime());
		return CurrentTime;
	}
	public static String getCurrentYMDDate() {
		Calendar cal = Calendar.getInstance(); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		CurrentTime = formatter.format(cal.getTime());
		return CurrentTime;
	}
	public static String getYesterdayCompactTimeForFileName() {
		Calendar cal = Calendar.getInstance(); 
		cal.add(cal.DATE, -1); 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		CurrentTime = formatter.format(cal.getTime());
		return CurrentTime;
	}
	public static String getCurrentDate() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		CurrentDate = formatter.format(NowDate);
		return CurrentDate;
	}
	public static String getCurrentDate1() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		CurrentDate = formatter.format(NowDate);
		return CurrentDate;
	}
	public static String getCurrentFirstDate() {
		Date NowDate = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-01");
		CurrentDate = formatter.format(NowDate);
		return CurrentDate;
	}
	public static String getCurrentLastDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar=null;
		try {
			java.util.Date date =formatter.parse(getCurrentFirstDate());
			calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH,1);
			calendar.add(Calendar.DAY_OF_YEAR, -1);
	        return formatDate(calendar.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}


    public static String formatDate(java.util.Date date)
    {
        return formatDateByFormat(date,"yyyy-MM-dd");
    }
    public static String formatDateByFormat(java.util.Date date,String format)
    {
        String result = "";
        if(date != null)
        {
            try
            {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            }
            catch(Exception ex)
            {
                
                ex.printStackTrace();
            }
        }
        return result;
    }
	public static String addDay(String currentdate, int add_day) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int year, month, day;

		try {
			year = Integer.parseInt(currentdate.substring(0, 4));
			month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdate.substring(8, 10));

			gc = new GregorianCalendar(year, month, day);
			gc.add(GregorianCalendar.DATE, add_day);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String addMonth(String currentdate, int add_month) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int year, month, day;

		try {
			year = Integer.parseInt(currentdate.substring(0, 4));
			month = Integer.parseInt(currentdate.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdate.substring(8, 10));

			gc = new GregorianCalendar(year, month, day);
			gc.add(GregorianCalendar.MONTH, add_month);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static int monthDiff(String beforeTime,String endTime){
		if(beforeTime==null||endTime==null){
			return 0;
		}
		int beforeYear,endYear,beforeMonth,endMonth;
		try {
			beforeYear = Integer.parseInt(beforeTime.substring(0, 4));
			endYear = Integer.parseInt(endTime.substring(0, 4));
			beforeMonth = Integer.parseInt(beforeTime.substring(5, 7)) - 1;
			endMonth = Integer.parseInt(endTime.substring(5, 7)) - 1;
			return (endYear-beforeYear)*12+(endMonth-beforeMonth);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static String addMinute(String currentdatetime, int add_minute) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int year, month, day, hour, minute, second;

		try {
			year = Integer.parseInt(currentdatetime.substring(0, 4));
			month = Integer.parseInt(currentdatetime.substring(5, 7))-1;
			day = Integer.parseInt(currentdatetime.substring(8, 10));

			hour = Integer.parseInt(currentdatetime.substring(11, 13));
			minute = Integer.parseInt(currentdatetime.substring(14, 16));
			second = Integer.parseInt(currentdatetime.substring(17, 19));

			gc = new GregorianCalendar(year, month, day, hour, minute, second);
			gc.add(GregorianCalendar.MINUTE, add_minute);
			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static String reductionMinute(String currentdatetime, int minute) {
		GregorianCalendar gc = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			 Date date = format.parse(currentdatetime);
			 long Time=(date.getTime()/1000)-60*minute;
			 date.setTime(Time*1000);
			return format.format(date.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String addSecond(String currentdatetime, int add_second) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int year, month, day, hour, minute, second;

		try {
			year = Integer.parseInt(currentdatetime.substring(0, 4));
			month = Integer.parseInt(currentdatetime.substring(5, 7))-1;
			day = Integer.parseInt(currentdatetime.substring(8, 10));

			hour = Integer.parseInt(currentdatetime.substring(11, 13));
			minute = Integer.parseInt(currentdatetime.substring(14, 16));
			second = Integer.parseInt(currentdatetime.substring(17, 19));

			gc = new GregorianCalendar(year, month, day, hour, minute, second);
			gc.add(GregorianCalendar.SECOND, add_second);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String addMinute1(String currentdatetime, int add_minute) {
		GregorianCalendar gc = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		int year, month, day, hour, minute, second;

		try {
			year = Integer.parseInt(currentdatetime.substring(0, 4));
			month = Integer.parseInt(currentdatetime.substring(5, 7)) - 1;
			day = Integer.parseInt(currentdatetime.substring(8, 10));

			hour = Integer.parseInt(currentdatetime.substring(8, 10));
			minute = Integer.parseInt(currentdatetime.substring(8, 10));
			second = Integer.parseInt(currentdatetime.substring(8, 10));

			gc = new GregorianCalendar(year, month, day, hour, minute, second);
			gc.add(GregorianCalendar.MINUTE, add_minute);

			return formatter.format(gc.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Date parseDate(String sDate) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			Date date = bartDateFormat.parse(sDate);
			return date;
		} catch (Exception ex) {
		}
		return null;
	}
	public static Date parseDate2(String sDate) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy/MM/dd");

		try {
			Date date = bartDateFormat.parse(sDate);
			return date;
		} catch (Exception ex) {
		}
		return null;
	}
	public static Date parseDateTime(String sDateTime) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");

		try {
			Date date = bartDateFormat.parse(sDateTime);
			return date;
		} catch (Exception ex) {
		}
		return null;
	}

	public static int getTotalDaysOfMonth(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = new GregorianCalendar();

		Date date = null;
		try {
			date = sdf.parse(strDate);
			calendar.setTime(date);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH); // 天数
		return day;
	}
	
	
	public static long getDateSubDay(String startDate ,String endDate ) {
		Calendar calendar = Calendar.getInstance(); 
		SimpleDateFormat   sdf   =   new   SimpleDateFormat("yyyy-MM-dd"); 
		long theday = 0;
		try  {
			calendar.setTime(sdf.parse(startDate)); 
			long   timethis   =   calendar.getTimeInMillis(); 
			calendar.setTime(sdf.parse(endDate)); 
			long   timeend   =   calendar.getTimeInMillis(); 
			theday   =   (timeend  -  timethis  )   /   (1000   *   60   *   60   *   24); 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return theday;
	}
	public static String getPlusTime(String sDateTime,String eDateTime) throws ParseException {
		 SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   java.util.Date ssDateTime= myFormatter.parse(sDateTime); 
		   java.util.Date eeDateTime= myFormatter.parse(eDateTime);
		 long  l=(eeDateTime.getTime()-ssDateTime.getTime());
		   long day=l/(24*60*60*1000);
		   long hour=(l/(60*60*1000)-day*24);
		   long min=((l/(60*1000))-day*24*60-hour*60);
		   long s=(l/1000-day*24*60*60-hour*60*60-min*60);
		   return ""+day+"天"+hour+"小时"+min+"分"+s+"秒";
	}
	public static int getTime(String sDateTime,String eDateTime) throws ParseException {
		 SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		   java.util.Date ssDateTime= myFormatter.parse(sDateTime); 
		   java.util.Date eeDateTime= myFormatter.parse(eDateTime);
		 long  l=(eeDateTime.getTime()-ssDateTime.getTime());
		   long day=l/(24*60*60*1000);
		   return (int)day;
	}
	public static long getPlusTotalTime(String sDateTime,String eDateTime) throws ParseException {
		 SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		   java.util.Date ssDateTime= myFormatter.parse(sDateTime); 
		   java.util.Date eeDateTime= myFormatter.parse(eDateTime);
		   return  (eeDateTime.getTime()-ssDateTime.getTime());
	}
 public static String getWeek(String sdate) {   
     // 再转换为时间   
     Date date = TimeHelper.strToDate(sdate);   
     Calendar c = Calendar.getInstance();   
     c.setTime(date);   
     // int hour=c.get(Calendar.DAY_OF_WEEK);   
     // hour中存的就是星期几了，其范围 1~7   
     // 1=星期日 7=星期六，其他类推   
     return new SimpleDateFormat("EEEE").format(c.getTime());   
 }   

 public static Date strToDate(String strDate) {   
     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");   
     ParsePosition pos = new ParsePosition(0);   
     Date strtodate = formatter.parse(strDate, pos);   
     return strtodate;   
 }   
 
	public static String convertToString(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return formatter.format(date);
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}
	}
 public static String getTwoDay(String sj1, String sj2) {   
     SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");   
     long day = 0;   
     try {   
      java.util.Date date = myFormatter.parse(sj1);   
      java.util.Date mydate = myFormatter.parse(sj2);   
      day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);   
     } catch (Exception e) {   
      return "";   
     }   
     return day + "";   
 }   
	public static String getTimeByMillis(long now) throws ParseException {
		  DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTimeInMillis(now);
		  return formatter.format(calendar.getTimeInMillis());
	}
	
	 public static void main(String[] args) throws ParseException {
		 long s =1348211141000L;
		 System.out.println(new TimeHelper().getTimeByMillis(s));
	  }
}
