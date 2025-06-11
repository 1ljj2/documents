package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@ApiModel(value = "PermissionsType", description = "请求权限类型,存放security权限控制的方式")
@TableName("a_permissions_type")
@EqualsAndHashCode(callSuper=true)
public class PermissionsType extends Model<PermissionsType> {

	private static final long serialVersionUID = -2645105311907035003L;

	@ApiModelProperty("主键")
	private Integer id;

	@NonNull
	@ApiModelProperty("类型名")
	private String typeName;

	@ApiModelProperty("类型描述")
	private String description;

	@ApiModelProperty("序号")
	private Integer seq;

	@ApiModelProperty("备注")
	private String remark;

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