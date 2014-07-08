/** 
 * Project Name:fesdmp 
 * File Name:BusinessType.java 
 * Package Name:cn.bjfu.fesdmp.domain.enums 
 * Date:2014年7月8日 下午9:47:40 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.domain.enums;  
/** 
 * ClassName:BusinessType <br/> 
 * Function: 系统业务类型. <br/> 
 * Reason:   系统业务类型. <br/> 
 * Date:     2014年7月8日 下午9:47:40 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public enum BusinessType {

	SYS_LOGIN("1"), SYS_LOGOUT("2"), SYS_OPERATE("3"), SYS_OTHERS("4");
	
	private String value;
	private BusinessType(String value) {
		this.value = value;
	}
	
	public String value() {
		return this.value;
	}
	
}
 