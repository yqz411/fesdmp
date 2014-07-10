/** 
 * Project Name:fesdmp 
 * File Name:Order.java 
 * Package Name:cn.bjfu.fesdmp.frame 
 * Date:2014年7月8日 下午11:58:06 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.frame.dao;  

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/** 
 * ClassName:Order <br/> 
 * Function: 排序的时候传入的排序类. <br/> 
 * Reason:   排序的时候传入的排序类. <br/> 
 * Date:     2014年7月8日 下午11:58:06 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class Order implements IOrder {

	private Map<String, Object> map;
	public Order() {
		map = new LinkedHashMap<String, Object>();
	}
	
	public Order(String key, String direction) {
		map = new LinkedHashMap<String, Object>();
		map.put(key, direction);
	}
	
	@Override
	public void addOrderBy(String key, String direction) {
		map.put(key, direction);
	}

	@Override
	public String convertToSQL() {
		StringBuilder builder = new StringBuilder(" ORDER BY ");
		Set<String> set = map.keySet();
		int size = set.size();
		if (size == 1) {
			for (String key : set) {
				builder.append(key +" " + map.get(key));
			}
			return builder.toString();
		} else if (size > 1) {
			for (String key : set) {
				builder.append(key +" " + map.get(key) +" ,");
			}
			String result = builder.toString();
			return result.substring(0, result.lastIndexOf(","));
		}
		return null;
	}

	public static void main(String[] args) {
		IOrder order = new Order();
		order.addOrderBy("name", "DESC");
		System.out.println(order.convertToSQL());
	}

	@Override
	public Map<String, Object> getOrderByMap() {
		return this.map;
	}
	
}
 