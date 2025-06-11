package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.domain.entity.MessReadUser;
import org.jit.sose.domain.vo.ListMessVo;
import org.jit.sose.domain.vo.ListReadUserByMess;

public interface MessReadUserMapper {

	/**
	 * 我的提醒页面根据登录用户查询相应的消息
	 * @param vo
	 * @return
	 */
	int newMessCount(ListReadUserByMess vo);
	
	/**
	 * 设置用户消息已读
	 * @param data
	 */
	void isRead(MessReadUser data);
	
	/**
	 * 把userId对应的messId状态置为"X"
	 * @param ruId
	 * @param messId 
	 */
	void setXByUserId(MessReadUser mr);
	/**
	 * 添加消息面向用户关联
	 * @param messId
	 * @param readUserIdList
	 */
	void addConList(Integer messId, List<Integer> readUserIdList);
	
	/**
	 * 把messId对应的userId状态置为"X"
	 * @param messId
	 */
	void setXByMessId(int messId);
	
    int deleteByPrimaryKey(Integer id);

    int insert(MessReadUser record);

    int insertSelective(MessReadUser record);

    MessReadUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessReadUser record);

    int updateByPrimaryKey(MessReadUser record);
	
}