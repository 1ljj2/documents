package org.jit.sose.web.handler;

import lombok.SneakyThrows;
import org.jit.sose.domain.entity.SecurityUser;
import org.jit.sose.domain.param.JWTParam;
import org.jit.sose.domain.vo.LoginInfoVo;
import org.jit.sose.redis.RedisService;
import org.jit.sose.util.EncryptionUtil;
import org.jit.sose.util.JWTUtil;
import org.jit.sose.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * AuthenticationSuccessHandler 登录验证成功
 * 
 * @author wangyue
 * @date 2020-03-13 23:53:21
 */
@Component
public class GoAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	RedisService redisService;

	/**
	 * Authentication 是封装的用户信息
	 */
	@SneakyThrows
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		response.setHeader("Content-Type", "application/json;charset=utf-8");
		SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
		System.out.println("执行到onAuthenticationSuccess");

//		UserInfo userInfo = new UserInfo().selectOne(Wrappers.lambdaQuery(UserInfo.class)
//				.select(UserInfo::getNickName, UserInfo::getWeixinName, UserInfo::getIntegral)
//				.eq(UserInfo::getUserId, securityUser.getId()));

		Map<Integer, String> rsaKeyPair = EncryptionUtil.getRsaKeyPair();//生成临时公私钥用于非对称加密
		String userId = String.valueOf(securityUser.getId());//用户id，用于唯一标识每一位用户
		redisService.set(userId,rsaKeyPair.get(1),36000);//将私钥存放到redis中

//		String token = JWTUtil.createToken(new JWTParam(securityUser, 1000L * 60 * 3));
		String token = JWTUtil.createToken(new JWTParam(securityUser));//登陆成功后生成token
		// 设置用户登录信息
		LoginInfoVo loginInfoVo = new LoginInfoVo();
		loginInfoVo.setMessage("success");
		loginInfoVo.setToken(token);
		loginInfoVo.setUserName(securityUser.getUserName());//用户名
		loginInfoVo.setPhone(securityUser.getPhone());//用户电话号码
		loginInfoVo.setMail(securityUser.getMail());//用户邮箱
		loginInfoVo.setRsaPublicKey(rsaKeyPair.get(0));//发给客户端的临时公钥
		loginInfoVo.setUserId(securityUser.getId());//用户id

//		loginInfoVo.setNickName(userInfo.getNickName());
//		loginInfoVo.setIntegral(userInfo.getIntegral());
//		loginInfoVo.setWeixinName(userInfo.getWeixinName());

		String result = ResponseUtil.success(loginInfoVo);
		response.getWriter().write(result);
		response.getWriter().flush();
	}

}
