package org.jit.sose.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.Permissions;
import org.jit.sose.domain.param.ListPageInfoParam;
import org.jit.sose.domain.vo.PermissionsVo;
import org.jit.sose.enums.PermissionTypeEnum;

import java.util.List;
import java.util.Set;

public interface PermissionsMapper {

	/**
	 * 禁用单个请求权限
	 *
	 * @param id
	 */
	void disablePermission(Integer id);

	/**
	 * 删除单个请求权限
	 *
	 * @param id
	 */
	void removePermission(Integer id);

	/**
	 * 批量禁用请求权限
	 *
	 * @param idList
	 */
	void disablePermissionSelect(List<Integer> idList);

	/**
	 * 批量删除请求权限
	 *
	 * @param idList
	 */
	void removePermissionSelect(List<Integer> idList);

	/**
	 * 编辑请求权限
	 *
	 * @param permissions
	 */
	void editPermission(Permissions permissions);

	/**
	 * 新增请求权限
	 *
	 * @param permissions
	 */
	void addPermission(Permissions permissions);

	/**
	 * 根据请求路径、所属模块、所属类型 查询请求权限列表
	 *
	 * @param param
	 * @return
	 */
	List<Permissions> listByURLModuleType(ListPageInfoParam param);

	/**
	 * 根据按钮id查询所有所属的请求路径的Url集合
	 *
	 * @param buttonId 按钮id
	 * @return 请求路径的Url集合
	 */
	Set<String> listUrlByMenuButtonId(Integer buttonId);

	/**
	 * 根据请求权限类型查询请求路径集合
	 *
	 * @param type 请求权限类型
	 * @return 请求路径集合
	 */
	String[] listUrlByType(PermissionTypeEnum type);

	/**
	 * 根据按钮id查询所有所属的请求权限对象
	 *
	 * @param buttonId
	 * @return 请求权限对象集合
	 */
	List<PermissionsVo> listByMenuButtonId(Integer buttonId);

	/**
	 * 通过请求路径查询请求权限对象
	 *
	 * @param url 请求路径
	 * @return 请求权限对象
	 */
	List<PermissionsVo> listByUrl(@Param("url") String url);

	int deleteByPrimaryKey(Integer id);

	int insert(Permissions record);

	int insertSelective(Permissions record);

	Permissions selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Permissions record);

	int updateByPrimaryKey(Permissions record);
}