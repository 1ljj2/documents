package org.jit.sose.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wufang
 * @Date 2020-10-07 15:14:01
 */
@ApiModel(value = "ListArchiveTemParam", description = "分页查询参数封装档案模板")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListArchiveTemParam {

    @ApiModelProperty(value = "当前页索引", required = true)
    private Integer pageNum;

    @ApiModelProperty(value = "当前页页面大小", required = true)
    private Integer pageSize;

    @ApiModelProperty(value = "所属分类标识")
    private Integer categoryId;

    @ApiModelProperty(value = "学期标识")
    private Integer termId;

    @ApiModelProperty(value = "用户标识")
    private Integer userId;

    @ApiModelProperty(value = "课程标识")
    private Integer courseId;

    @ApiModelProperty(value = "档案模板名称搜索值")
    private String archiveTemName;

    @ApiModelProperty(value = "是否按照学期排序")
    private Boolean soltByTerm;

    @ApiModelProperty(value = "是否按照课程排序")
    private Boolean soltByCourse;

    @ApiModelProperty(value = "是否查询我审核的档案")
    private Boolean isAudit;

}
