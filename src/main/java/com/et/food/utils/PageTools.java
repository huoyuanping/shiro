package com.et.food.utils;

import java.util.List;

/**
 * 实体类
 * 分页参数
 * @author Administrator
 *
 */
public class PageTools {
	/**
	 * 构造参数
	 * @param curPage 页面传入的当前页
	 * @param totalCount 数据库查询的总条数
	 * @param pageCount 每页显示的条数
	 */
	public PageTools(Integer curPage,Integer totalCount,Integer pageCount){
		this.curPage=curPage;
		this.pageCount=pageCount==null?this.pageCount:pageCount;
		this.total=totalCount;
		//上一页
		this.prePage=(curPage==1?1:curPage-1);
		//总页数
		this.totalPage=totalCount%this.pageCount==0?totalCount/this.pageCount:totalCount/this.pageCount+1;
		//下一页
		this.nextPage=(curPage==totalPage)?totalPage:(curPage+1);
		this.startIndex=(curPage-1)*this.pageCount+1;
		this.endIndex=curPage*this.pageCount;
		
	}
	public Integer getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}

	public Integer getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(Integer endIndex) {
		this.endIndex = endIndex;
	}
	/**
	 * 开始位置
	 * curPage pageCount(每页显示的条数)    start-end
	 * 	1			10					1-10
	 * 							
	 *  								start=(curPage-1)*pageCount+1 
	 *  								end= curPage*pageCount
	 */
	private Integer startIndex;
	/**
	 * 结束位置
	 */
	private Integer endIndex;
	/**
	 * 当前页（动态由页面传递的）
	 */
	private Integer curPage;
	/**
	 * 每页显示的条数 （默认10条）
	 */
	private Integer pageCount=10;
	/**
	 * 上一页
	 * prePage=(curPage==1?1:curPage-1)
	 * 
	 * 举例
	 * 第二页    上一页 1
	 * 3       上一页 2
	 */
	private Integer prePage;
	/**
	 * 下一页
	 *
	 * nextPage=(curPage==totalpage)?totalpage:(curPage+1)
	 * 举例
	 * 当前页      总页数   下一页
	 * 
	 * 1      3    2
	 * 2      3    3
	 * 3      3    3
	 */
	private Integer nextPage;
	
	/**
	 * 总页数
	 * totalpage=(totalCount%pageCount==0?totalCount/pageCount:totalCount/pageCount+1)
	 * pageCount（每页显示的条数）   totalCount（总条数）  totalpage（分总页数）
	 * 10                       21              3
	 */
	private Integer totalPage;
	/**
	 * 总条数（从数据库查询）
	 */
	private Integer total;
	
	/**
	 * 存储最终取出的条数
	 */
	private List rows;

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPageCount() {
		return pageCount;
		
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPrePage() {
		return prePage;
	}

	public void setPrePage(Integer prePage) {
		this.prePage = prePage;
	}

	public Integer getNextPage() {
		return nextPage;
	}

	public void setNextPage(Integer nextPage) {
		this.nextPage = nextPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalpage(Integer totalpage) {
		this.totalPage = totalpage;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	
}
