/** 
 * Project Name:fesdmp 
 * File Name:SystemLogDaoImpl.java 
 * Package Name:cn.bjfu.fesdmp.sys.dao.impl 
 * Date:2014年7月9日 上午12:38:25 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.sys.dao.impl;  

import org.springframework.stereotype.Repository;

import cn.bjfu.fesdmp.domain.sys.SystemLog;
import cn.bjfu.fesdmp.sys.dao.ISystemLogDao;

/** 
 * ClassName:SystemLogDaoImpl <br/> 
 * Function: SystemLogDaoImpl. <br/> 
 * Reason:   SystemLogDaoImpl. <br/> 
 * Date:     2014年7月9日 上午12:38:25 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
@Repository
public class SystemLogDaoImpl extends AbstractGenericDao<SystemLog> implements ISystemLogDao {

	public SystemLogDaoImpl() {
		super(SystemLog.class);
	}

}
 