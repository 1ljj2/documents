package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.domain.entity.Mess;
import org.jit.sose.domain.param.ListMessParam;
import org.jit.sose.domain.vo.ListMessVo;

public interface MessMapper {

	/**
	 * 我的提醒页面根据登录用户查询相应的消息
	 * @param param
	 * @return
	 */
	List<ListMessVo> listMessMyRemind(ListMessParam param);
	
	/**
	 * 新增消息
	 * @param mess
	 */
	void addMess(Mess mess);
	
	/**
	 * 编辑消息
	 * @param mess
	 */
	void editMess(Mess mess);
	
	/**
	 * 删除单个消息
	 * @param id
	 */
	void removeMess(Integer id);

	/**
	 * 批量删除消息
	 * @param idList
	 */
	void removeMessSelect(List<Integer> idList);

	/**
	 * 根据【标题、发布部门、发布用户、分类、分页参数】筛选消息列表对象
	 * @param param
	 * @return
	 */
	List<ListMessVo> listMess(ListMessParam param);
	
    int deleteByPrimaryKey(Integer id);

    int insert(Mess record);

    int insertSelective(Mess record);

    Mess selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Mess record);

    int updateByPrimaryKey(Mess record);

}