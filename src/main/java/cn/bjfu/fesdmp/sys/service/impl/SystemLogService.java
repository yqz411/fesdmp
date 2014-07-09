/** 
 * Project Name:fesdmp 
 * File Name:SystemLogService.java 
 * Package Name:cn.bjfu.fesdmp.sys.service.impl 
 * Date:2014年7月9日 上午10:29:49 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.sys.service.impl;  

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bjfu.fesdmp.domain.sys.SystemLog;
import cn.bjfu.fesdmp.frame.IOrder;
import cn.bjfu.fesdmp.sys.dao.ISystemLogDao;
import cn.bjfu.fesdmp.sys.service.ISystemLogService;
import cn.bjfu.fesdmp.utils.Pagination;

/** 
 * ClassName:SystemLogService <br/> 
 * Function: SystemLogService. <br/> 
 * Reason:   SystemLogService. <br/> 
 * Date:     2014年7月9日 上午10:29:49 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
@Service
@Transactional
public class SystemLogService implements ISystemLogService {

	@Autowired
	private ISystemLogDao systemLogDao;
	
	@Override
	public void addSysLog(SystemLog systemLog) {
		this.systemLogDao.insert(systemLog);
	}

	@Override
	public void deleteSysLog(SystemLog systemLog) {
		this.systemLogDao.delete(systemLog);
	}

	@Transactional(readOnly = true)
	@Override
	public List<SystemLog> queryAll() {
		return this.systemLogDao.findAll();
	}
	
	@Transactional(readOnly = true)
	@Override
	public void queryByCondition(Object condition, IOrder order,
			Pagination<SystemLog> page) {
		this.systemLogDao.findByCondition(condition, order, page);
	}

}
 