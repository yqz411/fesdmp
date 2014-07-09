/** 
 * Project Name:fesdmp 
 * File Name:Dog.java 
 * Package Name:cn.bjfu.fesdmp.reflect 
 * Date:2014年7月9日 下午10:55:28 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.reflect;  

import java.io.Serializable;

/** 
 * ClassName:Dog <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2014年7月9日 下午10:55:28 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class Dog implements Serializable {
	
	private String name;
	private Integer age;
	
	public Dog() {}

	public Dog(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Dog [name=" + name + ", age=" + age + "]";
	}
}
 