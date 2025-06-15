package org.jit.sose.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * JWT 配置类
 * 
 * @author wangyue
 * @date 2019-09-11 14:54:11
 */
@Configuration
@PropertySource("classpath:properties/jwt.properties")
public class JWTProperties {

	/**
	 * 密钥
	 */
	public static String SECRET;

	/**
	 * 签发人
	 */
	public static String ISS;

	/**
	 * 浏览器缓存key
	 */
	public static String TOKEN_KEY;

	/**
	 * token前缀
	 */
	public static String TOKEN_PREFIX;

	/**
	 * 有效时间
	 */
	public static Long EXPIRATION;

	@Value("${SECRET}")
	public void setSECRET(String SECRET) {
		JWTProperties.SECRET = SECRET;
	}

	@Value("${ISS}")
	public void setISS(String ISS) {
		JWTProperties.ISS = ISS;
	}

	@Value("${TOKEN_KEY}")
	public void setTOKEN_KEY(String TOKEN_KEY) {
		JWTProperties.TOKEN_KEY = TOKEN_KEY;
	}

	@Value("${TOKEN_PREFIX}")
	public void setTOKEN_PREFIX(String TOKEN_PREFIX) {
		JWTProperties.TOKEN_PREFIX = TOKEN_PREFIX;
	}

	@Value("${EXPIRATION}")
	public void setEXPIRATION(Long EXPIRATION) {
		JWTProperties.EXPIRATION = EXPIRATION;
	}

}
