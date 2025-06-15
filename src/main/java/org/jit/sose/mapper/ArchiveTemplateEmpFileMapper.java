package org.jit.sose.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.ArchiveTemplateEmpFile;
import org.jit.sose.domain.vo.ListArchiveFilePoolVo;
import org.jit.sose.domain.vo.ListFilePoolVo;

import java.util.List;

public interface ArchiveTemplateEmpFileMapper {

    /**
     * 批量插入模板-档案关联信息
     * @param list
     * @return
     */
    int insertList(List<ArchiveTemplateEmpFile> list);

    /**
     * 根据档案模板标识获取文档文件池
     *
     * @param archiveTemId
     * @return
     */
    List<ListFilePoolVo> getFilePool(@Param("archiveTemId") Integer archiveTemId);

    /**
     * 根据档案模板标识获取文档文件池[包含用户上传的文件]
     * @param archiveTemId
     * @return
     */
    List<ListArchiveFilePoolVo> getArchiveFilePool(@Param("archiveTemId") Integer archiveTemId);

    int deleteByPrimaryKey(Integer id);

    int insert(ArchiveTemplateEmpFile record);

    int insertSelective(ArchiveTemplateEmpFile record);

    ArchiveTemplateEmpFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArchiveTemplateEmpFile record);

    int updateByPrimaryKey(ArchiveTemplateEmpFile record);

    void insertArcAndCourse(Integer archiveTemId, Integer integer);
}