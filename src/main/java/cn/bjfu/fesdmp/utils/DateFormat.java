package cn.bjfu.fesdmp.utils;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

/**
 * 时间处理工具类
 * @author FuZhijun
 * @version 1.0
 */
public class DateFormat {

	private static SimpleDateFormat formatter;

	/**
	 * 将日期格式化成yyyy-MM-dd的字符串
	 * @param date Date 时间对象
	 * @return String
	 */
	public static String getShortDate(Date date) {
		try {
			formatter = new SimpleDateFormat("yyyy-MM-dd");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formatter.format(date);
	}

	/**
	 * 将日期格式化成yyyyMMddHHmmssSSS的字符串
	 * @param date Date 时间对象
	 * @return String
	 */
	public static String getFullDate(Date date) {
		try {
			formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formatter.format(date);
	}

	/**
	 * 将日期格式化成yyyy-MM-dd HH:mm:ss的字符串
	 * @param date Date 时间对象
	 * @return String
	 */
	public static String getGeneralDate(Date date) {
		try {
			formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formatter.format(date);
	}

	/**
	 * 将日期格式化成HH:mm:ss的字符串
	 * @param date Date 时间对象
	 * @return String
	 */
	public static String getTime(Date date) {
		try {
			formatter = new SimpleDateFormat("HH:mm:ss");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formatter.format(date);
	}

	/**
	 * 将日期格式化成yyyyMMddHHmmZ的字符串
	 * @param date Date 时间对象
	 * @return String
	 */
	public static String getLDAPDate(Date date) {
		try {
			formatter = new SimpleDateFormat("yyyyMMddHHmm'Z'");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formatter.format(date);
	}

	/**
	 * 根据格式化方式对日期进行格式化
	 * @param date Date 时间对象
	 * @param format String 格式化串
	 * @return String
	 */
	public static String formatDate(Date date, String format) {
		try {
			formatter = new SimpleDateFormat(format);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formatter.format(date);
	}

	/**
	 * 根据字符串得到时间对象
	 * @param yyyymmdd String yyyymmdd格式的字符串
	 * @return Date
	 */
	public static Date getDate(String yyyymmdd) {
		try {
			if ((null == yyyymmdd) || (yyyymmdd.trim().length() == 0))
				return null;
			int year = Integer.parseInt(yyyymmdd.substring(0, yyyymmdd.indexOf("-")));
			int month = Integer.parseInt(yyyymmdd.substring(yyyymmdd.indexOf("-") + 1, yyyymmdd.lastIndexOf("-")));
			int day = Integer.parseInt(yyyymmdd.substring(yyyymmdd.lastIndexOf("-") + 1));
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month - 1, day);
			return calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据字符串得到通用时间对象
	 * @param stringDate String 时间格式的字符串
	 * @return Date
	 */
	public static Date parserGeneralDate(String stringDate) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			return simpleDateFormat.parse(stringDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据字符串和格式化方式得到时间对象
	 * @param stringDate String 时间格式的字符串
	 * @param formatter String 格式化方式
	 * @return Date
	 */
	public static Date parserDate(String stringDate, String formatter) {
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter);
			return simpleDateFormat.parse(stringDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据日期和增加的分钟得到时间对象
	 * @param date Date 时间对象
	 * @param minutes Integer 增加分钟数
	 * @return Date
	 */
	public static Date addMinute(Date date, int minutes) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			int minute = 0;
			minutes = -(minutes);
			if (minutes > 60) {
				int hour = (int) minutes / 60;
				if (hour * 60 > minutes) {
					minute = hour * 60 - minutes;
					calendar.add(Calendar.HOUR_OF_DAY, -hour);
					calendar.add(Calendar.MINUTE, minute);
				} else if (hour * 60 < minutes) {
					minute = minutes - hour * 60;
					calendar.add(Calendar.HOUR_OF_DAY, -hour);
					calendar.add(Calendar.MINUTE, -minute);
				} else {
					calendar.add(Calendar.HOUR_OF_DAY, -hour);
				}
			} else {
				calendar.add(Calendar.MINUTE, -minutes);
			}
			return calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据日期和增加的天数得到时间对象
	 * @param date Date 时间对象
	 * @param days Integer 增加天数
	 * @return Date
	 */
	public static Date addDay(Date date, int days) {
		try {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, days);
			return calendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据日期和增加的月数得到时间对象
	 * @param date Date 时间对象
	 * @param months Integer 增加月数
	 * @return Date
	 */
	public static Date addMonth(Date date, int months) {
		try {
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(date);
			boolean isEndDayOfMonthTrue = gregorianCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) == gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
			gregorianCalendar.add(GregorianCalendar.MONTH, months);
			boolean isEndDayOfMonthFalse = gregorianCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) == gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
			if (isEndDayOfMonthTrue && !isEndDayOfMonthFalse) {
				gregorianCalendar.set(GregorianCalendar.DATE, gregorianCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
			}
			return gregorianCalendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据日期和增加的年数得到时间对象
	 * @param date Date 时间对象
	 * @param years Integer 增加年数
	 * @return Date
	 */
	public static Date addYear(Date date, int years) {
		try {
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(date);
			boolean isEndDayOfMonthTrue = gregorianCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) == gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
			gregorianCalendar.add(GregorianCalendar.YEAR, years);
			boolean isEndDayOfMonthFalse = gregorianCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH) == gregorianCalendar.get(GregorianCalendar.DAY_OF_MONTH);
			if (isEndDayOfMonthTrue && !isEndDayOfMonthFalse) {
				gregorianCalendar.set(GregorianCalendar.DATE, gregorianCalendar.getActualMaximum(GregorianCalendar.DAY_OF_MONTH));
			}
			return gregorianCalendar.getTime();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据日期得到工作日
	 * @param date Date 时间对象
	 * @return Integer
	 */
	public static int getWeekDay(Date date) {
		int result = 0;
		try {
			GregorianCalendar gregorianCalendar = new GregorianCalendar();
			gregorianCalendar.setTime(date);
			result = gregorianCalendar.get(GregorianCalendar.DAY_OF_WEEK) - 1;
			if (result == 0) {
				return 7;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据日期得到时间
	 * @param date Date 时间对象
	 * @return Integer
	 */
	public static int getTimeFromDate(Date date) {
		int result = 0;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hhmmss");
			result = Integer.parseInt(simpleDateFormat.format(date));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据数据库 timeStamp对象生成时间
	 * @param time 
	 * @param formatDate 
	 * @return
	 */
	public static Timestamp getTimestampFromTime(String time){
		return new Timestamp((DateFormat.parserDate(time, "yyyy-MM-dd HH:mm:ss").getTime()));
	}
	
	/**
	 * 获取当天00：00：00时间对象
	 * @return
	 */
	public static Date getZeroDate(){
		Calendar calendar = Calendar.getInstance();
		String date = DateFormat.formatDate(calendar.getTime(), "yyyy-MM-dd 00:00:00");
		return DateFormat.parserDate(date, "yyyy-MM-dd HH:mm:ss");
	}
	public static void main(String[] args) {
		String ss = "2014-5-6.16.32. 14. 0";
		Timestamp ts = new Timestamp(System.currentTimeMillis());  
        try {  
            ts = Timestamp.valueOf(ss);  
            System.out.println(ts);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	/**
	 * Definition: 获取上周一
	 * author: yangfan
	 * Created date: Jun 18, 2014
	 * @param date
	 * @return
	 */
	public static Date getLastWeekMonday(Date date) {    
        Date a = DateUtils.addDays(date, -1);    
       Calendar cal = Calendar.getInstance();    
       cal.setTime(a);    
       cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周    
       cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);    
              
        return cal.getTime();    
   }
	
	/**
	 * Definition: 获取上上周一
	 * author: yangfan
	 * Created date: Jun 26, 2014
	 * @param date
	 * @return
	 */
	public static Date getBeforeWeekMonday(Date date) {    
        Date a = DateUtils.addDays(date, -1);    
       Calendar cal = Calendar.getInstance();    
       cal.setTime(a);    
       cal.add(Calendar.WEEK_OF_YEAR, -2);// 一周    
       cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);    
              
        return cal.getTime();    
   }
	
	/**
	 * Definition: 获取上周日
	 * author: yangfan
	 * Created date: Jun 18, 2014
	 * @param date
	 * @return
	 */
	public static Date getLastWeekSunday(Date date) {    
	    Calendar c = Calendar.getInstance();
	    c.setFirstDayOfWeek(Calendar.MONDAY);
	    c.add(Calendar.WEEK_OF_MONTH, -1); 
	    c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
	    return c.getTime();    
    }
	
	/**
	 * Definition: 获取上上周日
	 * author: yangfan
	 * Created date: Jun 26, 2014
	 * @param date
	 * @return
	 */
	public static Date getBeforeWeekSunday(Date date) {    
		Calendar c = Calendar.getInstance();
	    c.setFirstDayOfWeek(Calendar.MONDAY);
	    c.add(Calendar.WEEK_OF_MONTH, -2); 
	    c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);   
        return c.getTime();    
    }
	
	/**
	 * Definition: 获取上个月的第一天和最后一天
	 * author: yangfan
	 * Created date: Jun 18, 2014
	 * @param date
	 * @return
	 */
	public static Map<String, Date> getFirstday_Lastday_Month(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -1);
        Date theDate = calendar.getTime();
        
        //上个月第一天
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);

        //上个月最后一天
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天

        Map<String, Date> map = new java.util.HashMap<String, Date>();
        map.put("first", gcLast.getTime());
        map.put("last", calendar.getTime());
        return map;
    }
	
	
	/**
	 * Definition: 获取上上月的第一天和最后一天
	 * author: yangfan
	 * Created date: Jun 30, 2014
	 * @param date
	 * @return
	 */
	public static Map<String, Date> getFirstday_Lastday_BeforeMonth(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, -2);
        Date theDate = calendar.getTime();
        
        //上个月第一天
        GregorianCalendar gcLast = (GregorianCalendar) Calendar.getInstance();
        gcLast.setTime(theDate);
        gcLast.set(Calendar.DAY_OF_MONTH, 1);

        //上个月最后一天
        calendar.add(Calendar.MONTH, 1);    //加一个月
        calendar.set(Calendar.DATE, 1);        //设置为该月第一天
        calendar.add(Calendar.DATE, -1);    //再减一天即为上个月最后一天

        Map<String, Date> map = new java.util.HashMap<String, Date>();
        map.put("first", gcLast.getTime());
        map.put("last", calendar.getTime());
        return map;
    }
	
	/**
	 * Definition: 获取本月第一天的日期
	 * author: yangfan
	 * Created date: Jun 26, 2014
	 * @return
	 */
	public static String getFirstdayAtMonth(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar c = Calendar.getInstance();    
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
        return format.format(c.getTime());
	}
	
	/**
	 * Definition: 获取本月最后一天的日期
	 * author: yangfan
	 * Created date: Jun 26, 2014
	 * @return
	 */
	public static String getEnddayAtMonth(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); 
		Calendar ca = Calendar.getInstance();    
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));  
        return format.format(ca.getTime());
	}
}