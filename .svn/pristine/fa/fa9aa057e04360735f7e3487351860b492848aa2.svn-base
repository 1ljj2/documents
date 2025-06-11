package org.jit.sose.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.UserRole;

import java.util.List;

public interface UserRoleMapper extends BaseMapper<UserRole> {
	/**
	 *  判断这个用户是否有档案审核权限
	 * @param userId
	 * @return
	 */
	int checkAuditArcPermission(Integer userId);
	/**
	 * 判断这个用户是否有文档审核权限
	 * @param userId
	 * @return
	 */
	int checkAuditFilePermission(Integer userId);
	/**
	 * 判断这个用户是否有公告审核权限
	 * @param userId
	 * @return
	 */
	int checkAuditNoticePermission(Integer userId);
	/**
	 * 根据一个用户id查出所有的userId集合
	 * @param rId
	 * @return
	 */
	List<Integer> selectUserIdByRole(Integer rId);
	
	/**
	 * 注册时用户选择的基本角色
	 * 
	 * @param record
	 * @return
	 */
	int register(UserRole record);

	/**
	 * 批量更新或插入
	 *
	 * @param userId     用户id
	 * @param roleIdList 角色id集合
	 */
	void insertOrUpdateList(@Param("userId") Integer userId, @Param("roleIdList") List<Integer> roleIdList);

	/**
	 * 批量更新或插入
	 *
	 * @param userId     用户id
	 * @param roleIdList 角色id集合
	 */
	void deleteList(@Param("userId") Integer userId, @Param("roleIdList") List<Integer> roleIdList);

	int deleteByPrimaryKey(Integer id);

	int insert(UserRole record);

	int insertSelective(UserRole record);

	UserRole selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(UserRole record);

	int updateByPrimaryKey(UserRole record);

    int checkAuditPersonPermission(@Param("userId") Integer userId);
}