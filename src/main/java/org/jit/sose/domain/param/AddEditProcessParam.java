package org.jit.sose.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author wufang
 * @Date 2020-09-22 16:42:45
 */
@ApiModel(value = "AddEditProcessParam", description = "增加流程项信息的封装参数")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddEditProcessParam {

    @ApiModelProperty(value = "流程标识")
    private Integer id;

    @ApiModelProperty(value = "流程名称", required = true)
    private String processName;

    @ApiModelProperty(value = "流程备注")
    private String remark;

    @ApiModelProperty(value = "服务角色标识", required = true)
    private Integer roleId;

    @ApiModelProperty(value = "分类标识", required = true)
    private Integer categoryId;

    @ApiModelProperty(value = "步骤集合", required = true)
    private List<Object> stepList;

    @ApiModelProperty(value = "用于编辑：编辑前最后一个流程步骤的id")
    private Integer fatherStepId;

}
