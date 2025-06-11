package org.jit.sose.mapper;

import org.jit.sose.domain.entity.MenuFront;

public interface MenuFrontMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MenuFront record);

    int insertSelective(MenuFront record);

    MenuFront selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuFront record);

    int updateByPrimaryKey(MenuFront record);
}