/** 
 * Project Name:fesdmp 
 * File Name:SystemLogDaoImpl.java 
 * Package Name:cn.bjfu.fesdmp.sys.dao.impl 
 * Date:2014年7月9日 上午12:38:25 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.sys.dao.impl;  

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import cn.bjfu.fesdmp.domain.sys.SystemLog;
import cn.bjfu.fesdmp.frame.dao.IOrder;
import cn.bjfu.fesdmp.frame.dao.JoinMode;
import cn.bjfu.fesdmp.sys.dao.ISystemLogDao;
import cn.bjfu.fesdmp.utils.DateFormat;
import cn.bjfu.fesdmp.utils.Pagination;
import cn.bjfu.fesdmp.web.jsonbean.LogSearch;

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
	
	private static final Logger logger = Logger.getLogger(SystemLogDaoImpl.class);
	
	public SystemLogDaoImpl() {
		super(SystemLog.class);
	}

	@Override
	public List<SystemLog> findByCondtinWithOperationTime(LogSearch condition,
			IOrder order, Pagination<SystemLog> page, JoinMode joinMode) {
		String jpal = " SELECT p FROM SystemLog p ";
		if (condition != null) {
			jpal += convertBeanToJPAL(condition, joinMode);
			if (condition.getStartTime() != null && condition.getEndTime() != null) {
				jpal += " AND ( p.operateTime >= '" + DateFormat.getShortDate(condition.getStartTime()) +
						"' AND  p.operateTime <= '" + DateFormat.getShortDate(condition.getEndTime()) + "' ) ";
			}
			if (condition.getStartTime() != null && condition.getEndTime() == null) {
				jpal += " AND ( p.operateTime >=  '" + DateFormat.getShortDate(condition.getStartTime()) + 
						"' AND p.operateTime <= '" + DateFormat.getShortDate(new Date()) + "' ) ";
			}
		} 
		if (order != null) {
			jpal += convertToSQL(order);
		}
		
		logger.info(jpal);
		Query query = super.getEntityManager().createQuery(jpal);
		if (page != null) {
			page.setTotalRecord(query.getResultList().size());
			List<SystemLog> result =  query.setFirstResult(page.getOffset()).setMaxResults(page.getPageSize()).getResultList();
			page.setDatas(result);
			return result;
		}else{
			return query.getResultList();
		}
		
	}

}
 