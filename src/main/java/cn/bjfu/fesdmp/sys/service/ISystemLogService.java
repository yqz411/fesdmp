/** 
 * Project Name:fesdmp 
 * File Name:ISystemLogService.java 
 * Package Name:cn.bjfu.fesdmp.sys.service 
 * Date:2014年7月9日 上午10:25:41 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.sys.service;  

import java.util.List;

import cn.bjfu.fesdmp.domain.sys.SystemLog;
import cn.bjfu.fesdmp.frame.dao.IOrder;
import cn.bjfu.fesdmp.frame.dao.JoinMode;
import cn.bjfu.fesdmp.utils.Pagination;

/** 
 * ClassName:ISystemLogService <br/> 
 * Function: ISystemLogService. <br/> 
 * Reason:   ISystemLogService. <br/> 
 * Date:     2014年7月9日 上午10:25:41 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public interface ISystemLogService {

	public abstract void addSysLog(SystemLog systemLog);
	public abstract void deleteSysLog(SystemLog systemLog);
	public abstract List<SystemLog> queryAll(IOrder order);
	public abstract void queryByCondition(final Object condition, IOrder order, Pagination<SystemLog> page);
	public abstract List<SystemLog> queryByCondition(final Object condition, IOrder order, Pagination<SystemLog> page, JoinMode joinMode);
}
 