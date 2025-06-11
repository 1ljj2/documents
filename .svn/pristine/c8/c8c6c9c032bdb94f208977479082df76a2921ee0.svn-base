package org.jit.sose.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.ProcessCategoryCon;

public interface ProcessCategoryConMapper {

    /**
     * 根据processId修改它的categoryId（一对一关系）
     *
     * @param processId
     * @param categoryId
     */
    void changeCon(@Param("processId") Integer processId, @Param("categoryId") Integer categoryId);

    /**
     * 新增分类流程关联
     *
     * @param category
     * @param processId
     */
    void addCon(@Param("categoryId") Integer category, @Param("processId") Integer processId);

    int deleteByPrimaryKey(Integer id);

    int insert(ProcessCategoryCon record);

    int insertSelective(ProcessCategoryCon record);

    ProcessCategoryCon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProcessCategoryCon record);

    int updateByPrimaryKey(ProcessCategoryCon record);
}