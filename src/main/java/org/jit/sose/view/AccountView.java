package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 账户模块视图跳转：用户登录、注册、忘记密码、重置密码<br>
 *
 * @author wangyue
 * @date 2019年4月18日 下午11:15:01
 */
@Controller
public class AccountView {

	/**
	 * 跳转平台首页，目前为登录页面
	 *
	 * @return 登录页面路径
	 */
	@GetMapping({ "/","/login" })
	public String loginView() {
		return "account/login";
	}

	/**
	 * 跳转注册页面
	 */
	@GetMapping(value = "/register")
	public String registerView() {
		return "account/register";
	}

	/**
	 * 跳转忘记密码页面
	 */
	@GetMapping(value = "/reset_password")
	public String forgetPwdView() {
		return "account/forgetPwd";
	}

}
