package org.jit.sose.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.Role;
import org.jit.sose.domain.vo.ListRoleVo;

import java.util.List;
import java.util.Set;

public interface RoleMapper extends BaseMapper<Role> {

	/**
	 * 根据nameList获取角色idList
	 *
	 * @param roleNameList
	 * @return
	 */
	List<Integer> translateNameToId(List<String> roleNameList);

	/**
	 * 将RoleKeyList转化为IdList
	 *
	 * @param roleKeyList
	 * @return
	 */
	List<Integer> translateKeyToId(Set<String> roleKeyList);

	/**
	 * 根据idList获取角色名称List
	 *
	 * @param roleIdList
	 * @return
	 */
	List<String> showRoleSelected(List<Integer> roleIdList);

	/**
	 * 根据roleName查询角色对象id、name集合
	 *
	 * @param roleName
	 * @return
	 */
	List<ListRoleVo> listRoleByName(@Param("roleName") String roleName);

	/**
	 * 根据id查询角色对象id、name集合
	 *
	 * @param roleName
	 * @return
	 */
	List<ListRoleVo> listRoleNameById(Integer id);

	/**
	 * 依据角色名称查询角色，用于查询用户在微信注册时选择的基础角色的id
	 * 
	 * @param record
	 * @return
	 */
	Role selectByRoleName(Role record);

	int deleteByPrimaryKey(Integer id);

	Role selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Role record);

	int updateByPrimaryKey(Role record);

	/**
	 * 根据用户id查询角色key集合
	 *
	 * @param userId 用户id
	 * @return 角色key集合
	 */
	Set<String> listRoleKeyByUserId(Integer userId);

	/**
	 * 根据用户id查询角色key,Name集合
	 *
	 * @param userId 用户id
	 * @return 角色key, Name集合
	 */
	List<Role> listRoleKeyNameByUserId(Integer userId);

	/**
	 * 查询角色key和id集合
	 *
	 * @return List
	 */
	List<Role> listRoleKeyWithId();

	/**
	 * 过滤查询角色表信息
	 *
	 * @param role 角色信息
	 * @return 角色集合
	 */
	List<Role> listRoleByKeyName(Role role);

	/**
	 * 通过后台菜单id查询角色集合
	 *
	 * @param menuBackId 后台菜单id
	 * @return 角色集合
	 */
	List<Role> listRoleByMenuBackId(Integer menuBackId);

	/**
	 * 插入角色
	 */
	int insert(Role record);

	/**
	 * 批量禁用
	 * 
	 * @param idList id集合
	 */
	Integer disableList(@Param("idList") List<Integer> idList);

}