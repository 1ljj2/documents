package org.jit.sose.service;

import org.jit.sose.domain.entity.ArchiveAudit;
import org.jit.sose.domain.param.ForReviewArcToNextParam;
import org.jit.sose.domain.param.MyArchiveForReviewParam;
import org.jit.sose.domain.vo.ListAuditArchiveVo;
import org.jit.sose.domain.vo.PageInfoVo;

import java.util.Map;

/**
 * @Author: LJH
 * @Date: 2020/10/17 16:20
 */

public interface ArchiveAuditService {

    /**
     * 文档审核送审下一个人审核
     *
     * @param param
     */
    void forReviewArcToNext(ForReviewArcToNextParam param);


    /**
     * 获取档案审核模态框所需数据
     */
    Map<String, Object> getAuditArchiveModalInfo(Integer exampleId, Integer processId, Integer nowStepId);

    /**
     * 根据当前用户标识获取自身需要审核的档案
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfoVo<ListAuditArchiveVo> listAuditArchiveMyRemind(Integer userId, Integer pageNum, Integer pageSize);

    int insertSelective(ArchiveAudit record);

    int myArchiveForReview(MyArchiveForReviewParam param);
}
