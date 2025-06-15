package org.jit.sose.controller.remind;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jit.sose.domain.entity.SecurityUser;
import org.jit.sose.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/** 
* @author jinyu: 
* @Date 2020年10月21日 下午2:06:12 
*  
*/
@Api(tags = "我的提醒接口")
@ApiSupport(author = "jinyu")
@RestController
@RequestMapping("/myRemind")
public class MyRemindController {
	
	@Autowired
	private UserRoleService userRoleService;
	
	@ApiOperation(value = "判断这个用户是否有档案审核权限")
    @ApiOperationSupport(order = 3)
    @GetMapping("/checkAuditArcPermission")
    public int checkAuditArcPermission(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser) {
		Integer userId = securityUser.getId();//获取用户信息userId
        return userRoleService.checkAuditArcPermission(userId);
    }
	
	@ApiOperation(value = "判断这个用户是否有文档审核权限")
    @ApiOperationSupport(order = 2)
    @GetMapping("/checkAuditFilePermission")
    public int checkAuditFilePermission(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser) {
		Integer userId = securityUser.getId();//获取用户信息userId
        return userRoleService.checkAuditFilePermission(userId);
    }
	
	@ApiOperation(value = "判断这个用户是否有公告审核权限")
    @ApiOperationSupport(order = 1)
    @GetMapping("/checkAuditNoticePermission")
    public int checkAuditNoticePermission(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser) {
		Integer userId = securityUser.getId();//获取用户信息userId
        return userRoleService.checkAuditNoticePermission(userId);
    }


    @ApiOperation(value = "判断这个用户是否有人员审核权限")
    @ApiOperationSupport(order = 4)
    @GetMapping("/checkAuditPersonPermission")
    public int checkAuditPersonPermission(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser) {
        Integer userId = securityUser.getId();//获取用户信息userId
        return userRoleService.checkAuditPersonPermission(userId);
    }

}
 