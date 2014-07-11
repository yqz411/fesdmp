/** 
 * Project Name:fesdmp 
 * File Name:TestGson.java 
 * Package Name:cn.bjfu.fesdmp.jackson 
 * Date:2014年7月10日 下午4:06:27 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.jackson;  

import java.util.Date;

import org.junit.Test;

import cn.bjfu.fesdmp.web.jsonbean.LogSearch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/** 
 * ClassName:TestGson <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年7月10日 下午4:06:27 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class TestGson {

	@Test
	public void test() {
		Gson gson = //new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd").create();
				new Gson();
		LogSearch logSearch = new LogSearch();
		logSearch.setUserName("xxx");
		logSearch.setStartTime(new Date());
		String result = gson.toJson(logSearch);
		String json = "{\"userName\":\"xxxxx\",\"startTime\":\"Jul 11, 2014 5:55:48 PM\"}";
		System.out.println(result);
		logSearch = gson.fromJson(json, LogSearch.class);
		System.out.println(logSearch);
	}
	
	
}
 