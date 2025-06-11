package org.jit.sose.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.RoleMenuBack;

import java.util.List;

public interface RoleMenuBackMapper {

    /**
     * 根据用户id查询所有目录和菜单id集合
     *
     * @param roleId 角色id
     * @return 菜单id集合
     */
    List<Integer> listMenuIdByRoleId(Integer roleId);

    /**
     * 批量逻辑删除菜单项
     *
     * @param roleId           角色id
     * @param removeMenuIdList 要删除菜单的id集合
     */
    void deleteList(@Param("roleId") Integer roleId, @Param("removeMenuIdList") List<Integer> removeMenuIdList);

    /**
     * 批量添加或更新菜单项
     *
     * @param roleId        角色id
     * @param addMenuIdList 要添加菜单的id集合
     */
    void insertOrUpdateList(@Param("roleId") Integer roleId, @Param("addMenuIdList") List<Integer> addMenuIdList);

    int deleteByPrimaryKey(Integer id);

    int insert(RoleMenuBack record);

    int insertSelective(RoleMenuBack record);

    RoleMenuBack selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleMenuBack record);

    int updateByPrimaryKey(RoleMenuBack record);
}