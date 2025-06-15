package org.jit.sose.enums;

public enum PermissionTypeEnum {

	/**
	 * 配置无需权限校验的路径
	 */
	Ignoring,

	/**
	 * 匿名可访问
	 */
	Anonymous,

	/**
	 * 用户完全认证可访问
	 */
	Authenticated,

	/**
	 * 所有用户可访问
	 */
	PermitAll,

	/**
	 * 基于角色的访问控制
	 */
	RbacAuthority;

}
