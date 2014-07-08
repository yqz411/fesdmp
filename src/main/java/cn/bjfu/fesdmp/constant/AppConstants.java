/** 
 * Project Name:RiverEvaluationSys 
 * File Name:AppConstants.java 
 * Package Name:edu.riverevaluationsys.utils 
 * Date:2013年10月10日 下午6:32:35 
 * Copyright (c) 2013, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.constant;
/** 
 * ClassName:AppConstants <br/> 
 * Function: 定义系统需要用到的一些常量. <br/> 
 * Reason:   定义系统需要用到的一些常量. <br/> 
 * Date:     2013年10月10日 下午6:32:35 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.6 
 * @see       
 */
public class AppConstants {

	/**
	 * 登录之后的用户，存放到session 之中
	 */
	public static final String SESSION_USER = "SESSION_USER";
	
	/**
	 * 	Session 存活的最长时间
	 */
	public static final int SESSION_MAXINACTIVEINTERVAL = 30*60;
	/**
	 * DES 加密算法的key
	 */
	public static final String DES_KEY = "Yqoi$MaI";
	/**
	 * DES 加密算法的IV
	 */
	public static final String DES_IV = "Yqoi$MaI";
	/**
	 * 系统默认的用户密码，新添加的用户的密码
	 */
	public static final String ORIGINAL_PWD = "123456";
}

