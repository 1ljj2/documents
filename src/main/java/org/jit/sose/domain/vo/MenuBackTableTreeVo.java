package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@ApiModel(value = "MenuBackTableTreeVo", description = "后台菜单")
public class MenuBackTableTreeVo {

	@ApiModelProperty("主键")
	private Integer id;

	@ApiModelProperty("请求路径")
	private String url;

	@ApiModelProperty("菜单名称")
	private String name;

	@ApiModelProperty("父级菜单标识")
	private Integer parentId;

	@ApiModelProperty(value = "菜单类型", notes = "dir:目录\r\n" + "menu:菜单\r\n" + "button:按钮", example = "menu")
	private String type;

	@ApiModelProperty(value = "级别", notes = "目录为1级\r\n" + "菜单为2级\r\n" + "菜单中的操作为3级", example = "2")
	private Integer level;

	@ApiModelProperty("菜单排序")
	private Integer seq;

	@ApiModelProperty("是否启用")
	private boolean enable;

	@ApiModelProperty("图标")
	private String icon;

	@ApiModelProperty("子菜单集合")
	private List<MenuBackTableTreeVo> children;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("{\"id\":\"");
		builder.append(id);
		builder.append("\",\"url\":\"");
		builder.append(url);
		builder.append("\",\"name\":\"");
		builder.append(name);
		builder.append("\",\"parentId\":\"");
		builder.append(parentId);
		builder.append("\",\"type\":\"");
		builder.append(type);
		builder.append("\",\"level\":\"");
		builder.append(level);
		builder.append("\",\"seq\":\"");
		builder.append(seq);
		builder.append("\",\"enable\":\"");
		builder.append(enable);
		builder.append("\",\"icon\":\"");
		builder.append(icon);
		builder.append("\"}  ");
		return builder.toString();
	}

}