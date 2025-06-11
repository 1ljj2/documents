package org.jit.sose.mapper;

import org.jit.sose.TestAppDocument;
import org.jit.sose.domain.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends TestAppDocument {

	@Autowired
	UserMapper userMapper;

	@Test
	public void listRoleKeyByUserId() {
		String userName = "lisi";
		User user = userMapper.selectByUserName(userName);
		System.out.println(user);
		user.getRoles().forEach(role -> {
			System.out.println(role);
		});
	}

}
