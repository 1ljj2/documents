package org.jit.sose.web.scheduled;

import lombok.extern.slf4j.Slf4j;
import org.jit.sose.mapper.RoleMapper;
import org.jit.sose.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Redis相关定时任务
 * 
 * @author wangyue
 * @date 2020-04-02 00:56:07
 */
@Slf4j
@Configuration
@EnableScheduling // 开启定时任务
public class RedisScheduled {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	RedisService redisService;

	/**
	 * 保存角色对应的权限路径
	 */
//	@Scheduled(cron = "0 10 1 * * ?")
//	public void saveRoleWithUrl() {
//		log.info("********缓存角色权限开始********");
//		List<Role> roleList = roleMapper.listRoleKeyWithId();
//
//		String roleKey = "";
//		for (Role role : roleList) {
//			roleKey += role.getRoleKey() + ";"; // 将所有角色key以分号相连
//		}
//		// 根据分号相隔获取角色数组
//		String[] roleKeys = roleKey.split(";");
//		// 删除所有角色缓存
//		redisService.del(roleKeys);
//
//		// 重新将权限路径角色存入redis
//		for (Role role : roleList) {
//			// 根据roleId查询角色对应的权限路径集合
//			Set<String> urls = permissionMapper.listUrlByRoleId(role.getId());
//			log.info(urls.toString());
//
//			// 将权限路径集合存进redis中，key为roleKey
//			long result = redisService.sSetBySet(role.getRoleKey(), urls);
//			log.info(role.getRoleKey() + " 存放了 " + result + " 个路径");
//		}
//		log.info("********缓存角色权限完成********");
//	}

	// 添加定时任务
//	@Scheduled(cron = "0/5 * * * * ?")
//	private void configureTasks() {
//		System.err.println("执行静态定时任务时间: " + LocalDateTime.now());
//	}

}
