package org.jit.sose.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.ArchiveTemProcessCon;

public interface ArchiveTemProcessConMapper {

    /**
     * 新增档案流程关联
     */
    void insertCon(@Param("archiveTemId") Integer archiveTemId, @Param("processId") Integer processId);

    /**
     * 修改档案流程关联(一对一)
     */
    void changeCon(@Param("archiveTemId") Integer archiveTemId, @Param("processId") Integer processId);

    int deleteByPrimaryKey(Integer id);

    int insertSelective(ArchiveTemProcessCon record);

    ArchiveTemProcessCon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArchiveTemProcessCon record);

    int updateByPrimaryKey(ArchiveTemProcessCon record);
}