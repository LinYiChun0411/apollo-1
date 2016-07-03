package org.jay.frame.jdbc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jay.frame.FrameProperites;
import org.jay.frame.util.JsonUtil;


/**
 * 系统分页对象
 * @desc Page
 * @date 2011-2-16 上午09:53:18
 *
 */
public class Page<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -27308701186773909L;

	/**
	 * 当前页第一条数据的位置,从0开始
	 */
	private int start;

	/**
	 * 每页的记录数
	 */
	private int pageSize = FrameProperites.PAGING_DEFAULT_PAGE_SIZE;

	/**
	 * 当前页中存放的记录
	 */
	private Collection<T> results;

	/**
	 * 总记录数
	 */
	private long totalCount;

	/**
	 * 总页数
	 */
	private long totalPageCount;

	private int currentPageNo;

	/**
	 * 构造方法，只构造空页
	 */
	public Page() {
		this(0, 0, FrameProperites.PAGING_DEFAULT_PAGE_SIZE, new ArrayList());
	}

	/**
	 * 默认构造方法
	 * 
	 * @param start
	 *            本页数据在数据库中的起始位置
	 * @param totalSize
	 *            数据库中总记录条数
	 * @param pageSize
	 *            本页容量
	 * @param data
	 *            本页包含的数据
	 */
	public Page(int start, long totalSize, int pageSize, Collection<T> results) {
		this.pageSize = pageSize;
		this.start = start;
		this.totalCount = totalSize;
		this.results = results;
		if (totalCount % pageSize == 0) {
			this.totalPageCount = totalCount / pageSize;
		} else {
			this.totalPageCount = totalCount / pageSize + 1;
		}
		this.currentPageNo = (start / pageSize) + 1;
	}

	/**
	 * 取数据库中包含的总记录数
	 */
	public long getTotalCount() {
		return this.totalCount;
	}

	/**
	 * 取总页数
	 */
	public long getTotalPageCount() {
		return this.totalPageCount;
	}

	/**
	 * 取每页数据容量
	 */
	public int getPageSize() {
		return pageSize;
	}

	public Collection<T> getResults() {
		return results;
	}

	public List<T> getList() {
		return results == null ? null
				: results instanceof List ? (List) results : new ArrayList(
						results);
	}

	public Set<T> getSet() {
		return results == null ? null : results instanceof Set ? (Set) results
				: new HashSet(results);
	}

	public void setResults(Collection<T> results) {
		this.results = results;
	}

	/**
	 * 取当前页码,页码从1开始
	 */
	public int getCurrentPageNo() {
		return this.currentPageNo;
	}

	/**
	 * 是否有下一页
	 */
	public boolean isHasNext() {
		return (this.getCurrentPageNo() < this.getTotalPageCount() - 1);
	}
	
	/**
	 * 取得下页的页号, 序号从1开始.
	 * 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext())
			return currentPageNo + 1;
		else
			return currentPageNo;
	}


	/**
	 * 是否有上一页
	 */
	public boolean isHasPre() {
		return (this.getCurrentPageNo() > 1);
	}
	
	/**
	 * 取得上页的页号, 序号从1开始.
	 * 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre())
			return currentPageNo - 1;
		else
			return currentPageNo;
	}

	/**
	 * 获取任一页第一条数据的位置，每页条数使用默认值
	 */
	protected static int getStartOfPage(int pageNo) {
		return getStartOfPage(pageNo, FrameProperites.PAGING_DEFAULT_PAGE_SIZE);
	}

	/**
	 * 获取任一页第一条数据的位置,startIndex从0开始
	 */
	public static int getStartOfPage(int pageNo, int pageSize) {
		if (pageNo == -1 && pageSize == -1) {
			return -1;// 表示从第一行开始，不设置
		}
		return (pageNo - 1) * pageSize;
	}


	public int getStart() {
		return start;
	}

	public void setTotalCount(long totalCount) {
    	this.totalCount = totalCount;
    }
	
	public String toJsonStr(){
		Map info = new HashMap();
		info.put(FrameProperites.PAGING_JSON_ROWS, this.getList());
		//info.put("success", Boolean.TRUE);
		info.put(FrameProperites.PAGING_JSON_TOTAL, this.totalCount);
		return JsonUtil.toJson(info);
	}
}
