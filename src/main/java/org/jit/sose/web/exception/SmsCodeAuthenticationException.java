package org.jit.sose.web.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 手机短信认证异常
 * 
 * @author wangyue
 * @date 2020-04-28 22:10:12
 */
public class SmsCodeAuthenticationException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SmsCodeAuthenticationException(String msg) {
		super(msg);
	}

	public SmsCodeAuthenticationException(String msg, Throwable t) {
		super(msg, t);
	}
}
