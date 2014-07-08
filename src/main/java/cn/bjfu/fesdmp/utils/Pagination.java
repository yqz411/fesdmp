package cn.bjfu.fesdmp.utils;  

import java.util.List;

/** 
 * ClassName:Pagination <br/> 
 * Function: 分页类的通用实现类. <br/> 
 * Reason:   分页类的通用实现类. <br/> 
 * Date:     2013年10月18日 上午9:36:07 <br/> 
 * @author   zhangzhaoyu 
 * @version   
 * @since    JDK 1.7
 * @see       
 */
public class Pagination<T> {
	/**
	 * 第一条记录的位置
	 */
	private int offset;
	
	/**
	 * 每页显示的条数
	 */
	private int pageSize;
	/**
	 * 总页面数
	 */
	private int totalPage;
	/**
	 * 当前页面
	 */
	private int currentPage;
	/**
	 * 总条数
	 */
	private int totalRecord;
	
	/**
	 * 该分页的数据集
	 */
	private List<T> datas;
	
	/**
	 *
	 * 默认的构造函数，设置默认的页面大小为 10
	 * 默认的当前页为第 1 页
	 *
	 */
	public Pagination(){
		this.pageSize = 10;
		this.currentPage = 1;
	}
		
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		setCurrentPage(this.getCurrentPage());
	}
	
	/**
	 * 
	 * countTotalPage:<br />
	 * 计算并设置总的页数
	 *
	 * @author zhangzhaoyu
	 */
	public void countTotalPage() {
		if (getPageSize() != 0) {
			this.totalPage = getTotalRecord() % getPageSize() == 0 ? getTotalRecord()
					/ getPageSize()
					: getTotalRecord() / getPageSize() + 1;
		}
	}

	/**
	 * 
	 * getOffset:<br />
	 * 获取下一次分页的偏移量
	 *
	 * @author zhangzhaoyu
	 * @return
	 */
	public int getOffset() {
		if(pageSize != 0){
			this.offset = (this.currentPage -1)* pageSize;
		}
		return offset;
	}

	/**
	 * 
	 * getCurrentPage:<br />
	 * 获取当前的页数
	 *
	 * @author zhangzhaoyu
	 * @return
	 * 返回当前的 currentPage
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * 
	 * setCurrentPage:<br />
	 * 设置当前的页数
	 *
	 * @author zhangzhaoyu
	 * @param currentPage
	 * 当前的页数
	 */
	public void setCurrentPage(int currentPage) {
		if (currentPage < 0){
			this.currentPage = 1;
		}else if (getTotalPage() != 0 && currentPage > getTotalPage()){
			this.currentPage = getTotalPage();
		}else{
			this.currentPage = currentPage;
		}
	}

	/**
	 * 
	 * getTotalRecord:<br />
	 * 获取总的记录数
	 *
	 * @author zhangzhaoyu
	 * @return
	 * 返回总的记录数
	 */
	public int getTotalRecord() {
		return totalRecord;
	}

	/**
	 * 
	 * setTotalRecord:<br />
	 * 设置总的记录数 该函数还调用了 countTotalPage()
	 *
	 * @author zhangzhaoyu
	 * @param totalRecord
	 */
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		//计算总页数
		this.countTotalPage();
	}

	public List<T> getDatas() {
		return datas;
	}

	public void setDatas(List<T> datas) {
		this.datas = datas;
	}
}
