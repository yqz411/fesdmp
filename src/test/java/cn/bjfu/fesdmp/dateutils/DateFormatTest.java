/** 
 * Project Name:fesdmp 
 * File Name:DateFormatTest.java 
 * Package Name:cn.bjfu.fesdmp.dateutils 
 * Date:2014年7月7日 下午10:40:07 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.dateutils;  

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.junit.Test;

/** 
 * ClassName:DateFormatTest <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年7月7日 下午10:40:07 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class DateFormatTest {

	@Test
	public void test() {
		String date = DateFormatUtils.ISO_DATE_FORMAT.format(new Date());
		System.out.println(date);
		String dateStr = DateFormatUtils.format(new Date(), "yyyy-MM-dd");
		System.out.println(dateStr);
		
		FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
		System.out.println(fdf.format(new Date()));
		System.out.println(fdf.format(System.currentTimeMillis()));
	}
	
}
 