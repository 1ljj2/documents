package org.jit.sose.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.FileTermCon;

public interface FileTermConMapper extends BaseMapper<FileTermCon> {

    /**
     * 修改文档学期关联(一对一)
     *
     * @param fileId
     * @param termId
     */
    void changeCon(@Param("fileId") Integer fileId, @Param("termId") Integer termId);

    int deleteByPrimaryKey(Integer id);

//    int insert(FileTermCon record);

    int insertSelective(FileTermCon record);

    FileTermCon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileTermCon record);

    int updateByPrimaryKey(FileTermCon record);
}