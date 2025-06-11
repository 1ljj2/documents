package org.jit.sose.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.sql.Timestamp;
import java.util.Set;

/**
 * 用户类
 *
 * @Author: wangyue
 * @Date: 2020-3-11 22:15:03
 */
@Data
@NoArgsConstructor
@ApiModel(value = "User", description = "用户")
@TableName("a_user")
@EqualsAndHashCode(callSuper=true)
public class User extends Model<User> {

	private static final long serialVersionUID = -499841387695365352L;

	public User(Integer id, String userName) {
		super();
		this.id = id;
		this.userName = userName;
	}

	public User(String userName,String password,String phone){
		this.userName=userName;
		this.password=password;
		this.phone=phone;
	}

	@ApiModelProperty("主键")
	private Integer id;

	@ApiModelProperty("用户名")
	private String userName;

	@ApiModelProperty("登录密码")
	private String password;

	@ApiModelProperty("手机号码")
	private String phone;

	@Email
	@ApiModelProperty("邮箱")
	private String mail;

	@ApiModelProperty("创建时间")
	@TableField(select = false)
	private Timestamp createDate;

	@ApiModelProperty("更新时间")
	@TableField(select = false)
	private Timestamp updateDate;

	@ApiModelProperty("状态")
	@TableField(select = false)
	private String state;

	@ApiModelProperty(value = "角色", hidden = true)
	@TableField(exist = false)
	private String role;

	@ApiModelProperty(value = "角色集合", hidden = true)
	@TableField(exist = false)
	private Set<String> roles;

	@ApiModelProperty(value = "年龄", hidden = true)
	@TableField(exist = false)
	private Integer age;

	@ApiModelProperty(value = "邮箱", hidden = true)
	@TableField(exist = false)
	private String email;

	@ApiModelProperty(value = "用户唯一标识openid", hidden = true)
	@TableField(exist = false)
	private String openid;

	@ApiModelProperty(value = "性别", hidden = true)
	@TableField(exist = false)
	private String sex;

	@ApiModelProperty(value = "微信名称", hidden = true)
	@TableField(exist = false)
	private String weixinName;

	/**
	 * 根据用户名获取缓存在redis中的角色key<br>
	 * 存放用户的角色集合
	 *
	 * @param userName 用户名
	 * @return ROLE:userName
	 */
	public static String getRedisRoleKey(String userName) {
		String key = "ROLE:" + userName;
		return key;
	}

}