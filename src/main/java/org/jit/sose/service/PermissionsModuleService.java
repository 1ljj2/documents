package org.jit.sose.service;

import org.jit.sose.domain.entity.PermissionsModule;

import java.util.List;

public interface PermissionsModuleService {

    /**
     * 根据模块名称模糊查询
     *
     * @param query
     * @return PermissionsModule集合
     */
    List<PermissionsModule> listModuleByName(String query);

    /**
     * 获取所有的模块名称
     *
     * @return
     */
    List<String> getModuleName();

    /**
     * 根据请求权限id 看请求所属模块详情
     *
     * @param permissionId
     * @return
     */
    PermissionsModule viewModuleByPermissionId(Integer permissionId);

    /**
     * 根据模块名称查询模块id
     * @param moduleName
     */
    Integer selectIdBymoduleName(String moduleName);
}
