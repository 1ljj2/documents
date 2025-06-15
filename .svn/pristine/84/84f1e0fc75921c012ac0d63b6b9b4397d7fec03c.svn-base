package org.jit.sose.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.jit.sose.domain.entity.MenuBackPermissions;
import org.jit.sose.domain.entity.Permissions;
import org.jit.sose.domain.param.ListPageInfoParam;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.domain.vo.PermissionsVo;
import org.jit.sose.mapper.MenuBackPermissionsMapper;
import org.jit.sose.mapper.PermissionsMapper;
import org.jit.sose.service.PermissionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PermissionsServiceImpl implements PermissionsService {

	@Autowired
	private PermissionsMapper permissionsMapper;

	@Autowired
	private MenuBackPermissionsMapper menuBackPermissionsMapper;

	@Override
	public void disablePermission(Integer id) {
		permissionsMapper.disablePermission(id);
	}

	@Override
	public void removePermission(Integer id) {
		permissionsMapper.removePermission(id);
	}

	@Override
	public void disablePermissionSelect(List<Integer> idList) {
		permissionsMapper.disablePermissionSelect(idList);
	}

	@Override
	public void removePermissionSelect(List<Integer> idList) {
		permissionsMapper.removePermissionSelect(idList);
	}

	@Override
	public void editPermission(Permissions permissions) {
//        //根据模块名称获取请求权限模块id
//        Integer typeId = typeMapper.selectIdByTypeName(permissions.getTypeName());
//        permissions.setPermissionsTypeId(typeId);
		// 编辑请求权限
		permissionsMapper.editPermission(permissions);
	}

	@Transactional
	@Override
	public void addPermission(Permissions permissions) {
		// 新增请求权限
		permissionsMapper.addPermission(permissions);
		System.out.println(permissions);
	}

	@Transactional
	@Override
	public PageInfoVo<Permissions> listPageInfo(ListPageInfoParam param) {
		// 设置分页参数
		PageHelper.startPage(param.getPageNum(), param.getPageSize());
		// 查询集合
		List<Permissions> permissionsList = permissionsMapper.listByURLModuleType(param);
		PageInfo<Permissions> pageInfo = new PageInfo<>(permissionsList);
		// 封装集合
		return new PageInfoVo<>(pageInfo);
	}

	@Override
	public List<PermissionsVo> listByMenuButtonId(Integer buttonId) {
		return permissionsMapper.listByMenuButtonId(buttonId);
	}

	@Override
	public List<PermissionsVo> listByUrl(String url) {
		return permissionsMapper.listByUrl(url);
	}

	@Override
	public void insertMenuPermissions(MenuBackPermissions record) {
		menuBackPermissionsMapper.insertOrUpdate(record);
	}
}
