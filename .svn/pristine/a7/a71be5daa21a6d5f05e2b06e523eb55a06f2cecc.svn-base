package org.jit.sose.service;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.ArchiveExampleFile;
import org.jit.sose.domain.vo.ListArchiveFileForAuditVo;

import java.util.List;

/**
 * @Author: LJH
 * @Date: 2020/10/16 10:08
 */

public interface ArchiveExampleFileService {
    /**
     * 批量插入个人文档-档案关联
     * @param list
     * @return
     */
    int insertList(List<ArchiveExampleFile> list);

    /**
     * 查询用户上传档案的文件及模板列表
     * @param exampleId
     * @return
     */
    List<ListArchiveFileForAuditVo> listArchiveFile(@Param("exampleId") Integer exampleId);
}
