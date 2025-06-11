package org.jit.sose.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.FileCategoryCon;

public interface FileCategoryConMapper extends BaseMapper<FileCategoryCon> {

    /**
     * 修改文档分类关联(一对一)
     *
     * @param fileId
     * @param categoryId
     */
    void changeCon(@Param("fileId") Integer fileId, @Param("categoryId") Integer categoryId);

    int deleteByPrimaryKey(Integer id);

//    int insert(FileCategoryCon record);

    int insertSelective(FileCategoryCon record);

    FileCategoryCon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileCategoryCon record);

    int updateByPrimaryKey(FileCategoryCon record);
}