/** 
 * Project Name:fesdmp 
 * File Name:Base64Test.java 
 * Package Name:cn.bjfu.fesdmp.codec 
 * Date:2014年7月7日 下午10:19:54 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.codec;  

import org.apache.commons.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;



/** 
 * ClassName:Base64Test <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年7月7日 下午10:19:54 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class Base64Test {
	
	private Base64 base64;
	
	@Before
	public void inti() {
		base64 = new Base64();
	}
	
	@Test
	public void testBase64() {
		String str = "zhangzhaoyu";
		byte[] enbytes = base64.encode(str.getBytes());
		String encodeStr = new String(enbytes);
		System.out.println(encodeStr);
		
		byte[] decodebytes = base64.decode(encodeStr);
		String decodeStr = new String(decodebytes);
		System.out.println(decodeStr);
	}
	
}
 