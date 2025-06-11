package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wufang
 * @Date 2020-10-15 16:57:52
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ListProcessForReviewVo", description = "【根据名称、分类、分页】查询流程项返回信息")
public class ListProcessForReviewVo {
    @ApiModelProperty("主键标识")
    private Integer id;

    @ApiModelProperty("流程项名称")
    private String processName;

    @ApiModelProperty("流程步骤第一个步骤id")
    private Integer stepId;

    @ApiModelProperty("流程步骤第一个步骤所需审核的角色名称")
    private String roleName;

    @ApiModelProperty("分类")
    private Integer categoryId;
    private String categoryName;

    @ApiModelProperty("更新时间")
    private String  stateTimeString;

    private Boolean isSelected;
}
