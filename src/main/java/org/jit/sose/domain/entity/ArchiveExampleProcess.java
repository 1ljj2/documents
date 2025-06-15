package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@ApiModel(value = "ArchiveExampleProcess", description = "档案实例流程")
@TableName(value = "t_archive_example_process")
@EqualsAndHashCode(callSuper = false)
public class ArchiveExampleProcess {

    @ApiModelProperty(value = "主键")
    private Integer id;
    
    @ApiModelProperty(value = "用户档案编号")
    private String userArchiveNumber;

    @ApiModelProperty(value = "用户档案名称")
    private String userArchiveName;
    
    @ApiModelProperty(value = "负责人标识")
    private Integer leaderId;

    @ApiModelProperty(value = "用户标识")
    private Integer userId;

    @ApiModelProperty(value = "档案模板标识")
    private Integer archiveTemplateId;

    @ApiModelProperty(value = "流程标识")
    private Integer processId;

    @ApiModelProperty(value = "审核状态")
    private String auditState;

    @ApiModelProperty(value = "审核意见")
    private String auditOpinion;

    @ApiModelProperty(value = "是否禁用")
    private Boolean isEnable;

    @ApiModelProperty(value = "状态")
    private String state;

    @ApiModelProperty(value = "状态日期")
    private Date stateTime;

}