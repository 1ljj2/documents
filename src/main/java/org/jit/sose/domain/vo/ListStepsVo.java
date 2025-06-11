package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wufang
 * @Date 2020-09-22 17:20:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ListStepsVo", description = "流程步骤条返回信息")
public class ListStepsVo {

    private Integer id;

    private String stepName;

    private Integer pStepId;

    private Integer roleId;

    private String roleName;

    private Integer isSign;

    private String isSignName;

    private String remark;


}
