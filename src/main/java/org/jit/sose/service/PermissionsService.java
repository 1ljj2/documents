package org.jit.sose.service;

import org.jit.sose.domain.entity.MenuBackPermissions;
import org.jit.sose.domain.entity.Permissions;
import org.jit.sose.domain.param.ListPageInfoParam;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.domain.vo.PermissionsVo;

import java.util.List;

public interface PermissionsService {

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
	 * 根据请求权限所属模块、接口地址分页查询
	 *
	 * @param param
	 * @return 请求权限分页集合
	 */
	PageInfoVo<Permissions> listPageInfo(ListPageInfoParam param);

	/**
	 * 根据按钮id查询所有所属的请求路径
	 *
	 * @param buttonId
	 * @return 请求路径集合
	 */
	List<PermissionsVo> listByMenuButtonId(Integer buttonId);

	/**
	 * 通过请求路径查询请求权限对象
	 *
	 * @param url 请求路径
	 * @return 请求权限对象
	 */
	List<PermissionsVo> listByUrl(String url);

	/**
	 * 添加菜单和权限的关联
	 *
	 * @param record MenuBackPermissions
	 */
	void insertMenuPermissions(MenuBackPermissions record);

}
