package org.jit.sose.web.handler;

import lombok.extern.slf4j.Slf4j;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.web.exception.SmsCodeAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AuthenticationFailureHandler 身份验证失败时调用（token无效）
 */
@Slf4j
@Component
public class GoAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		// 若短信异常返回异常中的信息
		if (exception instanceof SmsCodeAuthenticationException) {
			log.debug("手机短信登录失败信息：{}", exception.getMessage());
			response.getWriter().write(ResponseUtil.success(exception.getMessage()));
		} else {
			log.debug("账号密码登录失败信息：{}", exception.getMessage());
			response.getWriter().write(ResponseUtil.success("fail"));
		}
		response.getWriter().flush();
	}

}
