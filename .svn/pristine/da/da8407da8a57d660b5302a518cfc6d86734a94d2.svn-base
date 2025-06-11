package org.jit.sose.service.impl;

import org.jit.sose.domain.entity.UserRole;
import org.jit.sose.mapper.UserRoleMapper;
import org.jit.sose.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 小红
 * @Date 2020/7/9 17:23
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;

    @Override
    public int register(UserRole record) {
        return userRoleMapper.register(record);
    }

	@Override
	public int checkAuditNoticePermission(Integer userId) {
		return userRoleMapper.checkAuditNoticePermission(userId);
	}

	@Override
	public int checkAuditFilePermission(Integer userId) {
		return userRoleMapper.checkAuditFilePermission(userId);
	}

	@Override
	public int checkAuditArcPermission(Integer userId) {
		return userRoleMapper.checkAuditArcPermission(userId);
	}

	@Override
	public int checkAuditPersonPermission(Integer userId) {
		return userRoleMapper.checkAuditPersonPermission(userId);
	}
}
