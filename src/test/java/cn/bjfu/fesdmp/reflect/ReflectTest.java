/** 
 * Project Name:fesdmp 
 * File Name:ReflectTest.java 
 * Package Name:cn.bjfu.fesdmp.reflect 
 * Date:2014年7月9日 下午9:19:59 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.reflect;  

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Ignore;
import org.junit.Test;

import cn.bjfu.fesdmp.domain.enums.BusinessType;
import cn.bjfu.fesdmp.domain.enums.OperationType;
import cn.bjfu.fesdmp.domain.sys.SystemLog;

/** 
 * ClassName:ReflectTest <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年7月9日 下午9:19:59 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class ReflectTest {

	public  String convertBeanToAndQL(Object bean) {
		StringBuilder qlString = new StringBuilder();
		try {
			Map map = PropertyUtils.describe(bean);
			Set keyset = map.keySet();
			for (Object key : keyset) {
				System.out.println("key -> " + key);
				if (!key.toString().equals("class")) {
					Object value = map.get(key);
					Class typeClass = PropertyUtils.getPropertyType(bean, key.toString());
					if (typeClass != null) {
						String type = typeClass.toString();
						if (value != null && type.contains("String")) {
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
			e.printStackTrace();
		}
		return qlString.toString();
	}
	
	@Test
	public void test() {
		Dog dog = new Dog("cacaca", 12);
		
		System.out.println(convertBeanToAndQL(dog));
		System.out.println("----------------------------------------------------------");
		Map map = null;
		try {
			map = PropertyUtils.describe(dog);
			Set keyset = map.keySet();
			for (Object key : keyset) {
				System.out.print("key : " + key + " ||||||| value : " + map.get(key));
				System.out.println("|| type : " + PropertyUtils.getPropertyType(dog, key.toString()));
			}
			System.out.println(map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Ignore
	public void testReflect()  {
		
		SystemLog log = new SystemLog();
		log.setBusinessType(BusinessType.SYS_LOGIN);
		log.setOperateContent("user login");
		log.setOperateTime(new Date());
		log.setOperationType(OperationType.QUERY);
		log.setUserName("Lilei");
		log.setUserSourceIp("202.204.110.22");
		
		System.out.println(log);
		
		Class clazz = log.getClass();
		Field[] fields = clazz.getDeclaredFields();
		
		for (Field field : fields) {
			try {
			System.out.println("name : " + field.getName());
			System.out.println("getGenericType" + field.getGenericType());
			System.out.println("getType" + field.getType());
			int modifier = field.getModifiers();
			if (Modifier.isFinal(modifier) && Modifier.isStatic(modifier)) {
				System.out.println("modifier FINAL STATIC"  );
			}
			if (Modifier.isPrivate(modifier)) {
				System.out.println("modifier PRIVATE"  );
			}
			/*if (modifier == Modifier.STATIC) {
				System.out.println("modifier static"  );
			} else if (modifier == Modifier.PRIVATE) {
				System.out.println("modifier PRIVATE"  );
			} else if (modifier == Modifier.FINAL) {
				System.out.println("modifier FINAL"  );
			}*/
			String filedName = field.getName();
			String firstLetter = filedName.substring(0, 1).toUpperCase();
			String getMethodName = "get" + firstLetter + filedName.substring(1);
			Method getMethod = clazz.getMethod(getMethodName, new Class[]{});
			Object value = getMethod.invoke(log, new Object[]{});
			System.out.println("value : " + value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	@Ignore
	public void testBeanReflects() throws Exception {
		
		SystemLog log = new SystemLog();
		log.setBusinessType(BusinessType.SYS_LOGIN);
		log.setOperateContent("user login");
		log.setOperateTime(new Date());
		log.setOperationType(OperationType.QUERY);
		log.setUserName("Lilei");
		log.setUserSourceIp("202.204.110.22");
		
		Map map = BeanUtils.describe(log);
		Set keyset = map.keySet();
		for (Object key : keyset) {
			System.out.println("key : " + key + " value : " + map.get(key));
			System.out.println("type : " + PropertyUtils.getPropertyType(log, key.toString()));
		}
		System.out.println("------------------------------------------------");
		Dog dog = new Dog("cacaca", 12);
		map = PropertyUtils.describe(dog);
		keyset = map.keySet();
		for (Object key : keyset) {
			System.out.println("key : " + key + " value : " + map.get(key));
			System.out.println("type : " + PropertyUtils.getPropertyType(dog, key.toString()));
		}
		System.out.println("------------------------------------------------");
	}
	
}

