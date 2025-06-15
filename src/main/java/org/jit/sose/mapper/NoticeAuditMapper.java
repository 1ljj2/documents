package org.jit.sose.mapper;

import org.jit.sose.domain.entity.NoticeAudit;
import org.jit.sose.domain.param.AddNoticeAuditParam;

public interface NoticeAuditMapper {

	/**
	 * 添加公告和审核人的绑定
	 * @param addNoticeAuditParam
	 */
	void addAuditNoticeCon(AddNoticeAuditParam addNoticeAuditParam);
	
    int insert(NoticeAudit record);

    int insertSelective(NoticeAudit record);
}