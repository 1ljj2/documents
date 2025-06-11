package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

import static com.baomidou.mybatisplus.annotation.FieldStrategy.NOT_EMPTY;

@Data
@ApiModel(value = "ArchiveAudit", description = "用户档案审核过程")
@TableName(value = "t_archive_audit")
@EqualsAndHashCode(callSuper = false)
public class ArchiveAudit {
    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "父标识")
    private Integer parId;

    @ApiModelProperty(value = "文档实例流程标识")
    @TableField(insertStrategy = NOT_EMPTY, updateStrategy = NOT_EMPTY)
    private Integer exampleId;

    @ApiModelProperty(value = "审核用户表示")
    @TableField(insertStrategy = NOT_EMPTY, updateStrategy = NOT_EMPTY)
    private Integer auditUserId;

    @ApiModelProperty(value = "审核步骤标识")
    @TableField(insertStrategy = NOT_EMPTY, updateStrategy = NOT_EMPTY)
    private Integer stepId;

    @ApiModelProperty(value = "审核状态")
    @TableField(insertStrategy = NOT_EMPTY, updateStrategy = NOT_EMPTY)
    private String auditState;

    @ApiModelProperty(value = "审核意见")
    @TableField(insertStrategy = NOT_EMPTY, updateStrategy = NOT_EMPTY)
    private String auditOpinion;

    @ApiModelProperty(value = "状态")
    @TableField(insertStrategy = NOT_EMPTY, updateStrategy = NOT_EMPTY)
    private String state;

    @ApiModelProperty(value = "状态日期")
    @TableField(insertStrategy = NOT_EMPTY, updateStrategy = NOT_EMPTY)
    private Date stateTime;

}