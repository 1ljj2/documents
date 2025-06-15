package org.jit.sose.web.config;

import org.jit.sose.web.filter.SmsCodeAuthenticationFilter;
import org.jit.sose.web.handler.GoAuthenticationFailureHandler;
import org.jit.sose.web.handler.GoAuthenticationSuccessHandler;
import org.jit.sose.web.provider.SmsCodeAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 短信认证配置
 * 
 * @author wangyue
 * @date 2020-04-28 15:33:41
 */
@Component
public class SmsCodeAuthenticationSecurityConfig
		extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	@Autowired
    GoAuthenticationSuccessHandler authenticationSuccessHandler; // 登录成功返回的 JSON 格式数据给前端（否则为 html）

	@Autowired
    GoAuthenticationFailureHandler authenticationFailureHandler; // 登录失败返回的 JSON 格式数据给前端（否则为 html）

	@Autowired
    SmsCodeAuthenticationProvider smsCodeAuthenticationProvider; // 短信认证

	@Override
	public void configure(HttpSecurity http) throws Exception {
		SmsCodeAuthenticationFilter smsCodeAuthenticationFilter = new SmsCodeAuthenticationFilter();
		smsCodeAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
		smsCodeAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
		smsCodeAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

		// 把smsCodeAuthenticationProvider放入authenticationProvider中
		http.authenticationProvider(smsCodeAuthenticationProvider).addFilterAfter(smsCodeAuthenticationFilter,
				UsernamePasswordAuthenticationFilter.class);
	}

}
