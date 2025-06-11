package org.jit.sose.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.ArchiveTemplate;
import org.jit.sose.domain.entity.CourseInfo;
import org.jit.sose.domain.param.ListArchiveTemParam;
import org.jit.sose.domain.vo.ListArchiveTemVo;

import java.util.List;

public interface ArchiveTemplateMapper {

    /**
     * 新增【档案模板】
     *
     * @param archiveTemplate
     */
    void addArchiveTem(ArchiveTemplate archiveTemplate);

    /**
     * 编辑【档案模板】
     */
    void editArchiveTem(ArchiveTemplate archiveTemplate);

    /**
     * 根据【档案模板名称、学期、分类、分页参数】筛选档案模板列表对象
     *
     * @param param
     * @return
     */
    List<ListArchiveTemVo> listArchiveTemByCondition(ListArchiveTemParam param);

    List<ListArchiveTemVo> listArchiveTemByCondition1(ListArchiveTemParam param);

    List<ListArchiveTemVo> listArchiveTemByCondition2(ListArchiveTemParam param);

    List<ListArchiveTemVo> listArchiveTemByCondition3(ListArchiveTemParam param);

    int deleteByPrimaryKey(Integer id);

    int insert(ArchiveTemplate record);

    ArchiveTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(ArchiveTemplate record);

    int[] listArchiveTemOfCourseId(@Param("userId") Integer userId);


    List<CourseInfo> selectCourseByArcId(Integer id);

    void deleteCon(Integer id);

    void addNewCon(Integer id, Integer integer1);
}