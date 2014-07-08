/** 
 * Project Name:fesdmp 
 * File Name:IOrder.java 
 * Package Name:cn.bjfu.fesdmp.frame 
 * Date:2014年7月8日 下午11:58:43 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.frame;  
/** 
 * ClassName:IOrder <br/> 
 * Function: 排序的接口类. <br/> 
 * Reason:   排序的接口类. <br/> 
 * Date:     2014年7月8日 下午11:58:43 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public interface IOrder {
	public abstract void addOrderBy(String key, String direction);
	public abstract String convertToSQL();
}
 