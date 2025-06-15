package org.jit.sose.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@ApiModel(value = "UpdateUserRoleParam", description = "更新用户对象的角色集合参数")
public class UpdateUserRoleParam {

    @NotNull
    @ApiModelProperty(value = "用户id", required = true, example = "3")
    private Integer userId;

    @NotEmpty
    @ApiModelProperty(value = "角色id集合", required = true, example = "[3, 2, 4]")
    private List<Integer> roleIdList;

}
