package org.jit.sose.web.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局捕获异常
 * 
 * @author wangyue
 * @date 2019年4月4日 下午11:08:20
 */
@Slf4j
@ControllerAdvice(basePackages = { "org.jit.sose.web.security", "org.jit.sose.controller" }) // 作为全局异常处理的切面类，可以设置包的范围
public class GlobalExceptionToErrorPageHandler {

	@ExceptionHandler(HttpException.class)
	public String handleHttpException(HttpException e, HttpServletRequest request) {
		log.info("HTTP异常，错误码：" + e.getMsg());
		e.printStackTrace();
		// 重定向
//        return "redirect:/error";
		// 转发到/error
		return "forward:/" + e.getCode();
	}

}
