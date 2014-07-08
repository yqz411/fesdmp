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
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import cn.bjfu.fesdmp.frame.IOrder;
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

	@Override
	public List<T> findByCondition(Object condition, IOrder order, Pagination<T> page) {
		PropertyDescriptor props[] = null;
		if (condition != null){
			try {
				props = Introspector.getBeanInfo(condition.getClass(),Object.class).getPropertyDescriptors();
			} catch (IntrospectionException e) {
				e.printStackTrace();
			}
			if(props != null){
				String jpal = " SELECT p FROM "+ entityClass.getSimpleName() +" p WHERE 1 = 1 ";
				for (int i = 0; i < props.length; i++){
					String name = props[i].getName();
					String value;
					try {
						if(props[i].getReadMethod().invoke(condition) != null){
							value =  (props[i].getReadMethod().invoke(condition)).toString();
							if (value != null && !"".equals(value)){
								jpal +=  " AND " + name + " = '" + value + "'";
							}
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e){
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
				jpal += order.convertToSQL();
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
		}
		return null;
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
	
}
 