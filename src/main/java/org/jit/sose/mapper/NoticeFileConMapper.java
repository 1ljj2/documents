package org.jit.sose.mapper;

import org.jit.sose.domain.entity.NoticeFileCon;
import org.jit.sose.domain.param.AddNoticeParam;

public interface NoticeFileConMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NoticeFileCon record);

    int insertSelective(NoticeFileCon record);

    NoticeFileCon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NoticeFileCon record);

    int updateByPrimaryKey(NoticeFileCon record);

    /**
     * 新增公告和文件关联
     * @param param
     */
	void addNoticeFileCon(AddNoticeParam param);
}