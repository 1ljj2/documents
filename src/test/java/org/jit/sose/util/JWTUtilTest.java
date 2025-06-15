package org.jit.sose.util;

import org.jit.sose.TestAppDocument;
import org.junit.Test;

public class JWTUtilTest extends TestAppDocument {

	private String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6bnVsbCwiaXNzIjoid2FuZ3l1ZSIsInVzZXJOYW1lIjpudWxsLCJleHAiOjE1ODQ0NjUzOTksInVzZXJJZCI6bnVsbCwiaWF0IjoxNTg0MjkyNTk5fQ.R6DDcZH23Y2oFIRQRGqV3pRw01kfb-UirBgBtHM8ZHM";

	@Test
	public void test() {
		String userName = JWTUtil.getUsername(token);
		System.out.println(userName);
	}
}
