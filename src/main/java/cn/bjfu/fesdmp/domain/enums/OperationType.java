/** 
 * Project Name:fesdmp 
 * File Name:OperationType.java 
 * Package Name:cn.bjfu.fesdmp.domain.enums 
 * Date:2014年7月8日 下午9:53:32 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.domain.enums;  
/** 
 * ClassName:OperationType <br/> 
 * Function: 操作类型. <br/> 
 * Reason:   操作类型. <br/> 
 * Date:     2014年7月8日 下午9:53:32 <br/> 
 * @author   zhangzhaoyu 
 * @version   1.0
 * @since    JDK 1.7 
 * @see       
 */
public enum OperationType {
	
	ADD("1"), DELETE("2"), UPDATE("3"), QUERY("4"), OTHERS("5");
	
	private String value;
	private OperationType(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
}
 