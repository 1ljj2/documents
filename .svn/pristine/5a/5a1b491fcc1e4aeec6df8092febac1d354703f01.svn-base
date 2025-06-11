package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 所有目录菜单和当前角色拥有的菜单树
 * 
 * @author wangyue
 * @date 2020-05-21 11:49:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "MenuBackAllAndOwnTreeVo", description = "所有目录菜单和当前用户拥有的菜单树")
public class MenuBackAllAndOwnTreeVo {

	@ApiModelProperty(value = "所有目录菜单")
	private List<MenuBackTreeVo> dirVoList;

	@ApiModelProperty(value = "角色拥有的菜单树")
	private List<MenuBackTreeVo> ownDirVoList;
}
