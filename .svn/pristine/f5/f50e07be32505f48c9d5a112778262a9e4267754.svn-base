package org.jit.sose.domain.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@ApiModel(value = "UpdateRoleMenuParam", description = "更新角色对应的菜单集合参数")
public class UpdateRoleMenuParam {

	@NotNull
	@ApiModelProperty(value = "角色id", required = true, example = "4")
	private Integer roleId;

	@NotEmpty
	@ApiModelProperty(value = "后台菜单id集合", required = true, example = "[21,22,23]")
	private List<Integer> menuIdList;

}
