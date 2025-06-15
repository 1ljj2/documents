package org.jit.sose.mapper;

import org.jit.sose.TestAppDocument;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** 
* @author jinyu: 
* @Date 2020年10月21日 下午2:29:53 
*  
*/
public class UserRoleMapperTest extends TestAppDocument{
	
	@Autowired
    UserRoleMapper userRoleMapper;
	
	@Test
	public void TestCheckAuditNoticePermission() {
		int flag = userRoleMapper.checkAuditNoticePermission(3);
		System.out.println(flag);
	}
	
	@Test
	public void TestCheckAuditArcPermission() {
		int flag = userRoleMapper.checkAuditArcPermission(1);
		System.out.println(flag);
	}
	
	@Test
	public void TestCheckAuditFilePermission() {
		int flag = userRoleMapper.checkAuditFilePermission(1);
		System.out.println(flag);
	}

}
 