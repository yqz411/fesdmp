/** 
 * Project Name:fesdmp 
 * File Name:CustomDateSerializer.java 
 * Package Name:cn.bjfu.fesdmp.frame 
 * Date:2014年7月11日 下午5:33:46 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.frame;  

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/** 
 * ClassName:CustomDateSerializer <br/> 
 * Function: CustomDateSerializer. <br/> 
 * Reason:   CustomDateSerializer. <br/> 
 * Date:     2014年7月11日 下午5:33:46 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class CustomDateSerializer extends JsonSerializer<Date> {
	@Override
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String formattedDate = formatter.format(value);
		 jgen.writeString(formattedDate);
	}
}
 