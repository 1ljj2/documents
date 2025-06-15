package org.jit.sose.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.FileCourseCon;

public interface FileCourseConMapper extends BaseMapper<FileCourseCon> {

    /**
     * 修改文档课程关联(一对一)
     *
     * @param fileId
     * @param courseId
     */
    void changeCon(@Param("fileId") Integer fileId, @Param("courseId") Integer courseId);

    int deleteByPrimaryKey(Integer id);

//    int insert(FileCourseCon record);

    int insertSelective(FileCourseCon record);

    FileCourseCon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FileCourseCon record);

    int updateByPrimaryKey(FileCourseCon record);

    int selectByFileIdAndCourseId(Integer fileId, Integer integer);
}