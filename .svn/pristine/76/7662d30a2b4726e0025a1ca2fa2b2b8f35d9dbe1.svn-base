package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.domain.entity.Notice;
import org.jit.sose.domain.param.AddNoticeAuditParam;
import org.jit.sose.domain.param.ListNoticeParam;
import org.jit.sose.domain.vo.ListMessVo;
import org.jit.sose.domain.vo.ListNoticeVo;

public interface NoticeMapper {
	/**
	 * 设置过期的公告seq为1
	 * @param id
	 */
	void setOutTimeSeq();

	/**
	 * 查询待审核的公告个数
	 * @param userId
	 * @return
	 */
	int newNoticeToAuditCount(Integer userId);
	
	/**
	 * 筛选我的提醒待审核公告
	 * @param param
	 * @return
	 */
	List<ListNoticeVo> listAuditNoticeMyRemind(ListNoticeParam param);
	
	/***
	 * 查询新的公告个数
	 * @return
	 */
	int newNoticeCount();
	/**
	 * 设置公告待审核
	 * @param noticeId
	 */
	void setNoticeInAudit(Integer noticeId);
	/**
	 * 根据【标题、发布部门、发布用户、分类、分页参数】筛选公告审核人对应的列表对象
	 * @param param
	 * @return
	 */
	List<ListNoticeVo> listAuditNotice(ListNoticeParam param);
	
	/**
	 * 通过公告审核
	 * @param id
	 */
	void checkAuditTrue(Integer id);
	
    /**
     * 	在我的提醒页面公告
     * @param param
     * @return
     */
	List<ListNoticeVo> listNoticeMyRemind(ListNoticeParam param);
	
	/**
	 * 置顶公告
	 * @param id
	 */
	void setTopNotice(Integer id);

	/**
	 * 禁用公告
	 * @param id
	 */
	void disableNotice(Integer id);
	
	/**
	 * 编辑公告
	 * @param notice
	 */
	void editNotice(Notice notice);
	
	/**
	 * 新增公告
	 * @param notice
	 */
	void addNotice(Notice notice);
	
	/**
	 * 删除单个公告
	 * 
	 * @param id
	 */
	void removeNotice(Integer id);
	
	/**
     * 批量删除公告
     *
     * @param idList
     */
	void removeNoticeSelect(List<Integer> idList);

	/**
	 * 公告查询
	 * @param param
	 * @return
	 */
	List<ListNoticeVo> listNotice(ListNoticeParam param);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKey(Notice record);

}