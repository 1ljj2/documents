package org.jit.sose.enums;

/**
 * 统一自定义HTTP状态码枚举类型<br>
 * 没有的参考org.springframework.http.HttpStatus
 * 
 * @author wangyue
 * @date 2020-04-12 23:48:57
 */
public enum ResponseEnum {

	/**
	 * (200)请求成功。一般用于GET与POST请求
	 */
	SUCCESS(200, "Success"),

	/**
	 * (401)当前用户未认证
	 */
	No_AUTHORIZATION(401, "No Authorization"),

	/**
	 * (402)用户登录信息(token)失效
	 */
	LOGIN_INFORMATION_INVALID(402, "Login Information(token) Invalid"),

	/**
	 * (403)当前用户无权限访问资源
	 */
	NO_PERMISSION(403, "No Permission"),

	/**
	 * (420)传入的数据格式错误
	 */
	DATA_FORMAT_ERROR(420, "Data Format Error"),

	/**
	 * (421)redis缓存错误
	 */
	REDIS_ERROR(421, "Redis Error"),

	/**
	 * (500)服务器内部错误，无法完成请求
	 */
	ERROR(500, "Internal Server Error"),

	/**
	 * (567)支付宝模块错误
	 */
	ALIPAY_ERROR(567, "Alipay Error");

	/**
	 * 状态码
	 */
	private final Integer code;

	/**
	 * 状态码信息
	 */
	private final String msg;

	ResponseEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	/**
	 * 获取状态码
	 */
	public Integer code() {
		return this.code;
	}

	/**
	 * 获取状态码信息
	 */
	public String msg() {
		return this.msg;
	}
}
