package org.jit.sose.mapper;

import org.apache.ibatis.annotations.Param;
import org.jit.sose.domain.entity.UserInfo;

public interface UserInfoMapper {

    /**
     * 注册
     * @param record
     * @return
     */
    int register(UserInfo record);

    int deleteByPrimaryKey(Integer id);

    int insert(UserInfo record);

    UserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    void wxregister(UserInfo wxUserInfo);

    void wxAddDepartment(@Param("userId") Integer userId,@Param("departmentId") Integer departId);
}