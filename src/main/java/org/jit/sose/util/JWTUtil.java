package org.jit.sose.util;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.jit.sose.constant.JWTConstant;
import org.jit.sose.domain.entity.SecurityUser;
import org.jit.sose.domain.entity.User;
import org.jit.sose.domain.param.JWTParam;
import org.jit.sose.properties.JWTProperties;

import java.util.Date;
import java.util.HashMap;

/**
 * json web token 生成工具类
 * 
 * @author wangyue
 * @date 2019-09-10 22:56:49
 */
@Slf4j
public class JWTUtil {

	/**
	 * 创建token，token前缀
	 * 
	 * @param param 参数封装类
	 * @return token
	 */
	public static String createToken(JWTParam param) {
		// 设置私有字段
		// 方法二
		HashMap<String, Object> claimsMap = new HashMap<>();

		claimsMap.put("userId", param.getUserId()); // 用户id
		claimsMap.put("userName", param.getUserName());
//		claimsMap.put("role", param.getRole());

		// 当前时间
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		log.trace("token生成时间：" + DateFormatUtil.formatSecond(now));

		// 签名算法
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//		SecretKey secretKey = generalKey();
		// 密钥
//		System.out.println(generalKey());

		JwtBuilder builder = Jwts.builder()
				// 添加负载
				.setClaims(claimsMap) // 私有字段集合
//				.setSubject(subject) // 主题,
				.setIssuer(JWTProperties.ISS) // 签发人
				.setIssuedAt(now) // 签发时间，设置当前时间，确保每次生成的token的负载都不一样
				.signWith(signatureAlgorithm, generalKey()); // 签名算法以及密匙（头部）

		// 声明有效时间
		long expMillis;
		if (param.getExpiration() != null && param.getExpiration() >= 0) {
			// 自定义过期时间
			expMillis = nowMillis + param.getExpiration();
		} else if (JWTProperties.EXPIRATION != null) {
			// 设置配置文件中过期时间
			expMillis = nowMillis + JWTProperties.EXPIRATION;
		} else {
			// 设置为默认时间,2小时
			expMillis = nowMillis + JWTConstant.EXPIRATION;
		}
		Date expDate = new Date(expMillis);
		builder.setExpiration(expDate); // 过期时间
		log.trace("token失效时间：" + DateFormatUtil.formatSecond(expDate));

		String token = JWTProperties.TOKEN_PREFIX + builder.compact();
		return token;
	}

	/**
	 * 解析JWT字符串
	 * 
	 * @param token
	 * @return
	 */
	public static Claims parseJWT(String token) {
		return Jwts.parser().setSigningKey(generalKey()).parseClaimsJws(token).getBody();
	}

	/**
	 * 并且获取token解析内容，判断token是否有效
	 * 
	 * @param token
	 * @return 有效:claim;失效:null
	 */
	public static Claims isUseful(String token) {
		Claims claims = null;
		try {
			// 解析token,正常解析未过期
			claims = parseJWT(token);
			log.trace("token未过期，有效时间：" + DateFormatUtil.formatSecond(claims.getExpiration()));
		} catch (ExpiredJwtException e) {
			// 解析出错，token过期
			log.trace("token解析错误");
		}
		return claims;
	}

	/**
	 * 如果需要刷新token，返回新的token<br>
	 * 如果不需要则返回null<br>
	 * 没有token前缀
	 * 
	 * @param token 旧token
	 * @return null/token
	 */
	public static String refreshToken(Claims claims) {
		// token创建时间
		Date createTime = claims.getIssuedAt();
		// 需要刷新token的时间= 创建时间+刷新时间
		Date refreshTime = new Date(createTime.getTime() + JWTConstant.RRFRESH_TIME);
		// token过期时间
		Date expiration = getExpiration(claims);
		// 当前时间
		Date nowDate = new Date();

		log.trace("token需要刷新的时间：" + DateFormatUtil.formatSecond(refreshTime));
		// 需要刷新token的时间 在旧过期时间或当前之后,不需要刷新token
		if (refreshTime.after(nowDate) || refreshTime.after(expiration)) {
			return null;
		}
		log.trace("刷新token");

		// 创建新的token
		HashMap<String, Object> claimsMap = new HashMap<>();

		claimsMap.put("userId", getUserId(claims));
		claimsMap.put("userName", getUsername(claims));
//		claimsMap.put("role", getUserRole(claims));

		// 当前时间
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		log.trace("新token生成时间：" + DateFormatUtil.formatSecond(now));

		// 签名算法
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		JwtBuilder builder = Jwts.builder().setClaims(claimsMap) // 方式二、私有字段集合
				.setIssuer(JWTProperties.ISS) // 签发人
				.setIssuedAt(now) // 签发时间，设置当前时间，确保每次生成的token的负载都不一样
				.setExpiration(getExpiration(claims))// 过期时间
				.signWith(signatureAlgorithm, generalKey()); // 签名算法以及密匙（头部）

		log.trace("新token失效时间：" + DateFormatUtil.formatSecond(getExpiration(claims)));

		// 完成生成
		return builder.compact();
	}

	/**
	 * 获取过期时间
	 * 
	 * @param token
	 * @return
	 */
	public static Date getExpiration(Claims claims) {
		return claims.getExpiration();
	}

	/**
	 * 获取主题
	 * 
	 * @param token
	 * @return
	 */
	public static String getSubject(Claims claims) {
		return (String) claims.getSubject();
	}

	/**
	 * 从token中获取用户id
	 * 
	 * @param token
	 * @return 用户id
	 */
	public static Integer getUserId(Claims claims) {
		return (Integer) claims.get("userId");
	}

	/*
	 * 从token中获取用户id
	 * 
	 * @param token
	 * 
	 * @return 用户id
	 */
	public static Integer getUserId(String token) {
		Claims claims = parseJWT(token);
		return (Integer) claims.get("userId");
	}

	/**
	 * 从token中获取用户名
	 * 
	 * @param token
	 * @return 用户名
	 */
	public static String getUsername(Claims claims) {
		return (String) claims.get("userName");
	}

	/**
	 * 从token中获取用户名
	 * 
	 * @param token
	 * @return 用户名
	 */
	public static String getUsername(String token) {
		Claims claims = parseJWT(token);
		return (String) claims.get("userName");
	}

	/**
	 * 从token中获取用户角色
	 * 
	 * @param token
	 * @return 用户角色
	 */
	public static String getUserRole(Claims claims) {
		return (String) claims.get("role");
	}

	/**
	 * 从token中获取用户信息：id、userName和roles
	 * 
	 * @param token
	 * @return User
	 */
	public static User getUser(String token) {
		User user = new User();
		Claims claims = parseJWT(token);
		user.setId(getUserId(claims));
		user.setUserName(getUsername(claims));
		return user;
	}

	public static SecurityUser getSecurityUser(Claims claims) {
		int id = getUserId(claims);
		String userName = getUsername(claims);
		SecurityUser securityUser = new SecurityUser(id, userName, null, null, null, null);
		return securityUser;
	}

	/**
	 * 加密秘钥
	 * 
	 * @return 加密后的秘钥
	 */
	public static String generalKey() {
		String key = new Md5Hash(JWTProperties.SECRET, JWTProperties.ISS).toString();
		return key;
	}

}
