package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wufang
 * @Date 2020-10-19 15:36:35
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ListAuditArchiveVo")
public class ListAuditArchiveVo {

    @ApiModelProperty("档案审核名称")
    private String archiveName;

    @ApiModelProperty("档案审核实例步骤标识t_archive_audit")
    private Integer archiveAuditId;

    @ApiModelProperty("实例标识t_archive_example_process")
    private Integer exampleId;

    @ApiModelProperty("关联流程标识")
    private Integer processId;

    @ApiModelProperty("当前步骤标识")
    private Integer nowStepId;

    @ApiModelProperty("申请审核时间")
    private String stateTimeString;

    @ApiModelProperty("申请的用户标识")
    private Integer applyUserId;

    @ApiModelProperty("申请用户的名称")
    private String applyUserName;

    @ApiModelProperty("当前步骤名称")
    private String nowStepName;



}
