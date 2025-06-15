package org.jit.sose.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.ArchiveTemCategoryCon;

public interface ArchiveTemCategoryConMapper {

    /**
     * 新增档案模板分类关联
     */
    void insertCon(@Param("archiveTemId") Integer archiveTemId, @Param("categoryId") Integer categoryId);

    /**
     * 修改档案模板分类关联(一对一)
     */
    void changeCon(@Param("archiveTemId") Integer archiveTemId, @Param("categoryId") Integer categoryId);

    int deleteByPrimaryKey(Integer id);

    int insert(ArchiveTemCategoryCon record);

    int insertSelective(ArchiveTemCategoryCon record);

    ArchiveTemCategoryCon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArchiveTemCategoryCon record);

    int updateByPrimaryKey(ArchiveTemCategoryCon record);
}