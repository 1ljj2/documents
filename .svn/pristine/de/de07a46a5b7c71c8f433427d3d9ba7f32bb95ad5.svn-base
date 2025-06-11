package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.jit.sose.constant.RedisConstant;

import java.sql.Timestamp;

@Data
@ApiModel(value = "Role", description = "角色")
@TableName(value = "a_role")
@EqualsAndHashCode(callSuper = false)
public class Role extends Model<Role> {

	private static final long serialVersionUID = 7178333696169620690L;

	@ApiModelProperty(value = "主键")
	private Integer id;

	@ApiModelProperty(value = "角色key")
	private String roleKey;

	@ApiModelProperty(value = "角色名称")
	private String roleName;

	@ApiModelProperty(value = "描述")
	private String description;

	@ApiModelProperty(value = "是否启用")
	@TableField(value = "is_enable")
	private boolean enable;

	@ApiModelProperty(value = "创建时间")
	@TableField(select = false)
	private Timestamp createDate;

	@ApiModelProperty(value = "更新时间")
	@TableField(select = false)
	private Timestamp updateDate;

	@ApiModelProperty(value = "状态")
	@TableField(select = false)
	private String state;

	/**
	 * 根据角色key将对应的权页面路径缓存进redis中
	 * 
	 * @param roleKey 角色key
	 * @return MENU:roleKey
	 */
	public static String getRedisMenuKey(String roleKey) {
		String key = RedisConstant.ROLE_MENU__KEY_PREFIX + ":" + roleKey;
		return key;
	}

}