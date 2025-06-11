package org.jit.sose.service;

import org.jit.sose.domain.entity.Role;
import org.jit.sose.domain.vo.ListRoleVo;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.domain.vo.RoleListAndOwnVo;

import java.util.List;
import java.util.Set;

public interface RoleService {

    /**
     * 根据nameList获取角色idList
     *
     * @param roleNameList
     * @return
     */
    List<Integer> translateNameToId(List<String> roleNameList);

    /**
     * 将RoleKeyList转化为IdList
     *
     * @param roleKeyList
     * @return
     */
    List<Integer> translateKeyToId(Set<String> roleKeyList);

    /**
     * 根据idList获取角色名称List
     *
     * @param roleIdList
     * @return
     */
    List<String> showRoleSelected(List<Integer> roleIdList);

    /**
     * 根据roleName查询角色对象id、name集合
     *
     * @param roleName
     * @return
     */
    List<ListRoleVo> listRoleByName(String roleName);

    /**
     * 根据id查询角色对象id、name集合
     *
     * @param id
     * @return
     */
    List<ListRoleVo> listRoleNameById(Integer id);

    /**
     * 过滤查询
     *
     * @param role     查询参数
     * @param pageNum  当前页索引
     * @param pageSize 当前页大小
     * @return 角色分页列表
     */
    PageInfoVo<Role> listPageInfo(Role role, Integer pageNum, Integer pageSize);

    /**
     * 插入
     *
     * @param role Role
     */
    void insert(Role role);

    /**
     * 依据角色名称查询角色，用于查询用户在微信注册时选择的基础角色的id
     *
     * @param record
     * @return
     */
    Role selectByRoleName(Role record);

    /**
     * 插入
     *
     * @param role Role
     */
    void update(Role role);

    /**
     * 批量删除
     *
     * @param idList 主键集合
     */
    void deleteList(List<Integer> idList);

    /**
     * 批量禁用
     *
     * @param idList 主键集合
     */
    void disableList(List<Integer> idList);

    /**
     * 获取用户对应的角色集合和所有角色集合
     *
     * @param userId 用户id
     * @return 角色key, Name集合
     */
    RoleListAndOwnVo listRoleKeyNameByUserId(Integer userId);

    /**
     * 更新用户的角色集合
     *
     * @param userId     用户id
     * @param roleIdList 角色id集合
     */
    void updateUserRole(Integer userId, List<Integer> roleIdList);

}
