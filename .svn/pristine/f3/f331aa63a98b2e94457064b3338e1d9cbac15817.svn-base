package org.jit.sose.web.filter;

import org.jit.sose.constant.SecurityConstant;
import org.jit.sose.web.security.SmsCodeAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 短信认证过滤器
 * 
 * @author wangyue
 * @date 2020-04-28 15:15:24
 */
public class SmsCodeAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	// ~ Static fields/initializers
	// =====================================================================================

	// 手机号码请求参数
	private String mobileParameter = SecurityConstant.DEFAULT_PARAMETER_NAME_PHONE;

	// 短信验证码请求参数
	private String smsCodeParameter = SecurityConstant.DEFAULT_PARAMETER_NAME_SMSCODE;

	private boolean postOnly = true;

	// ~ Constructors
	// ===================================================================================================

	public SmsCodeAuthenticationFilter() {
		// 匹配手机登录请求和请求类型
		super(new AntPathRequestMatcher(SecurityConstant.DEFAULT_SIGN_IN_PROCESSING_URL_PHONE, "POST"));
	}

	// ~ Methods
	// ========================================================================================================

	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		// 获取手机号
		String phone = obtainPhone(request);

		// 短信验证码
		String smsCode = obtainSmsCode(request);

		if (phone == null) {
			phone = "";
		}

		if (smsCode == null) {
			smsCode = "";
		}
		phone = phone.trim();
		smsCode = smsCode.trim();

		// 实例化token,将手机号码放入Token中,未认证状态
		SmsCodeAuthenticationToken authRequest = new SmsCodeAuthenticationToken(phone, smsCode);

		// Allow subclasses to set the "details" property
		// 将request 放入 authRequest中
		setDetails(request, authRequest);

		// 调用AuthenticationManager
		return this.getAuthenticationManager().authenticate(authRequest);
	}

	/**
	 * 获取手机号
	 */
	protected String obtainPhone(HttpServletRequest request) {
		return request.getParameter(mobileParameter);
	}

	/**
	 * 获取短信验证码
	 */
	protected String obtainSmsCode(HttpServletRequest request) {
		return request.getParameter(smsCodeParameter);
	}

	/**
	 * Provided so that subclasses may configure what is put into the authentication
	 * request's details property.
	 *
	 * @param request     that an authentication request is being created for
	 * @param authRequest the authentication request object that should have its
	 *                    details set
	 */
	protected void setDetails(HttpServletRequest request, SmsCodeAuthenticationToken authRequest) {
		authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
	}

	/**
	 * Sets the parameter name which will be used to obtain the username from the
	 * login request.
	 *
	 * @param usernameParameter the parameter name. Defaults to "username".
	 */
	public void setMobileParameter(String usernameParameter) {
		Assert.hasText(usernameParameter, "Username parameter must not be empty or null");
		this.mobileParameter = usernameParameter;
	}

	/**
	 * Defines whether only HTTP POST requests will be allowed by this filter. If
	 * set to true, and an authentication request is received which is not a POST
	 * request, an exception will be raised immediately and authentication will not
	 * be attempted. The <tt>unsuccessfulAuthentication()</tt> method will be called
	 * as if handling a failed authentication.
	 * <p>
	 * Defaults to <tt>true</tt> but may be overridden by subclasses.
	 */
	public void setPostOnly(boolean postOnly) {
		this.postOnly = postOnly;
	}

	public final String getMobileParameter() {
		return mobileParameter;
	}

}
