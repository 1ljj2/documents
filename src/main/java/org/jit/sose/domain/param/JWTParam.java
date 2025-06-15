package org.jit.sose.domain.param;

import lombok.Data;
import org.jit.sose.domain.entity.SecurityUser;

import java.util.Set;

/**
 * 封装jwt参数
 * 
 * @author wangyue
 * @date 2019-09-12 10:48:09
 */
@Data
public class JWTParam {

	public JWTParam() {
		super();
	}

	/**
	 * 参数封装构造方法
	 * 
	 * @param SecurityUser SecurityUser
	 */
	public JWTParam(SecurityUser securityUser) {
		super();
		this.userId = securityUser.getId();
		this.userName = securityUser.getUserName();
		this.roles = securityUser.getRoles();
	}

	/**
	 * 参数封装构造方法
	 * 
	 * @param SecurityUser SecurityUser
	 * @param expiration   token有效时间，单位毫秒
	 */
	public JWTParam(SecurityUser securityUser, Long expiration) {
		super();
		this.userId = securityUser.getId();
		this.userName = securityUser.getUserName();
		this.roles = securityUser.getRoles();
		this.expiration = expiration;
	}

	/**
	 * 用户id
	 */
	Integer userId;

	/**
	 * 用户名
	 */
	String userName;

	/**
	 * 角色
	 */
	String role;

	/**
	 * 角色集合
	 */
	Set<String> roles;

	/**
	 * 有效时间
	 */
	Long expiration;

}
