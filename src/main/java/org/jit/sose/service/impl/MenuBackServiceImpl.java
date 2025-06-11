package org.jit.sose.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.jit.sose.domain.entity.MenuBack;
import org.jit.sose.domain.vo.MenuBackAllAndOwnTreeVo;
import org.jit.sose.domain.vo.MenuBackTableTreeVo;
import org.jit.sose.domain.vo.MenuBackTreeVo;
import org.jit.sose.domain.vo.MenuBackVo;
import org.jit.sose.mapper.MenuBackMapper;
import org.jit.sose.mapper.RoleMenuBackMapper;
import org.jit.sose.service.MenuBackService;
import org.jit.sose.util.CompareListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MenuBackServiceImpl implements MenuBackService {

	@Autowired
	private MenuBackMapper menuBackMapper;

	@Autowired
	private RoleMenuBackMapper roleMenuMapper;

	@Override
	public List<MenuBackVo> listMenuByUserId(Integer userId) {
		// 查询用户所有菜单集合
		List<MenuBack> menuList = menuBackMapper.listMenuByUserId(userId);

		// 返给客户端的菜单集合
		List<MenuBackVo> menuVoList = getMenuVoList(menuList);
		return menuVoList;
	}

	/**
	 * 获取返回去客户端的菜单集合
	 *
	 * @param menuList 原始菜单集合
	 * @return 菜单集合
	 */
	private List<MenuBackVo> getMenuVoList(List<MenuBack> menuList) {
		// 返给客户端的菜单集合
		List<MenuBackVo> menuVoList = new ArrayList<MenuBackVo>();
		for (MenuBack parentMenu : menuList) {
			// 将menu转为menvuVo
			MenuBackVo menuVo = new MenuBackVo(parentMenu);
			menuVoList.add(menuVo);
		}
		return menuVoList;
	}

	@Override
	public MenuBackAllAndOwnTreeVo listAllDirAndOwnTree(Integer roleId) {
		// 定义所有菜单的返回值集合
		ArrayList<MenuBackTreeVo> dirVoList = new ArrayList<MenuBackTreeVo>();
		// 查询所有目录菜单
		List<MenuBack> dirList = menuBackMapper.listAllMenu();
		for (MenuBack dir : dirList) {
			// 转为菜单树
			dirVoList.add(new MenuBackTreeVo(dir));
		}

		// 定义用户拥有的菜单返回值集合
		ArrayList<MenuBackTreeVo> ownDirVoList = new ArrayList<MenuBackTreeVo>();
		// 查询用户所有菜单集合
		List<MenuBack> ownDirList = menuBackMapper.listMenuByRoleId(roleId);
		for (MenuBack menuBack : ownDirList) {
			ownDirVoList.add(new MenuBackTreeVo(menuBack));
		}

		// 封装返回值
		MenuBackAllAndOwnTreeVo menuTreeVo = new MenuBackAllAndOwnTreeVo(dirVoList, ownDirVoList);
		return menuTreeVo;
	}

	@Override
	public List<MenuBackTreeVo> listOwnTree(Integer roleId) {
		// 定义用户拥有的菜单返回值集合
		ArrayList<MenuBackTreeVo> ownDirVoList = new ArrayList<MenuBackTreeVo>();
		// 查询用户所有菜单集合
		List<MenuBack> ownDirList = menuBackMapper.listMenuByRoleId(roleId);
		for (MenuBack menuBack : ownDirList) {
			ownDirVoList.add(new MenuBackTreeVo(menuBack));
		}
		return ownDirVoList;
	}

	@Transactional
	@Override
	public void updateRoleMenu(Integer roleId, List<Integer> menuIdList) {
		// 根据用户id查询所有菜单集合
		List<Integer> ownMenuIdList = roleMenuMapper.listMenuIdByRoleId(roleId);
		// 减少的菜单集合
		List<Integer> removeMenuIdList = CompareListUtil.getReduceaListThanbList(menuIdList, ownMenuIdList);
		// 增加的菜单集合
		List<Integer> addMenuIdList = CompareListUtil.getAddaListThanbList(menuIdList, ownMenuIdList);
		log.debug("角色id为 {}，减少的菜单id集合为 {}", roleId, removeMenuIdList);
		log.debug("角色id为 {}，增加的菜单id集合为 {}", roleId, addMenuIdList);

		// 批量删除菜单
		if (!removeMenuIdList.isEmpty()) {
			roleMenuMapper.deleteList(roleId, removeMenuIdList);
		}
		// 批量添加菜单
		if (!addMenuIdList.isEmpty()) {
			roleMenuMapper.insertOrUpdateList(roleId, addMenuIdList);
		}
	}

	@Override
	public List<MenuBackTableTreeVo> listMenuTableTree() {
		return menuBackMapper.listMenuTableTree();
	}

	@Transactional
	@Override
	public void insert(MenuBack menu) {
		menuBackMapper.insert(menu);
		// 菜单需要添加跳转页面的请求
		if ("menu".equals(menu.getType())) {
			// TODO
		}
	}

	@Override
	public void update(MenuBack menu) {
		menuBackMapper.updateById(menu);

	}

	@Override
	public void delete(Integer id) {
		menuBackMapper.deleteById(id);
	}

	@Override
	public Integer deleteList(List<Integer> idList) {
		return menuBackMapper.deleteBatchIds(idList);
	}

	@Override
	public void disable(Integer id) {
		MenuBack menuBack = new MenuBack();
		menuBack.setId(id);
		menuBack.setEnable(false);
		menuBackMapper.updateById(menuBack);
	}

	@Override
	public Integer disableList(List<Integer> idList) {
		return menuBackMapper.disableList(idList);
	}

}
