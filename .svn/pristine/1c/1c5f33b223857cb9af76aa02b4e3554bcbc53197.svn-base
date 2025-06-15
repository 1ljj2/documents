package org.jit.sose.service;

import org.jit.sose.domain.param.ForReviewToNextParam;
import org.jit.sose.domain.vo.ListAuditFileVo;
import org.jit.sose.domain.vo.PageInfoVo;

/**
 * @author wufang
 * @Date 2020-10-16 10:44:15
 */
public interface FileAuditService {

    /**
     * 文档审核送审下一个人审核
     *
     * @param param
     */
    void forReviewToNext(ForReviewToNextParam param) throws Exception;

    /**
     * 根据当前用户标识获取自身需要审核的文档
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfoVo<ListAuditFileVo> listAuditFileMyRemind(Integer userId, Integer pageNum, Integer pageSize);

    /**
     * 根据【流程步骤、审核人、文档标识】进行流程运转-送审
     */
    void myFileForReview(Integer processId, Integer stepId, Integer userId, Integer fileId);

    int getProcessIdByFileId(Integer fileId);
}
