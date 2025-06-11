package org.jit.sose.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.jit.sose.domain.entity.PermissionsType;
import org.jit.sose.mapper.PermissionsTypeMapper;
import org.jit.sose.service.PermissionsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionsTypeServiceImpl extends ServiceImpl<PermissionsTypeMapper, PermissionsType>
		implements PermissionsTypeService {

	@Autowired
	private PermissionsTypeMapper permissionsTypeMapper;


	@Override
	public List<PermissionsType> listType() {
		return permissionsTypeMapper.listType();
	}

	@Override
	public String showPermissionsType(Integer id) {
		return permissionsTypeMapper.showPermissionsType(id);
	}

	@Override
	public List<String> getTypeName() {
		return permissionsTypeMapper.getTypeName();
	}

	@Override
	public PermissionsType viewTypeByPermissionId(Integer permissionId) {
		return permissionsTypeMapper.viewTypeByPermissionId(permissionId);
	}

	@Override
	public Integer selectIdByTypeName(String typeName) {
		return permissionsTypeMapper.selectIdByTypeName(typeName);
	}
}
