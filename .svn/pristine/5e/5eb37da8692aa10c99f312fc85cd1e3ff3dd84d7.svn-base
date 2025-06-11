package org.jit.sose.redis;

import org.jit.sose.TestAppDocument;
import org.jit.sose.domain.entity.Role;
import org.jit.sose.mapper.RoleMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class RedisSecurityTest extends TestAppDocument {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	RedisService redisService;

	/**
	 * 将所有角色对应的权限集合放入redis中
	 */
	@Test
	public void saveRoleWithUrl() {
		List<Role> roleList = roleMapper.listRoleKeyWithId();

		String roleKey = "";
		for (Role role : roleList) {
			roleKey += role.getRoleKey() + ";"; // 将所有角色key以分号相连
		}
		// 根据分号相隔获取角色数组
		String[] roleKeys = roleKey.split(";");
		// 删除所有角色缓存
		redisService.del(roleKeys);

		// 重新将权限路径角色存入redis
		for (Role role : roleList) {
			// 根据roleId查询角色对应的权限路径集合
//			Set<String> urls = permissionMapper.listUrlByRoleId(role.getId());
			Set<String> urls = null;
			System.out.println(urls);

			// 将权限路径集合存进redis中，key为roleKey
			long result = redisService.sSetBySet(role.getRoleKey(), urls);
			System.out.println(role.getRoleKey() + " 存放了 " + result + " 个路径");
		}

	}

	/**
	 * 多个Set 求并集
	 */
	@Test
	public void sunion() {
		Set<String> urls = new HashSet<String>();

		String key = "s1";
		Set<String> otherKeys = new HashSet<String>();
		otherKeys.add("s2");
		otherKeys.add("s3");

//		Set<Object> objects = redisService.sUnion(key, otherKeys);
//		Iterator<Object> iterator = objects.iterator();
//		while (iterator.hasNext()) {
//			urls.add(iterator.next().toString());
//		}

		urls = redisService.sUnionString(key, otherKeys);

		Iterator<String> urlSet = urls.iterator();
		while (urlSet.hasNext()) {
			System.out.println(urlSet.next());
		}
	}

	@Test
	public void del() {
		String key = "test";
		redisService.del(key);
	}

	@Test
	public void setTest() {
//		String key = "test";
//		redisService.del(key);
//		Integer value=10;
		String key = "1";
		String value = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJoDeo7KrSNY0XPDzTjHRjVCd00iBPmdqaX+8BWSjzlKt30wdJrF5h2Ihp6b59CA4hErsHyncikjFU+NVmY59/OHjepnyxr1GjIn1vHthKZ/yfUcYLjWhkXoUcUC0l1xXNh/pMfYcX3PDq9xnRCflaJZLpBHt2Mu9iAaDugiYGrbAgMBAAECgYBtCyN98A/v49XAMhSeK7EQynNvYDPHjBJuXfccPv064iTR9TnJz24i4OP3bokNvgLRh25Yg7G3YIiZH4fZrxdW8vWMp077tt04E/pgz5WeVzBbEzBu72ISx5HI98JjXPLop9w/VCTXGRrGIAGifkAAO7mhrtOxifkiBkzbzTS24QJBAM42YhspIQfoaHMj9MB69Nh5KdnGHmbSISVvYX+R9WgZ7goal1wZYu7TL/cHjHX4mkhLSlGk/QeJOfuTwtSpJUsCQQC/MseMZWdWYmIPYxopwLgqMzFzaT2qvKIwgw+YIcZPl5hHAN9o0XEUzN4LJR/Lsr6GNGI+trgQI7E1HOg9XaaxAkBah/2iNvhNZHcWtZ5qMpDzyJ3bAeOu+Gmc6b1AKHA498lNnkb9JBgPCaBxL1s3H6F8Q7GpDmoXuOM/06shefDpAkEApEYI1UO6yam1/upy7DS9BuT9M9/UMAHqiCvTFi7OqEvjdf319aUdt3Vdwc2fx/BHah9P2fo7owJHmw6/KxpvwQJBAJfnm7wrA7624T8FO6rkoi40umO1NbyzgMkzZ34fmft6v5+ZuxVpyRRnm6Khn9/nLDJyy6YB58u9W/n8iXLvof0=";

//		long time=30000;
//		redisService.set(key,value,time);
		redisService.set(key,value);
		System.out.println(redisService.get("1"));
	}

}
