/** 
 * Project Name:fesdmp 
 * File Name:AbstractGenericDao.java 
 * Package Name:cn.bjfu.fesdmp.sys.dao.impl 
 * Date:2014年7月8日 下午10:17:35 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.sys.dao.impl;  

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
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import cn.bjfu.fesdmp.frame.dao.IOrder;
import cn.bjfu.fesdmp.frame.dao.JoinMode;
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
	public List<T> findAll(IOrder order) {
		String jpql = " SELECT p FROM " + this.entityClass.getSimpleName() + " p ";
		if (order != null) {
			jpql += order.convertToSQL();
		}
		return this.em.createQuery(jpql).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findByProperty(final Map<String, Object> map, IOrder order) {
		String jpal = "SELECT p FROM " + this.entityClass.getSimpleName() + " p ";
		jpal += convertSqlFromMap(map);
		if (order != null) {
			jpal += convertToSQL(order);
		}
		Query query = this.em.createQuery(jpal);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void findByPage(String jpql, Object[] values, Pagination<T> page, IOrder order) {
		if (order != null) {
			jpql += convertToSQL(order);
		}
		
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
			jpal += convertToSQL(order);
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
		String jpal = " SELECT p FROM "+ entityClass.getSimpleName() +" p ";
		if (condition != null) {
			jpal += convertBeanToJPAL(condition, joinMode);
		} 
		if (order != null) {
			jpal += convertToSQL(order);
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
	
	/**
	 * 
	 * convertSqlFromMap:<br />
	 * 将给定的Map 转换为 AND 连接的查询语句
	 *
	 * @author zhangzhaoyu
	 * @param map
	 * @return
	 */
	private String convertSqlFromMap(Map<String, Object> map) {
		String sql = " WHERE 1 = 1 ";
		StringBuilder builder = new StringBuilder(sql);
		Set<String> set = map.keySet();
		
		for (String key : set) {
			builder.append(" AND " + key +" = " + map.get(key));
		}
		return builder.toString();
	}
	
	/**
	 * 
	 * setQueryParams:<br />
	 * 设置查询的参数
	 *
	 * @author zhangzhaoyu
	 * @param query
	 * @param params
	 */
	private void setQueryParams(Query query, Object ... params) {  
        if (null == params) {  
            return;  
        }
        for (int i = 0; i < params.length; i++) {  
            query.setParameter(i, params[i]);  
        }
	}
	
	/**
	 * 
	 * getEntityManager:<br />
	 * 获取实体管理器
	 *
	 * @author zhangzhaoyu
	 * @return
	 */
	public EntityManager getEntityManager() {
		return this.em;
	}
	
	/**
	 * 
	 * convertBeanToJPAL:<br />
	 * 将给定的Bean 按照JoinMode 拼接成JPQL
	 * 注意 ： 本函数只能处理数值和字符串(字符串类型排除名字包含time 和 date)
	 *
	 * @author zhangzhaoyu
	 * @param bean 
	 * 不能为空
	 * @param joinMode
	 * 枚举类型取值为 AND 或者 OR
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected String convertBeanToJPAL(Object bean, JoinMode joinMode) {
		Class clazz = bean.getClass();
		Field[] fields = clazz.getDeclaredFields();
		StringBuilder qlStirng = new StringBuilder();
		
		if (joinMode == JoinMode.AND) {
			qlStirng.append(" WHERE 1 = 1 ");
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
							&& !Modifier.isStatic(modifier) && StringUtils.isNotEmpty(value.toString())) {
						if (type.contains("String")&&!filedName.toLowerCase().contains("time")
								&&!filedName.toLowerCase().contains("date")) {
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
			qlStirng.append(" WHERE ");
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
							&& !Modifier.isStatic(modifier) && value != null) {
						if (type.contains("String")&&!filedName.toLowerCase().contains("time")
								&&!filedName.toLowerCase().contains("date")) {
							qlStirng.append(" p." + filedName + " like '%" + value + "%' OR ");
						} else if (type.contains("Integer") || type.contains("Long") 
								|| type.contains("Short") || type.contains("Byte")
								|| type.contains("Double") || type.contains("Float")
								|| type.contains("Boolean")) {
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
	
	/**
	 * 
	 * convertBeanToAndQL:<br />
	 * 将一个实体Bean 转换成 AND 联结的JPAL
	 * 暂时只处理数值类型、字符串和Bool
	 *
	 * @author zhangzhaoyu
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected String convertBeanToAndQL(Object bean) {
		StringBuilder qlString = new StringBuilder();
		try {
			Map map = PropertyUtils.describe(bean);
			Set keyset = map.keySet();
			for (Object key : keyset) {
				if (!key.toString().equals("class")) {
					Object value = map.get(key);
					Class typeClass = PropertyUtils.getPropertyType(bean, key.toString());
					if (typeClass != null) {
						String type = typeClass.toString();
						if (value != null && type.contains("String")&&!key.toString().toLowerCase().contains("time")
								&&!key.toString().toLowerCase().contains("date")) {
							qlString.append(" AND p." + key + " like '%" + value + "%'");
						} else if (value != null && (type.contains("Integer") || type.contains("Long") 
								|| type.contains("Short") || type.contains("Byte")
								|| type.contains("Double") || type.contains("Float")
								|| type.contains("Boolean"))) {
							qlString.append(" AND p." + key + " = " + value);
						}
					}
				}
			}
		} catch (IllegalAccessException | InvocationTargetException
				| NoSuchMethodException e) {
			logger.info("convertBeanToQL exception.");
		}
		return qlString.toString();
	}
	
	/**
	 * 
	 * convertToSQL:<br />
	 * 将排序转化为JPQL
	 *
	 * @author zhangzhaoyu
	 * @param order
	 * @return
	 */
	protected String convertToSQL(IOrder order) {
		StringBuilder builder = new StringBuilder(" ORDER BY ");
		Map<String, Object> map = order.getOrderByMap();
		Set<String> set = map.keySet();
		int size = set.size();
		if (size == 1) {
			for (String key : set) {
				builder.append(" p." + key +" " + map.get(key));
			}
			return builder.toString();
		} else if (size > 1) {
			for (String key : set) {
				builder.append(" p." + key  +" " + map.get(key) +" ,");
			}
			String result = builder.toString();
			return result.substring(0, result.lastIndexOf(","));
		}
		return null;
	}
}
 