/** 
 * Project Name:fesdmp 
 * File Name:IGenericDao.java 
 * Package Name:cn.bjfu.fesdmp.sys.dao 
 * Date:2014年7月8日 下午10:04:42 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.sys.dao;  

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import cn.bjfu.fesdmp.frame.IOrder;
import cn.bjfu.fesdmp.frame.JoinMode;
import cn.bjfu.fesdmp.utils.Pagination;

/** 
 * ClassName:IGenericDao <br/> 
 * Function: 通用DAO接口 <br/> 
 * Reason:   通用DAO接口. <br/> 
 * Date:     2014年7月8日 下午10:04:42 <br/> 
 * @author   zhangzhaoyu 
 * @version   1.0
 * @since    JDK 1.7 
 * @see       
 */
public interface IGenericDao<T> {

	public abstract void insert(T t);
	public abstract void delete(T t);
	public abstract void update(T t);
	public abstract T findByKey(Serializable key);
	/**
	 * 
	 * findAll:<br />
	 * 查询所有的记录
	 *
	 * @author zhangzhaoyu
	 * @return
	 */
	public abstract List<T> findAll(IOrder order);
	/**
	 * 
	 * findByProperty:<br />
	 * 按条件查询 map
	 *
	 * @author zhangzhaoyu
	 * @param map
	 * map 参数键值对的属性集合
	 * @return
	 */
	public abstract List<T> findByProperty(final Map<String, Object> map, IOrder order);
	public abstract void findByPage(String jpql, Object[] values, Pagination<T> page, IOrder order);
	/**
	 * 
	 * findByCondition:<br />
	 * 所有参数均可为空，根据condition 的and 联结查询
	 *
	 * @author zhangzhaoyu
	 * @param condition
	 * 查询实体类
	 * @param order
	 * 排序
	 * @param page
	 * 分页
	 * @return
	 */
	public abstract List<T> findByCondition(final Object condition, IOrder order, Pagination<T> page);
	/**
	 * 
	 * findByCondition:<br />
	 * 根据给定参数查询，所有参数均可为空，但是 condition 
	 * 必须与 joinMode 同时出现 
	 *
	 * @author zhangzhaoyu
	 * @param condition
	 * 查询实体Bean
	 * @param order
	 * 排序字段
	 * @param page
	 * 分页字段
	 * @param joinMode
	 * 查询状态字段，取值为AND 或者 OR
	 * @return
	 */
	public abstract List<T> findByCondition(final Object condition, IOrder order, Pagination<T> page, JoinMode joinMode);
	
}
 