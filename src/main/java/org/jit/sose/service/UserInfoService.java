package org.jit.sose.service;

import org.jit.sose.domain.entity.UserInfo;

/**
 * @author 小红
 * @Date 2020/7/9 18:44
 */
public interface UserInfoService {

    /**
     * 注册
     * @param record
     * @return
     */
    int register(UserInfo record);

    void wxregister(UserInfo wxUserInfo);

    void wxAddDepartment(Integer userId, Integer departId);
}
