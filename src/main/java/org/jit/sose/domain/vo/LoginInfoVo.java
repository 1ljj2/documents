package org.jit.sose.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 登录返回信息
 * 
 * @author wangyue
 * @date 2020-04-12 01:38:03
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "LoginInfoVo", description = "登录返回信息")
public class LoginInfoVo {

	@ApiModelProperty(value = "是否成功信息", example = "success", notes = "成功:success;失败:fail")
	private String message;

	@ApiModelProperty(value = "token")
	private String token;

	@ApiModelProperty(value = "用户名")
	private String userName;

	@ApiModelProperty("手机号码")
	private String phone;

	@ApiModelProperty("邮箱")
	private String mail;

	@ApiModelProperty(value = "昵称")
	private String nickname;

	@ApiModelProperty(value = "临时公钥")
	private String rsaPublicKey;

	@ApiModelProperty(value = "用户id")
	private Integer userId;
}
