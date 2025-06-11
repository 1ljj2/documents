package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jit.sose.domain.entity.MenuBack;

import java.util.ArrayList;
import java.util.List;

/**
 * 返给客户端的菜单树
 * 
 * @author wangyue
 * @date 2020-05-21 11:49:22
 */
@Data
@ApiModel(value = "MenuBackTreeVo", description = "后台菜单树Vo")
public class MenuBackTreeVo {

	public MenuBackTreeVo() {
		super();
	}

	public MenuBackTreeVo(MenuBack menu) {
		super();
		this.id = menu.getId();
		this.label = menu.getName();
		if (menu.getMenuList() != null) {
			// 初始化，否则add会空指针异常
			this.children = new ArrayList<MenuBackTreeVo>();
			for (MenuBack childMenuBack : menu.getMenuList()) {
				// 创建子菜单时，会递归的往下创建子菜单的子菜单
				MenuBackTreeVo childMenuBackVo = new MenuBackTreeVo(childMenuBack);
				this.children.add(childMenuBackVo);
			}
		}
	}

	@ApiModelProperty(value = "主键")
	private Integer id;

	@ApiModelProperty(value = "菜单名称")
	private String label;

	@ApiModelProperty(value = "子菜单集合")
	private List<MenuBackTreeVo> children;
}
