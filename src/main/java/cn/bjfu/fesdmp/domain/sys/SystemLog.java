/** 
 * Project Name:fesdmp 
 * File Name:SystemLog.java 
 * Package Name:cn.bjfu.fesdmp.domain.sys 
 * Date:2014年7月8日 下午9:38:43 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.domain.sys;  

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import cn.bjfu.fesdmp.domain.enums.BusinessType;
import cn.bjfu.fesdmp.domain.enums.OperationType;

/** 
 * ClassName:SystemLog <br/> 
 * Function: 系统的日志记录领域对象. <br/> 
 * Reason:   系统的日志记录领域对象. <br/> 
 * Date:     2014年7月8日 下午9:38:43 <br/> 
 * @author   zhangzhaoyu 
 * @version   1.0
 * @since    JDK 1.7 
 * @see       
 */
@Entity
@Table(name = "t_system_log")
public class SystemLog implements Serializable {

	/**
	 * @since JDK 1.7
	 */
	private static final long serialVersionUID = -3583921713865091735L;
	
	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userSourceIp;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date operateTime;
	@Column(nullable = false)
	private String operateContent;
	@Column(nullable = false)
	private String userName;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private OperationType operationType;
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private BusinessType businessType;

	public SystemLog() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserSourceIp() {
		return userSourceIp;
	}

	public void setUserSourceIp(String userSourceIp) {
		this.userSourceIp = userSourceIp;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	public String getOperateContent() {
		return operateContent;
	}

	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(BusinessType businessType) {
		this.businessType = businessType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessType == null) ? 0 : businessType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((operateContent == null) ? 0 : operateContent.hashCode());
		result = prime * result
				+ ((operateTime == null) ? 0 : operateTime.hashCode());
		result = prime * result
				+ ((operationType == null) ? 0 : operationType.hashCode());
		result = prime * result
				+ ((userName == null) ? 0 : userName.hashCode());
		result = prime * result
				+ ((userSourceIp == null) ? 0 : userSourceIp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemLog other = (SystemLog) obj;
		if (businessType != other.businessType)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (operateContent == null) {
			if (other.operateContent != null)
				return false;
		} else if (!operateContent.equals(other.operateContent))
			return false;
		if (operateTime == null) {
			if (other.operateTime != null)
				return false;
		} else if (!operateTime.equals(other.operateTime))
			return false;
		if (operationType != other.operationType)
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (userSourceIp == null) {
			if (other.userSourceIp != null)
				return false;
		} else if (!userSourceIp.equals(other.userSourceIp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
	}
}
 