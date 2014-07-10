/** 
 * Project Name:fesdmp 
 * File Name:TestJackson.java 
 * Package Name:cn.bjfu.fesdmp.jackson 
 * Date:2014年7月10日 下午1:41:19 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.jackson;  

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import cn.bjfu.fesdmp.domain.enums.BusinessType;
import cn.bjfu.fesdmp.domain.enums.OperationType;
import cn.bjfu.fesdmp.domain.sys.SystemLog;

/** 
 * ClassName:TestJackson <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年7月10日 下午1:41:19 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class TestJackson {

	@Test
	public void test() {
		SystemLog log = new SystemLog();
		log.setBusinessType(BusinessType.SYS_LOGIN);
		log.setOperateContent("user login");
		log.setOperateTime(new Date());
		log.setOperationType(OperationType.QUERY);
		log.setUserName("Lilei");
		log.setUserSourceIp("202.204.110.22");
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String result = mapper.writeValueAsString(log);
			System.out.println(result);
			
			log = mapper.readValue(result, SystemLog.class);
			System.out.println("log : " + log);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testToObj() {
		String json = "{\"userName\" : \"zhang\", \"operateContent\" : \"zhang\"}";
		ObjectMapper mapper = new ObjectMapper();
		SystemLog log;
		try {
			log = mapper.readValue(json, SystemLog.class);
			System.out.println(log);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGson() {
		String json = "{userName : zhang , operateContent :zhang }";
		Gson gson = new Gson();
		SystemLog log = gson.fromJson(json, SystemLog.class);
		System.out.println("Gson : " + log);
	}
	
}
 