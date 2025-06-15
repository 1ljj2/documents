package org.jit.sose.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.MenuBack;
import org.jit.sose.domain.vo.MenuBackTableTreeVo;

import java.util.List;
import java.util.Map;

public interface MenuBackMapper extends BaseMapper<MenuBack> {

	/**
	 * 根据角色key查询对应的二级菜单id和url
	 *
	 * @param roleKey 角色key
	 * @return 二级菜单id和url
	 */
	List<Map<String, Object>> listMenuByRoleKey(String roleKey);

	/**
	 * 根据角色key和菜单id查询对应的菜单按钮id集合
	 *
	 * @param roleKey 角色key
	 * @param menuId  菜单按钮id
	 * @return 菜单按钮id集合
	 */
	List<Integer> listMenuButtonIdByRoleKeyAndMenuId(@Param("roleKey") String roleKey, @Param("menuId") Integer menuId);

	/**
	 * 通过用户id递归查询所有菜单
	 *
	 * @param userId 用户id
	 * @return 菜单集合
	 */
	List<MenuBack> listMenuByUserId(Integer userId);

	/**
	 * 通过父菜单id和用户id查询子菜单列表
	 *
	 * @param parentId 父目录id
	 * @param userId   用户id
	 * @return 子菜单列表
	 */
	List<MenuBack> listMenuByDirIdAndUserId(@Param("dirId") Integer parentId, @Param("userId") Integer userId);

	/**
	 * 通过角色id递归查询所有菜单
	 *
	 * @param roleId 角色id
	 * @return 菜单集合
	 */
	List<MenuBack> listMenuByRoleId(Integer roleId);

	/**
	 * 通过父菜单id和角色id查询子菜单列表
	 *
	 * @param parentId 父目录id
	 * @param roleId   角色id
	 * @return 子菜单列表
	 */
	List<MenuBack> listMenuByParentIdAndRoleId(@Param("parentId") Integer parentId, @Param("roleId") Integer roleId,
			@Param("level") Integer level);

	/**
	 * 查询所有的目录和对应的菜单，以及菜单对应的按钮
	 *
	 * @return 所有菜单列表
	 */
	List<MenuBack> listAllMenu();

	/**
	 * 通过父菜单id查询子菜单列表
	 *
	 * @param parentId 父目录id
	 * @param level    子菜单等级
	 * @return 子菜单列表
	 */
	List<MenuBack> listMenuByParentId(@Param("parentId") Integer parentId, @Param("level") Integer level);

	/**
	 * 查询所有的目录和对应的菜单，以及菜单对应的按钮<br>
	 * 用于iview表格树
	 *
	 * @return 所有菜单表格树
	 */
	List<MenuBackTableTreeVo> listMenuTableTree();

	/**
	 * 通过父菜单id查询子菜单列表<br>
	 * children
	 *
	 * @param parentId 父目录id
	 * @param level    子菜单等级
	 * @return 子菜单表格树
	 */
	List<MenuBackTableTreeVo> listMenuTableTreeByParentId(@Param("parentId") Integer parentId,
			@Param("level") Integer level);

	/**
	 * 新增
	 *
	 * @param record MenuBack
	 * @return
	 */
	int insert(MenuBack record);

	/**
	 * 批量禁用
	 * 
	 * @param idList id集合
	 */
	Integer disableList(@Param("idList") List<Integer> idList);

}