package org.jit.sose.web.security;

import lombok.extern.slf4j.Slf4j;
import org.jit.sose.constant.SecurityConstant;
import org.jit.sose.domain.entity.Role;
import org.jit.sose.domain.entity.SecurityUser;
import org.jit.sose.redis.RedisService;
import org.jit.sose.service.UserService;
import org.jit.sose.util.WebServletUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * Rbac:基于角色的访问控制（Role-Based policies Access Control）<br>
 * 校验用户是否有权限访问请求<br>
 * 在SecurityConfig中配置<br>
 * 根据上下文中的用户信息获取访问权限（即可以请求的路径）<br>
 * 判断当前的请求路径是否在用户可以访问的路径集合当中<br>
 * 若用户没有权限，则跳转至GoAccessDeniedHandler
 *
 * @author wangyue
 * @date 2020-01-05 15:43:37
 */
@Slf4j
@Component("rbacauthorityservice")
public class RbacAuthorityService {

	@Autowired
	private UserService userService;

	@Autowired
	RedisService redisService;

	/**
	 * 判断当前用户是否有权限访问当前请求<br>
	 * true:有权限；false:无权限
	 */
	public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
		System.out.println("执行到hasPermission");

		// 是否有访问权限
		boolean hasPermission = false;

		String refererUri = getRefererUri(request);

		// 放行App的请求
		if (refererUri == null) {
			refererUri = getRefererAppUri(request);
			if (Boolean.valueOf(refererUri)) {
				log.trace("放行App请求：" + request.getRequestURI());
				return true;
			}
		}

		// 从Authentication中获取用户身份信息
		Object userInfo = authentication.getPrincipal();

		// 未认证，security会设置用户为匿名用户
		if ("anonymousUser".equals(userInfo)) {
			return hasPermission;
		}
		// 已认证，转为SecurityUser
		SecurityUser securityUser = (SecurityUser) userInfo;

		log.trace("检查权限:" + WebServletUtil.getClientIpAddress(request) + "-当前用户：" + securityUser.getUserName()
				+ "-请求路径：" + request.getRequestURI());

		// 以'.htm'结尾的为页面请求
		if (request.getRequestURI().endsWith(SecurityConstant.AJAX_MEUN_SUFFIX)) {
			// 检查页面权限
			hasPermission = checkRoleMenu(request, securityUser);
		} else {
			// 检查请求权限
			hasPermission = checkMenuBackPermissions(request, securityUser);
		}
		log.trace("检查权限:" + WebServletUtil.getClientIpAddress(request) + "-当前用户：" + securityUser.getUserName()
				+ "-请求路径：" + request.getRequestURI()+"检查权限结果:" + hasPermission);
		return hasPermission;
	}

	/**
	 * 检查角色对应的页面权限
	 *
	 * @param request      HttpServletRequest
	 * @param securityUser 认证用户信息
	 * @return 有权限:<code>true</code><br>
	 *         无权限:<code>false</code>
	 */
	private Boolean checkRoleMenu(HttpServletRequest request, SecurityUser securityUser) {
		// 根据当前用户的角色判断是否有权限
		log.trace("页面权限检查");
		// 去掉后缀
		String menuUrl = request.getRequestURI().substring(0,
				request.getRequestURI().length() - SecurityConstant.AJAX_MEUN_SUFFIX.length());

		Set<String> roles = null;
		try {
			// 通过用户id和用户名查询角色集合
			roles = userService.getRoles(securityUser);

			Boolean isIsMember = false;
			for (String roleKey : roles) {
				String menuKey = Role.getRedisMenuKey(roleKey);
				isIsMember = redisService.sIsMember(menuKey, menuUrl);
				if (isIsMember) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			log.error("获取权限集合错误");
			return false;
		}
	}

	/**
	 * 检查后台页面对应的请求权限
	 *
	 * @param request      HttpServletRequest
	 * @param securityUser 认证用户信息
	 * @return 有权限:<code>true</code><br>
	 *         无权限:<code>false</code>
	 */
	private Boolean checkMenuBackPermissions(HttpServletRequest request, SecurityUser securityUser) {
		log.trace("检查请求权限");
		String refererUri = getRefererUri(request);

		log.trace("当前路径：{}，请求：{}", refererUri, request.getRequestURI());

		// 通过用户id和用户名查询角色集合
		Set<String> roles = userService.getRoles(securityUser);
		// 根据role 从redis中获取其所有请求权限集合
		Set<String> urls = userService.getPermissionURIs(roles, refererUri);

//		for(String s:urls){
////			System.out.println(s);
////		}
		String requestURL =  request.getRequestURI().substring(0,request.getRequestURI().lastIndexOf("/")+1)+"*";//下载文件请求地址为动态,修改为静态地址
//		log.trace("当前路径：{}，请求：{}", refererUri, requestURL);
//		log.trace("当前路径集合：{}，判断结果1：{}，判断结果2：{}", urls,urls != null && urls.contains(request.getRequestURI()),urls != null && urls.contains(requestURL));
		// 判断当前用户是否拥有 当前请求的权限
		if ((urls != null && urls.contains(request.getRequestURI()))||(urls != null && urls.contains(requestURL))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取 页面请求路径
	 *
	 * @param request HttpServletRequest
	 * @return 页面请求路径
	 */
	private String getRefererUri(HttpServletRequest request) {
		String refererUrl = request.getHeader(SecurityConstant.AJAX_HEADER_KEY_REFERER_URI);
		return refererUrl;
	}

	/**
	 * 在App中模拟获取 页面请求路径
	 *
	 * @param request HttpServletRequest
	 * @return 页面请求路径
	 */
	private String getRefererAppUri(HttpServletRequest request) {
		String refererUrl = request.getHeader(SecurityConstant.AJAX_HEADER_KEY_REFERER_URI_APP);
		return refererUrl;
	}

}
