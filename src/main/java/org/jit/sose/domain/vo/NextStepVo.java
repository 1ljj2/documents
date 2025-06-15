package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wufang
 * @Date 2020-10-19 13:08:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "NextStepVo")
public class NextStepVo {

    @ApiModelProperty(value = "下一个步骤标识")
    private Integer nextStepId;

    @ApiModelProperty(value = "下一个步骤的角色标识")
    private Integer roleId;

    @ApiModelProperty(value = "下一个步骤的角色名称")
    private String roleName;

}
