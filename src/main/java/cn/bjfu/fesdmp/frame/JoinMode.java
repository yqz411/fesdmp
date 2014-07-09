/** 
 * Project Name:fesdmp 
 * File Name:JoinMode.java 
 * Package Name:cn.bjfu.fesdmp.frame 
 * Date:2014年7月9日 下午11:47:24 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.frame;  
/** 
 * ClassName:JoinMode <br/> 
 * Function: JoinMode SQL 语句的联结类型. <br/> 
 * Reason:   JoinMode SQL 语句的联结类型. <br/> 
 * Date:     2014年7月9日 下午11:47:24 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public enum JoinMode {

	AND("ADN"), OR("OR");
	
	private String value;
	private JoinMode(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
}
 