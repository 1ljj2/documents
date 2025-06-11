package org.jit.sose.service.impl;

import org.jit.sose.mapper.UserDepartmentConMapper;
import org.jit.sose.service.UserDepartmentConService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 
* @author jinyu: 
* @Date 2020年9月24日 下午5:14:48 
*  
*/
@Service
public class UserDepartmentConServiceImpl implements UserDepartmentConService {
	
	@Autowired
	private UserDepartmentConMapper userDepartmentConMapper;

	@Override
	public int selectDepartmentIdByUserId(Integer id) {
		return userDepartmentConMapper.selectDepartmentIdByUserId(id);
	}

}
 