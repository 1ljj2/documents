package org.jit.sose.mapper;

import java.util.List;

import org.jit.sose.domain.entity.MessRoleCon;

public interface MessRoleConMapper {
	
	/**
	 * 添加消息面向角色关联
	 * @param messId
	 * @param roleIdList
	 */
	void addConList(Integer messId, List<Integer> roleIdList);
	
	/**
	 * 把messId对应的userId状态置为"X"
	 * @param messId
	 */
	void setXByMessId(int messId);
	
    int deleteByPrimaryKey(Integer id);

    int insert(MessRoleCon record);

    int insertSelective(MessRoleCon record);

    MessRoleCon selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MessRoleCon record);

    int updateByPrimaryKey(MessRoleCon record);
}