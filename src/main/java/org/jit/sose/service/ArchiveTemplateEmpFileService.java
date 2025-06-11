package org.jit.sose.service;

import org.jit.sose.domain.entity.ArchiveTemplateEmpFile;
import org.jit.sose.domain.vo.ListArchiveFilePoolVo;
import org.jit.sose.domain.vo.ListFilePoolVo;
import org.jit.sose.domain.vo.PageInfoVo;

import java.util.List;

/**
 * @author wufang
 * @Date 2020-10-08 16:08:13
 */
public interface ArchiveTemplateEmpFileService {

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
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfoVo<ListFilePoolVo> getFilePool(Integer archiveTemId, Integer pageNum, Integer pageSize);

    /**
     * 根据档案模板标识获取文档文件池[包含用户上传的文件]
     *
     * @param archiveTemId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfoVo<ListArchiveFilePoolVo> getArchiveFilePool(Integer archiveTemId, Integer pageNum, Integer pageSize);

}
