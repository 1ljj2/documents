package org.jit.sose.web.handler;

import org.jit.sose.enums.ResponseEnum;
import org.jit.sose.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用来解决认证过的用户访问无权限资源时的异常<br>
 * 状态码:403
 */
@Component
public class GoAccessDeniedHandler implements AccessDeniedHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
//		accessDeniedException.printStackTrace();
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		response.setStatus(HttpStatus.FORBIDDEN.value());
//		CommonResp commonResp = new CommonResp();
//		commonResp.setCode(HttpStatus.FORBIDDEN.value());
//		commonResp.setMsg("No Permission");
		String result = ResponseUtil.enumResp(ResponseEnum.NO_PERMISSION);
		response.getWriter().write(result);
		response.getWriter().flush();
	}

}
