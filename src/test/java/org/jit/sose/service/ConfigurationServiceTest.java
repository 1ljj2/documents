package org.jit.sose.service;

import org.jit.sose.TestAppDocument;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** 
* @author jinyu: 
* @Date 2020年10月21日 下午1:05:15 
*  
*/
public class ConfigurationServiceTest extends TestAppDocument{
	@Autowired
	private ConfigurationService configurationService;
	
	@Test
	public void TestCheckMessage4Audit() {
		Boolean enable = configurationService.checkMessage4Audit();
		System.out.println(enable);
	}

}
 