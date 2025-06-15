package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@ApiModel(value = "Configuration", description = "系统配置")
@TableName(value = "a_configuration")
@EqualsAndHashCode(callSuper = false)
public class Configuration {
    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("配置项名称")
    private String name;

    @ApiModelProperty("配置项值")
    private String value;

    @ApiModelProperty("是否启用")
    private Boolean isEnable;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("状态")
    private String state;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("状态日期")
    private Date stateTime;

}