package org.jit.sose.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.jit.sose.domain.entity.Role;
import org.jit.sose.domain.entity.UserRole;
import org.jit.sose.domain.vo.ListRoleVo;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.domain.vo.RoleListAndOwnVo;
import org.jit.sose.mapper.RoleMapper;
import org.jit.sose.mapper.UserRoleMapper;
import org.jit.sose.service.RoleService;
import org.jit.sose.util.CompareListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserRoleMapper userRoleMapper;

	@Override
	public List<Integer> translateNameToId(List<String> roleNameList) {
		return roleMapper.translateNameToId(roleNameList);
	}

	@Override
	public List<Integer> translateKeyToId(Set<String> roleKeyList) {
		return roleMapper.translateKeyToId(roleKeyList);
	}

	@Override
	public List<String> showRoleSelected(List<Integer> roleIdList) {
		return roleMapper.showRoleSelected(roleIdList);
	}

	@Override
	public List<ListRoleVo> listRoleByName(String roleName) {
		return roleMapper.listRoleByName(roleName);
	}

	@Override
	public List<ListRoleVo> listRoleNameById(Integer id) {
		return roleMapper.listRoleNameById(id);
	}

	@Override
	public PageInfoVo<Role> listPageInfo(Role role, Integer pageNum, Integer pageSize) {
		// 设置分页参数
		PageHelper.startPage(pageNum, pageSize);
		// 查询集合
		List<Role> roleList = roleMapper.listRoleByKeyName(role);
		PageInfo<Role> pageInfo = new PageInfo<>(roleList);
		// 封装集合
		return new PageInfoVo<>(pageInfo);
	}

	@Override
	public void insert(Role role) {
		roleMapper.insert(role);
	}

	@Override
	public Role selectByRoleName(Role record) {
		return roleMapper.selectByRoleName(record);
	}

	@Override
	public void update(Role role) {
		roleMapper.updateById(role);
	}

	@Override
	public RoleListAndOwnVo listRoleKeyNameByUserId(Integer userId) {

		// 查询当前用户的角色列表
		List<Role> ownRoleList = roleMapper.listRoleKeyNameByUserId(userId);

		// 查询所有的角色列表
		LambdaQueryWrapper<Role> query = new LambdaQueryWrapper<>();
		query.select(Role::getId, Role::getRoleKey, Role::getRoleName).eq(Role::isEnable, true)
				.orderByAsc(Role::getCreateDate);
		List<Role> roleList = roleMapper.selectList(query);

		return new RoleListAndOwnVo(ownRoleList, roleList);
	}

	@Transactional
	@Override
	public void updateUserRole(Integer userId, List<Integer> roleIdList) {
		// 查询用户已有的角色集合
		LambdaQueryWrapper<UserRole> query = new LambdaQueryWrapper<>();
		query.select(UserRole::getRoleId).eq(UserRole::getUserId, userId);
		List<Object> list = userRoleMapper.selectObjs(query);

		List<Integer> ownRoleIdList = new ArrayList<>();
		for (Object object : list) {
			ownRoleIdList.add(Integer.valueOf(object.toString()));
		}

		// 减少的菜单集合
		List<Integer> removeMenuIdList = CompareListUtil.getReduceaListThanbList(roleIdList, ownRoleIdList);
		// 增加的菜单集合
		List<Integer> addMenuIdList = CompareListUtil.getAddaListThanbList(roleIdList, ownRoleIdList);
		log.debug("角色id为 {}，减少的菜单id集合为 {}", userId, removeMenuIdList);
		log.debug("角色id为 {}，增加的菜单id集合为 {}", userId, addMenuIdList);

		// 批量删除菜单
		if (!removeMenuIdList.isEmpty()) {
			userRoleMapper.deleteList(userId, removeMenuIdList);
		}
		// 批量添加菜单
		if (!addMenuIdList.isEmpty()) {
			userRoleMapper.insertOrUpdateList(userId, addMenuIdList);
		}
	}

	@Override
	public void deleteList(List<Integer> idList) {
		roleMapper.deleteBatchIds(idList);
	}

	@Override
	public void disableList(List<Integer> idList) {
		roleMapper.disableList(idList);
	}

}
