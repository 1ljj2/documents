package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

@Data
@ApiModel(value = "UserRole", description = "用户角色关联表")
@TableName("a_user_role")
public class UserRole {
	@ApiModelProperty("主键")
	private Integer id;

	@ApiModelProperty("用户标识")
	private Integer userId;

	@ApiModelProperty("角色标识")
	private Integer roleId;

	@ApiModelProperty("创建时间")
	@TableField(select = false)
	private Timestamp createDate;

	@ApiModelProperty("更新时间")
	@TableField(select = false)
	private Timestamp updateDate;

	@ApiModelProperty("状态")
	@TableField(select = false)
	private String state;
}