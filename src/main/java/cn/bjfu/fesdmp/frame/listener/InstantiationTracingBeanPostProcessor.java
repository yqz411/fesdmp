/** 
 * Project Name:fesdmp 
 * File Name:InstantiationTracingBeanPostProcessor.java 
 * Package Name:cn.bjfu.fesdmp.listener 
 * Date:2014年7月10日 下午2:30:22 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.frame.listener;  

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/** 
 * ClassName:InstantiationTracingBeanPostProcessor <br/> 
 * Function: 在Spring容器将所有的Bean都初始化完成之后，做一些操作. <br/> 
 * Reason:   event.getApplicationContext().getParent()==null 表示为root applicationcontext. 
 * 			event.getApplicationContext().getParent()!=null 标识为 WebApplicationContext
 * <br/> 
 * Date:     2014年7月10日 下午2:30:22 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class InstantiationTracingBeanPostProcessor implements
		ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			String appName = event.getApplicationContext().getApplicationName();
			String id = event.getApplicationContext().getId();
			System.out.println("The spring context is ready." + appName + " id " + id);
		}
		
	}

}
 