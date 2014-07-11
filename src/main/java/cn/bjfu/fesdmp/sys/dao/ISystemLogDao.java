/** 
 * Project Name:fesdmp 
 * File Name:ISystemLogDao.java 
 * Package Name:cn.bjfu.fesdmp.sys.dao 
 * Date:2014年7月9日 上午12:37:30 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.sys.dao;  

import java.util.List;

import cn.bjfu.fesdmp.domain.sys.SystemLog;
import cn.bjfu.fesdmp.frame.dao.IOrder;
import cn.bjfu.fesdmp.frame.dao.JoinMode;
import cn.bjfu.fesdmp.utils.Pagination;
import cn.bjfu.fesdmp.web.jsonbean.LogSearch;

/** 
 * ClassName:ISystemLogDao <br/> 
 * Function: ISystemLogDao. <br/> 
 * Reason:   ISystemLogDao. <br/> 
 * Date:     2014年7月9日 上午12:37:30 <br/> 
 * @author   zhangzhaoyu 
 * @version   1.0
 * @since    JDK 1.7 
 * @see       
 */
public interface ISystemLogDao extends IGenericDao<SystemLog> {

	/**
	 * 
	 * findByCondtinWithOperationTime:<br />
	 * 查询包括时间
	 *
	 * @author zhangzhaoyu
	 * @param condition
	 * @param order
	 * @param page
	 * @param joinMode
	 */
	public abstract List<SystemLog> findByCondtinWithOperationTime(final LogSearch condition, 
			IOrder order, Pagination<SystemLog> page, JoinMode joinMode);
	
}
 