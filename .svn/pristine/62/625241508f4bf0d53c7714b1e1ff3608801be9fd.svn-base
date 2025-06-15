package org.jit.sose.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.PermissionsType;

import java.util.List;

public interface PermissionsTypeMapper extends BaseMapper<PermissionsType> {

    /**
     * 获取所有的请求类型对象
     * @return
     */
    List<PermissionsType> listType();

    /**
     * 根据id查询请求类型
     *
     * @param id
     * @return
     */
    String showPermissionsType(Integer id);

    /**
     * 获取所有的请求类型名称
     *
     * @return 求类型名称集合
     */
    List<String> getTypeName();

    /**
     * 根据请求权限id 看请求类型详情
     *
     * @param permissionId
     * @return
     */
    PermissionsType viewTypeByPermissionId(Integer permissionId);

    /**
     * 根据类型名称查询类型id
     *
     * @param typeName
     * @return
     */
    Integer selectIdByTypeName(@Param("typeName") String typeName);

    int deleteByPrimaryKey(Integer id);

    /**
     * 插入
     */
    int insert(PermissionsType record);

    PermissionsType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PermissionsType record);

    int updateByPrimaryKey(PermissionsType record);
}