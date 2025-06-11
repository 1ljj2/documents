package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "UserRoleVo", description = "用户和角色信息Vo")
public class UserRoleVo {

    @ApiModelProperty("主键")
    private Integer id;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("手机号码")
    private String phone;

    @ApiModelProperty(value = "角色主键")
    private Integer roleId;

    @ApiModelProperty(value = "角色key")
    private String roleKey;

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色是否启用")
    private boolean roleEnable;

}
