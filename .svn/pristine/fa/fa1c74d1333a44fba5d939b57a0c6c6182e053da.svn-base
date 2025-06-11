package org.jit.sose.domain.vo;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;

/**
 * 使用PageInfo分页查询时的返回参数封装
 * 
 * @author: 王越
 * @date: 2019-12-24 20:05:54
 */
@Data
public class PageInfoVo<T> {

	public PageInfoVo(PageInfo<T> pageInfo) {
		super();
		this.pageNum = pageInfo.getPageNum();
		this.total = pageInfo.getTotal();
		this.list = pageInfo.getList();
	}

	/**
	 * 当前页
	 */
	private int pageNum;

	/**
	 * 总记录数
	 */
	private long total;

	/**
	 * 结果集
	 */
	private List<T> list;

}
