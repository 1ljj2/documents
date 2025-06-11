package org.jit.sose.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jit.sose.domain.entity.MenuBackPermissions;

public interface MenuBackPermissionsMapper extends BaseMapper<MenuBackPermissions> {
    /**
     * 添加或更新
     *
     * @param record MenuBackPermissions
     */
    void insertOrUpdate(MenuBackPermissions record);

    int deleteByPrimaryKey(Integer id);

    MenuBackPermissions selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MenuBackPermissions record);

    int updateByPrimaryKey(MenuBackPermissions record);
}