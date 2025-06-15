package org.jit.sose.service.impl;

import org.jit.sose.domain.entity.PermissionsModule;
import org.jit.sose.mapper.PermissionsModuleMapper;
import org.jit.sose.service.PermissionsModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionsModuleServiceImpl implements PermissionsModuleService {

    @Autowired
    private PermissionsModuleMapper permissionsModuleMapper;


    @Override
    public List<PermissionsModule> listModuleByName(String query) {
        return permissionsModuleMapper.listModuleByName(query);
    }

    @Override
    public List<String> getModuleName() {
        return permissionsModuleMapper.getModuleName();
    }

    @Override
    public PermissionsModule viewModuleByPermissionId(Integer permissionId) {
        return permissionsModuleMapper.viewModuleByPermissionId(permissionId);
    }

    @Override
    public Integer selectIdBymoduleName(String moduleName) {
        return permissionsModuleMapper.selectIdBymoduleName(moduleName);
    }
}
