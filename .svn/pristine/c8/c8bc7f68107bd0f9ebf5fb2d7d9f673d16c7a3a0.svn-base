package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@ApiModel(value = "Permissions", description = "请求权限")
@TableName("a_permissions")
@EqualsAndHashCode(callSuper=true)
public class Permissions extends Model<Permissions> {

    private static final long serialVersionUID = -735149093381647378L;

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("请求权限类型标识")
    private Integer permissionsTypeId;

    @ApiModelProperty("请求权限模块标识")
    private Integer permissionsModuleId;

    @ApiModelProperty("请求权限类型名称")
    private String typeName;

    @ApiModelProperty("请求权限模块名称")
    private String moduleName;

    @ApiModelProperty("路径名称 对应@ApiOperation的value")
    private String name;

    @ApiModelProperty("路径描述 对应@ApiOperation的notes")
    private String description;

    @ApiModelProperty("请求路径")
    private String url;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty(value = "是否启用")
    @TableField(value = "is_enable")
    private boolean enable;

    @ApiModelProperty("创建时间")
    @TableField(select = false)
    private Timestamp createDate;

    @ApiModelProperty("更新时间")
    @TableField(select = false)
    private Timestamp updateDate;

    @ApiModelProperty("状态")
    @TableField(select = false)
    private String state;

}