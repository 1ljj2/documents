package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 自定义错误页面跳转
 * 
 * @author wangyue
 * @date 2020-04-08 11:46:18
 */
@Controller
public class ErrorView {

	/**
	 * 错误页面跳转
	 */
	@GetMapping("/error")
	public String errorView() {
		return "error/error";
	}

	/**
	 * 401页面跳转
	 */
	@GetMapping("/401")
	public String error401View() {
		return "error/401";
	}

	/**
	 * 403页面跳转
	 */
	@GetMapping("/403")
	public String error403View() {
		return "error/403";
	}

	/**
	 * 404页面跳转
	 */
	@GetMapping("/404")
	public String error404View() {
		return "error/404";
	}

	/**
	 * 500页面跳转
	 */
	@GetMapping("/500")
	public String error500View() {
		return "error/500";
	}


	/**
	 *
	 * 没有授权环境跳转
	 */
	@GetMapping("/501")
	public String error501View() {
		return "error/501";
	}
}
