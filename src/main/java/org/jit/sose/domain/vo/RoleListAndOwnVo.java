package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jit.sose.domain.entity.Role;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "RoleListAndOwnVo", description = "用户对应的角色集合和所有角色集合Vo")
public class RoleListAndOwnVo {

    @ApiModelProperty(value = "用户对应的角色集合")
    private List<Role> ownRoleList;

    @ApiModelProperty(value = "角色集合")
    private List<Role> roleList;

}