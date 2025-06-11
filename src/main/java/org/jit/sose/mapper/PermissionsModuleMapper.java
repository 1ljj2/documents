package org.jit.sose.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.PermissionsModule;

import java.util.List;

public interface PermissionsModuleMapper {

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
     * 根据类型名称查看是否存在
     *
     * @param moduleName
     * @return
     */
    Integer countByModuleName(String moduleName);

    /**
     * 根据请求权限id 看请求所属模块详情
     *
     * @param permissionId
     * @return
     */
    PermissionsModule viewModuleByPermissionId(Integer permissionId);

    /**
     * 根据模块名称查询模块id
     *
     * @param moduleName
     */
    Integer selectIdBymoduleName(@Param("moduleName") String moduleName);

    int deleteByPrimaryKey(Integer id);

    int insert(PermissionsModule record);

    int insertSelective(PermissionsModule record);

    PermissionsModule selectByPrimaryKey(Integer id);

    int updateByperPrimaryKeySelective(PermissionsModule record);

    int updateByPrimaryKey(PermissionsModule record);
}