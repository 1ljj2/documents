package org.jit.sose.web.provider;

import org.jit.sose.enums.SmsCodeEnum;
import org.jit.sose.redis.RedisService;
import org.jit.sose.util.QcloudSmsUtil;
import org.jit.sose.util.ValidatorUtil;
import org.jit.sose.web.exception.SmsCodeAuthenticationException;
import org.jit.sose.web.security.SmsCodeAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 短信登录验证逻辑
 *
 * @author wangyue
 * @date 2020-04-28 15:27:46
 */
@Component
public class SmsCodeAuthenticationProvider implements AuthenticationProvider {

	//	@Qualifier("myUserDetailsService")
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	RedisService redisService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// 转为短信认证的Token
		SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken) authentication;

		// 获取手机号
		String phone = authenticationToken.getPhone();
		boolean isPhone = ValidatorUtil.isMobile(phone);
		if (!isPhone) {
			throw new SmsCodeAuthenticationException("phoneError");
		}

		// 获取短信验证码
		String smsCode = authenticationToken.getSmsCode();

		// redis中验证码的key
		String key = QcloudSmsUtil.getRedisKey(SmsCodeEnum.loginPhone, phone);

		// 先设置一个，测试
//		redisService.set(key, "123456", 10 * 1L);

		// 查看key是否存在
		if (!redisService.hasKey(key)) {
			// key不存在，返回验证码已失效
			throw new SmsCodeAuthenticationException("codeExpired");
		}

		// 从redis中获取短信验证码
		String redisCode = (String) redisService.get(key);
		if (redisCode == null) {
			throw new SmsCodeAuthenticationException("codeExpired");
		}

		// 判断短信验证码是否正确；smsCode不为null，在filter中判断过了
		if (!smsCode.equals(redisCode)) {
			throw new SmsCodeAuthenticationException("smsCodeErr");
		}

		// 获取封装用户信息的对象
		UserDetails userDetails = userDetailsService.loadUserByUsername(phone);

		// 未查询到用户
		if (userDetails == null) {
			throw new SmsCodeAuthenticationException("phoneError");
		}

		SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(userDetails,
				userDetails.getAuthorities());

		authenticationResult.setDetails(authenticationToken.getDetails());

		return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return SmsCodeAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
