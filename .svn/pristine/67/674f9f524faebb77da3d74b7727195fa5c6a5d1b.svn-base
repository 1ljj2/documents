package org.jit.sose.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.ArchiveExampleFile;
import org.jit.sose.domain.param.ListChargingArchiveParam;
import org.jit.sose.domain.vo.ListArchiveFileForAuditVo;
import org.jit.sose.domain.vo.ListArchiveFileVo;

import java.util.List;

public interface ArchiveExampleFileMapper {

	/**
	 * 根据【绑定id、分页参数】筛选文件列表对象
	 * @param param
	 * @return
	 */
	List<ListArchiveFileVo> listArchiveFileByCondition(ListChargingArchiveParam param);

    /**
     * 查询用户上传档案的文件及模板列表
     * @param exampleId
     * @return
     */
    List<ListArchiveFileForAuditVo> listArchiveFile(@Param("exampleId") Integer exampleId);
	
    /**
     * 批量插入个人文档-档案关联
     * @param list
     * @return
     */
    int insertList(List<ArchiveExampleFile> list);

    /**
     * 插入个人文档-档案关联
     * @param record
     * @return
     */
    int insertSelective(ArchiveExampleFile record);

    int deleteByPrimaryKey(Integer id);

//    int insertSelective(ArchiveExampleFile record);

    ArchiveExampleFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArchiveExampleFile record);

    int updateByPrimaryKey(ArchiveExampleFile record);
}