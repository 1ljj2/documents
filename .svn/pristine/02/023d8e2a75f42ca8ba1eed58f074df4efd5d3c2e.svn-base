package org.jit.sose.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jit.sose.TestAppDocument;
import org.jit.sose.constant.RedisConstant;
import org.jit.sose.domain.entity.MenuBack;
import org.jit.sose.domain.entity.Role;
import org.jit.sose.domain.entity.User;
import org.jit.sose.mapper.MenuBackMapper;
import org.jit.sose.mapper.PermissionsMapper;
import org.jit.sose.mapper.RoleMapper;
import org.jit.sose.redis.RedisService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecurityTest extends TestAppDocument {

	@Autowired
	private PermissionsMapper permissionsMapper;

	@Autowired
	private MenuBackMapper menuBackMapper;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	RedisService redisService;

	@Test
	public void savePerssions() {
//		String roleKey = "ROLE_ADMIN";
//		String roleKey = "ROLE_SYSTEM";
//		String roleKey = "ROLE_USER";

		// 0.清空redis
		System.out.println("==========清空权限key==========");
		Set<String> keys = redisService.keys(RedisConstant.MENU_BACK_KEY_PREFIX + "*");
		keys.forEach(System.out::println);
		Long delNum = redisService.del(keys);
		System.out.println("共删除" + delNum + "个权限key/n");

		System.out.println("==========清空页面key==========");
		Set<String> menuKeys = redisService.keys(RedisConstant.ROLE_MENU__KEY_PREFIX + "*");
		menuKeys.forEach(System.out::println);
		Long delNum2 = redisService.del(menuKeys);
		System.out.println("共删除" + delNum2 + "个页面key");

		// 1.查询所有角色
		List<Map<String, Object>> roleList = roleMapper
				.selectMaps(new LambdaQueryWrapper<Role>().select(Role::getRoleKey).eq(Role::isEnable, true));

		roleList.forEach(role -> {
			System.out.println("=======================");
			String roleKey = role.get("role_key").toString();
			System.out.println("roleKey:" + roleKey);

			// 2.查询角色所有菜单
			List<Map<String, Object>> menuList = menuBackMapper.listMenuByRoleKey(roleKey);

			menuList.forEach(menu -> {
				System.out.println("	menu:" + menu.get("id") + ":" + menu.get("url"));
//				Integer menuId = ((Long) menu.get("id")).intValue();
				Integer menuId = (Integer) menu.get("id");

				// 2.5.缓存角色对应的页面请求
				String menuKey = Role.getRedisMenuKey(roleKey);
				redisService.sSet(menuKey, menu.get("url"));

				// 3.查询菜单所有按钮
				List<Integer> buttonIdList = menuBackMapper.listMenuButtonIdByRoleKeyAndMenuId(roleKey, menuId);
				buttonIdList.forEach(buttonId -> {
					System.out.println("    	buttonId：" + buttonId);

					// 4.查询按钮所有请求
					Set<String> urls = permissionsMapper.listUrlByMenuButtonId(buttonId);
					urls.forEach(url -> {
						System.out.println("			url:" + url);
					});

					// 5.redis缓存
					if(!urls.isEmpty()) {
						String key = MenuBack.getRedisPermissionsKey(roleKey, menu.get("url").toString());
						long result = redisService.sSetBySet(key, urls);
						System.out.println("	" + key + "成功放入" + result + "条路径");
					}
					
				});
			});

		});

	}

	@Test
	public void sIsMember() {
		String roleKey = "ROLE_MERCHANT";
		String menuUrl = "/config/eec";
		String menuKey = Role.getRedisMenuKey(roleKey);
		Boolean isIsMember = redisService.sIsMember(menuKey, menuUrl);
		System.out.println(isIsMember);
	}

	@Test
	public void getPermissons() {
		String roleKey = "ROLE_ADMIN";
		String menuUrl = "/process/proService";
		String key = MenuBack.getRedisPermissionsKey(roleKey, menuUrl);
		Set<String> permissonsSet = redisService.sGetString(key);
		permissonsSet.forEach(System.out::println);
	}

	/**
	 * 根据用户名设置用户角色的缓存
	 */
	@Test
	public void setRoleByUserName() {
		int userId = 2;
		String redisRoleKey = User.getRedisRoleKey("zhangsan");

		Set<String> roles = new HashSet<String>();
		// 若redis存在当前key
		if (redisService.hasKey(redisRoleKey)) {
			// 从redis中获取集合
			roles = redisService.sGetString(redisRoleKey);
		} else {
			// redis不存在角色key，从数据库读取，并放在redis中
			roles = roleMapper.listRoleKeyByUserId(userId);
			long result = redisService.sSetBySet(redisRoleKey, roles, 60 * 60 * 1);
			System.out.println(redisRoleKey + " 成功存放了 " + result + " 个角色");
		}
		System.out.println(roles);
	}

}
