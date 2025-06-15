package org.jit.sose.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * 自定义异常基础类
 * 
 * @author wangyue
 * @date 2019-06-06 00:41:00
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class BaseException extends RuntimeException {

	private static final long serialVersionUID = -4943083129831842554L;

	/**
	 * 自定义异常码
	 */
	@NonNull
	protected Integer code;

	/**
	 * 自定义异常信息
	 */
	protected String msg;

	public BaseException(String msg) {
		super();
		this.msg = msg;
	}

}
