package org.jit.sose.service;

import org.jit.sose.domain.entity.MenuBack;
import org.jit.sose.domain.vo.MenuBackAllAndOwnTreeVo;
import org.jit.sose.domain.vo.MenuBackTableTreeVo;
import org.jit.sose.domain.vo.MenuBackTreeVo;
import org.jit.sose.domain.vo.MenuBackVo;

import java.util.List;

public interface MenuBackService {

	/**
	 * 通过用户id递归查询所有菜单
	 *
	 * @param userId 用户id
	 * @return 菜单集合
	 */
	List<MenuBackVo> listMenuByUserId(Integer userId);

	/**
	 * 查询所有目录菜单和角色拥有的菜单集合
	 *
	 * @param roleId 角色id
	 * @return 所有目录菜单和角色拥有的菜单集合的对象
	 */
	MenuBackAllAndOwnTreeVo listAllDirAndOwnTree(Integer roleId);

	/**
	 * 查询所有目录菜单和角色拥有的菜单集合
	 *
	 * @param userId 角色id
	 * @return 角色拥有的菜单集合的对象
	 */
	List<MenuBackTreeVo> listOwnTree(Integer userId);

	/**
	 * 更新角色对应的菜单集合
	 *
	 * @param roleId     角色id
	 * @param menuIdList 菜单id集合
	 */
	void updateRoleMenu(Integer roleId, List<Integer> menuIdList);

	/**
	 * 查询所有的目录和对应的菜单，以及菜单对应的按钮<br>
	 * 用于iview表格树
	 *
	 * @return 所有菜单表格树
	 */
	List<MenuBackTableTreeVo> listMenuTableTree();

	/**
	 * 新增
	 *
	 * @param menu MenuBack
	 */
	void insert(MenuBack menu);

	/**
	 * 修改
	 *
	 * @param menu MenuBack
	 */
	void update(MenuBack menu);

	/**
	 * 删除
	 * 
	 * @param id 主键
	 */
	void delete(Integer id);

	/**
	 * 批量删除
	 * 
	 * @param idList 主键集合
	 * @return 删除的数量
	 */
	Integer deleteList(List<Integer> idList);

	/**
	 * 禁用
	 * 
	 * @param id 主键
	 */
	void disable(Integer id);

	/**
	 * 批量禁用
	 * 
	 * @param idList 主键集合
	 * @return 删除的数量
	 */
	Integer disableList(List<Integer> idList);

}
