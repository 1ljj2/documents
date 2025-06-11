package org.jit.sose.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wufang
 * @Date 2020-09-22 13:35:47
 */
@ApiModel(value = "ListFileTemParam", description = "分页查询参数封装文档模板")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListFileTemParam {

    @ApiModelProperty(value = "当前页索引", required = true)
    private Integer pageNum;

    @ApiModelProperty(value = "当前页页面大小", required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "所属分类标识")
    private Integer categoryId;

    @ApiModelProperty(value = "学期标识")
    private Integer termId;

    @ApiModelProperty(value = "文档模板名称搜索值")
    private String fileTemName;

}
