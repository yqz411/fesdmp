/** 
 * Project Name:fesdmp 
 * File Name:PageInfoBean.java 
 * Package Name:cn.bjfu.fesdmp.utils 
 * Date:2014年7月9日 下午4:34:58 
 * Copyright (c) 2014, zhangzhaoyu0524@163.com All Rights Reserved. 
 * 
*/  
  
package cn.bjfu.fesdmp.utils;  
/** 
 * ClassName:PageInfoBean <br/> 
 * Function: PageInfoBean 接受前台传来的分页信息. <br/> 
 * Reason:   PageInfoBean. <br/> 
 * Date:     2014年7月9日 下午4:34:58 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7 
 * @see       
 */
public class PageInfoBean {

	private Integer limit;
	private Integer page;
	private Integer start;
	
	/**
	 * 查询的Json 字符串
	 */
	private String searchJson;
	public PageInfoBean() {}

	public PageInfoBean(Integer limit, Integer page, Integer start,
			String searchJson) {
		super();
		this.limit = limit;
		this.page = page;
		this.start = start;
		this.searchJson = searchJson;
	}

	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public String getSearchJson() {
		return searchJson;
	}

	public void setSearchJson(String searchJson) {
		this.searchJson = searchJson;
	}

	@Override
	public String toString() {
		return "PageInfoBean [limit=" + limit + ", page=" + page + ", start="
				+ start + ", searchJson=" + searchJson + "]";
	}
}
 