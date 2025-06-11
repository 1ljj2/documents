package org.jit.sose.domain.param;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "PageInfoParam", description = "分页参数封装")
public class PageInfoParam {

	// 当前页索引
	public Integer pageNum;

	// 当前页页面大小
	public Integer pageSize;

}
