package org.jit.sose.mapper;

import org.jit.sose.domain.entity.MenuFrontPermissions;

public interface MenuFrontPermissionsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuFrontPermissions record);

    int insertSelective(MenuFrontPermissions record);

    MenuFrontPermissions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuFrontPermissions record);

    int updateByPrimaryKey(MenuFrontPermissions record);
}