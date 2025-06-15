package org.jit.sose.web.handler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.jit.sose.enums.ResponseEnum;
import org.jit.sose.util.WebServletUtil;
import org.jit.sose.web.response.CommonResp;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用来解决 [匿名用户] 或 [用户登录信息(token)失效时]<br>
 * 访问无权限资源时的异常<br>
 * 返回状态码：[匿名用户] 401、[用户登录信息(token)失效时]402
 */
@Slf4j
@Component
public class GoAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
//		authException.printStackTrace();
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		CommonResp commonResp = new CommonResp();

		log.trace("无权限访问:" + WebServletUtil.getClientIpAddress(request) + "-Response Status:" + response.getStatus()
				+ "-请求路径:" + request.getRequestURI());
		// 若返回状态码为402,即用户登录信息(token)失效时
		if (response.getStatus() == ResponseEnum.LOGIN_INFORMATION_INVALID.code()) {
			commonResp.setCode(response.getStatus());
			commonResp.setMsg("Login Information Invalid");
		} else {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			// 匿名用户
			commonResp.setCode(HttpStatus.UNAUTHORIZED.value());
			commonResp.setMsg("No Authorization");
		}
		response.getWriter().write(JSON.toJSONString(commonResp));
		response.getWriter().flush();
	}

}
