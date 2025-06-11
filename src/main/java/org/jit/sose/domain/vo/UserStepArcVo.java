package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wufang
 * @Date 2020-10-19 17:45:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserStepArcVo", description = "用户步骤返回信息")
public class UserStepArcVo {

    @ApiModelProperty("archiveAudit表中的Id")
    private Integer archiveAuditId;

    @ApiModelProperty("fileAudit表中的preId")
    private Integer preId;

    @ApiModelProperty("流程步骤标识")
    private Integer stepId;

    @ApiModelProperty("步骤名称")
    private String stepName;

    @ApiModelProperty("用户名称（审核的用户）")
    private String userName;

    @ApiModelProperty("审核状态")
    private String auditState;

    @ApiModelProperty("审核意见")
    private String opinion;

    @ApiModelProperty("审核时间")
    private String stateTimeString;

}
