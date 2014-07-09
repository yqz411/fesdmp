/** 
 * Project Name:fesdmp 
 * File Name:AbstractGenericDao.java 
 * Package Name:cn.bjfu.fesdmp.sys.dao.impl 
 * Date:2014年7月8日 下午10:17:35 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.sys.dao.impl;  

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;

import cn.bjfu.fesdmp.frame.IOrder;
import cn.bjfu.fesdmp.frame.JoinMode;
import cn.bjfu.fesdmp.sys.dao.IGenericDao;
import cn.bjfu.fesdmp.utils.Pagination;

/** 
 * ClassName:AbstractGenericDao <br/> 
 * Function: IGenericDao 的抽象实现类. <br/> 
 * Reason:   IGenericDao 的抽象实现类. <br/> 
 * Date:     2014年7月8日 下午10:17:35 <br/> 
 * @author   zhangzhaoyu 
 * @version   1.0
 * @since    JDK 1.7 
 * @see       
 */
public abstract class AbstractGenericDao<T> implements IGenericDao<T> {

	private static final Logger logger = Logger.getLogger(AbstractGenericDao.class);
	
	private Class<T> entityClass;
	
	@PersistenceContext
	private EntityManager em;
	
	public AbstractGenericDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	@Override
	public void insert(T t) {
		if (t != null) {
			this.em.persist(t);
		}
	}

	@Override
	public void delete(T t) {
		this.em.remove(t);
	}

	@Override
	public void update(T t) {
		this.em.merge(t);
	}

	@Override
	public T findByKey(Serializable key) {
		return this.em.find(entityClass, key);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		String jpql = " SELECT p FROM " + this.entityClass.getSimpleName() + " p ";
		return this.em.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByProperty(final Map<String, Object> map) {
		String qlString = convertSqlFromMap(map);
		Query query = this.em.createQuery(qlString);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void findByPage(String jpql, Object[] values, Pagination<T> page) {
		
		Query query = this.em.createQuery(jpql);
		setQueryParams(query, values);
		page.setTotalRecord(query.getResultList().size());
		List<T> result = query.setFirstResult(page.getOffset())
				.setMaxResults(page.getPageSize()).getResultList();
		
		page.setDatas(result);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByCondition(Object condition, IOrder order, Pagination<T> page) {
		String jpal = " SELECT p FROM "+ entityClass.getSimpleName() +" p WHERE 1 = 1 ";
		if (condition != null) {
			jpal += convertBeanToAndQL(condition);
		}
		if (order != null) {
			jpal += order.convertToSQL();
		}
		Query query = this.em.createQuery(jpal);
		if (page != null){
			page.setTotalRecord(query.getResultList().size());
			List<T> result =  query.setFirstResult(page.getOffset()).setMaxResults(page.getPageSize()).getResultList();
			page.setDatas(result);
			return result;
		}else{
			return query.getResultList();
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCondition(final Object condition, IOrder order, 
			Pagination<T> page, JoinMode joinMode) {
		String jpal = null;
		if (condition != null) {
			jpal = convertBeanToJPAL(condition, joinMode);
		} else {
			jpal = "SELECT p FROM "+ entityClass.getSimpleName() +" p ";
		}
		
		if (order != null) {
			jpal += order.convertToSQL();
		}
		
		logger.info(jpal);
		Query query = this.em.createQuery(jpal);
		if (page != null) {
			page.setTotalRecord(query.getResultList().size());
			List<T> result =  query.setFirstResult(page.getOffset()).setMaxResults(page.getPageSize()).getResultList();
			page.setDatas(result);
			return result;
		}else{
			return query.getResultList();
		}
	}
	
	private String convertSqlFromMap(Map<String, Object> map) {
		String sql = "SELECT p FROM " + this.entityClass.getSimpleName() + " p WHERE 1 = 1 ";
		StringBuilder builder = new StringBuilder(sql);
		Set<String> set = map.keySet();
		
		for (String key : set) {
			builder.append(" AND " + key +" = " + map.get(key));
		}
		
		return builder.toString();
	}
	
	private void setQueryParams(Query query, Object[] params) {  
        if (null == params) {  
            return;  
        }
        for (int i = 0; i < params.length; i++) {  
            query.setParameter(i, params[i]);  
        }
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
	
	/**
	 * 
	 * convertBeanToJPAL:<br />
	 * 将给定的Bean 按照JoinMode 拼接成JPQL
	 * 注意 ： 本函数只能处理数值和字符串
	 *
	 * @author zhangzhaoyu
	 * @param bean 
	 * 不能为空
	 * @param joinMode
	 * 枚举类型取值为 AND 或者 OR
	 * @return
	 */
	protected String convertBeanToJPAL(Object bean, JoinMode joinMode) {
		Class clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		StringBuilder qlStirng = new StringBuilder();
		
		if (joinMode == JoinMode.AND) {
			qlStirng.append(" SELECT p FROM "+ entityClass.getSimpleName() +" p WHERE 1 = 1 ");
			for (Field field : fields) {
				String filedName = field.getName();
				String firstLetter = filedName.substring(0, 1).toUpperCase();
				String getMethodName = "get" + firstLetter + filedName.substring(1);
				try {
					Method getMethod = clazz.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(bean, new Object[]{});
					
					String type = field.getGenericType().toString();
					int modifier = field.getModifiers();
					// just private not final static 
					if (Modifier.isPrivate(modifier) && !Modifier.isFinal(modifier) 
							&& !Modifier.isStatic(modifier) && value != null) {
						if (type.contains("String")) {
							qlStirng.append(" AND p." + filedName + " like '%" + value + "%'");
						} else if (type.contains("Integer") || type.contains("Long") 
								|| type.contains("Short") || type.contains("Byte")) {
							qlStirng.append(" AND p." + filedName + " = " + value);
						}
					} 
				} catch (Exception e) {
					logger.info("getMethod " + getMethodName +" not found!");
				}
			}
			return qlStirng.toString();
		}
		else if (joinMode == JoinMode.OR) {
			qlStirng.append(" SELECT p FROM "+ entityClass.getSimpleName() +" p WHERE ");
			int size = fields.length;
			for (int i=0; i<size; i++) {
				Field field = fields[i];
				String filedName = field.getName();
				String firstLetter = filedName.substring(0, 1).toUpperCase();
				String getMethodName = "get" + firstLetter + filedName.substring(1);
				try {
					
					Method getMethod = clazz.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(bean, new Object[]{});
					
					String type = field.getGenericType().toString();
					int modifier = field.getModifiers();
					// just private not final static 
					if (Modifier.isPrivate(modifier) && !Modifier.isFinal(modifier) 
							&& !Modifier.isStatic(modifier) && value != null && i == (size -1)) {
						if (type.contains("String")) {
							qlStirng.append(" p." + filedName + " like '%" + value + "%' ");
						} else if (type.contains("Integer") || type.contains("Long") 
								|| type.contains("Short") || type.contains("Byte")) {
							qlStirng.append(" p." + filedName + " = " + value);
						}
					} else if (Modifier.isPrivate(modifier) && !Modifier.isFinal(modifier) 
							&& !Modifier.isStatic(modifier) && value != null && i < (size -1)) {
						if (type.contains("String")) {
							qlStirng.append(" p." + filedName + " like '%" + value + "%' OR ");
						} else if (type.contains("Integer") || type.contains("Long") 
								|| type.contains("Short") || type.contains("Byte")) {
							qlStirng.append(" p." + filedName + " = " + value + " OR ");
						}
					}
				} catch (Exception e) {
					logger.info("getMethod " + getMethodName +" not found!");
				}
			}
			String result = qlStirng.toString();
			int index = result.lastIndexOf("OR");
			return result.substring(0, index);
		}
		return null;
	}
	
	
	@SuppressWarnings("rawtypes")
	protected String convertBeanToAndQL(Object bean) {
		StringBuilder qlString = new StringBuilder();
		try {
			Map map = PropertyUtils.describe(bean);
			Set keyset = map.entrySet();
			for (Object key : keyset) {
				Object value = map.get(key);
				String type = PropertyUtils.getPropertyType(bean, key.toString()).toString();
				
				if (type.contains("String") && value != null) {
					qlString.append(" AND p." + key + " like '%" + value + "%'");
				} else if ((type.contains("Integer") || type.contains("Long") 
						|| type.contains("Short") || type.contains("Byte")) && value != null) {
					qlString.append(" AND p." + key + " = " + value);
				}
			}
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			logger.info("convertBeanToQL exception.");
		}
		return qlString.toString();
	}
}
 