package org.jit.sose.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jit.sose.domain.entity.MenuBack;

import java.util.ArrayList;
import java.util.List;

/**
 * 返给客户端的菜单
 * 
 * @author: 王越
 * @date: 2019-09-12 10:42:06
 */
@Data
@ApiModel(value = "MenuBackVo", description = "后台菜单Vo")
public class MenuBackVo {

	public MenuBackVo() {
		super();
	}

	public MenuBackVo(MenuBack menu) {
		super();
		this.id = menu.getId();
		this.url = menu.getUrl();
		this.name = menu.getName();
		this.icon = menu.getIcon();
		if (menu.getMenuList() != null) {
			// 初始化，否则add会空指针异常
			this.menuList = new ArrayList<MenuBackVo>();
			for (MenuBack childMenuBack : menu.getMenuList()) {
				// 创建子菜单时，会递归的往下创建子菜单的子菜单
				MenuBackVo childMenuBackVo = new MenuBackVo(childMenuBack);
				this.menuList.add(childMenuBackVo);
			}
		}
	}

	@ApiModelProperty(value = "主键")
	private Integer id;

	@ApiModelProperty(value = "请求路径")
	private String url;

	@ApiModelProperty(value = "菜单名称")
	private String name;

	@ApiModelProperty("图标")
	private String icon;

	@ApiModelProperty(value = "子菜单集合")
	@TableField(exist = false)
	private List<MenuBackVo> menuList;
}
