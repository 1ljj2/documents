package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PermissionsVo", description = "请求权限Vo")
public class PermissionsVo {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("请求权限类型标识")
    private Integer permissionsTypeId;

    @ApiModelProperty("请求权限类型")
    private String typeDescription;

    @ApiModelProperty("请求权限模块名称")
    private String moduleName;

    @ApiModelProperty("请求权限模块标识")
    private Integer permissionsModuleId;

    @ApiModelProperty("请求权限模块")
    private String moduleDescription;

    @ApiModelProperty("路径名称 对应@ApiOperation的value")
    private String name;

    @ApiModelProperty("路径描述 对应@ApiOperation的notes")
    private String description;

    @ApiModelProperty("请求路径")
    private String url;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty(value = "是否启用")
    private boolean enable;

}