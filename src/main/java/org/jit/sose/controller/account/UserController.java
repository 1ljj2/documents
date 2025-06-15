package org.jit.sose.controller.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.jit.sose.domain.entity.SecurityUser;
import org.jit.sose.domain.entity.User;
import org.jit.sose.domain.entity.UserInfo;
import org.jit.sose.domain.param.ListUserParam;
import org.jit.sose.domain.vo.ListAuditUserVo;
import org.jit.sose.domain.vo.ListUserVo;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.domain.vo.UserVo;
import org.jit.sose.service.UserService;
import org.jit.sose.util.FastjsonUtil;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.util.StringUtil;
import org.jit.sose.web.response.CommonRespT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@ApiResponses({ @ApiResponse(code = 200, message = "Request Success"),
		@ApiResponse(code = 401, message = "Authentication Failure"),
		@ApiResponse(code = 402, message = "Login Information Invalid"),
		@ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail") })
@Slf4j
@Api(tags = "用户接口")
@ApiSupport(order = 2, author = "wangyue")
@RestController
@RequestMapping("/account/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "根据用户id获取用户手机号")
	@PostMapping("/getPhoneByUserId")
	public CommonRespT<String> getPhoneByUserId(@RequestBody Integer id) {
		return ResponseUtil.successT(userService.getPhoneByUserId(id));
	}

	@ApiOperation(value = "根据条件查询审核人用户列表", notes = "根据【所属部门、姓名、电话号码、分页】条件查询用户列表")
	@PostMapping(value = "/listAuditUser")
	public CommonRespT<PageInfoVo<ListUserVo>> listAuditUser(@RequestBody ListUserParam param) {
		PageInfoVo<ListUserVo> pageInfoVo = userService.listAuditUser(param);
		return ResponseUtil.successT(pageInfoVo);
	}
	
	@ApiOperation(value = "获取审核角色是公告审核人的user集合")
    @GetMapping("/listAuditUser")
    public CommonRespT<List<ListAuditUserVo>> listAuditUser() {
        //公告审核角色的role_key是ROLE_AUDIT_NOTICE
        String roleKey = "ROLE_AUDIT_NOTICE";
        List<ListAuditUserVo> auditUserList = userService.listAuditUserByRoleKey(roleKey);
        return ResponseUtil.successT(auditUserList);
    }
	
	@ApiOperation(value = "根据用户nameList模糊查询用户id集合")
	@ApiOperationSupport(order = 2,
			params = @DynamicParameters(properties = {
					@DynamicParameter(name = "userNameList", value = "用户nameList", example = "body", required = true, dataTypeClass = String.class)}))
	@PostMapping("/translateNameToId")
	public CommonRespT<List<Integer>> translateNameToId(@RequestBody JSONObject json) {
		JSONArray jsonArray = json.getJSONArray("userNameList");
		List<String> userNameList = FastjsonUtil.convertJSONArrayToTypeList(jsonArray, String.class);
		return ResponseUtil.successT(userService.translateNameToId(userNameList));
	}

	@ApiOperation(value = "根据用户idList模糊查询用户名称集合")
	@ApiOperationSupport(author = "jinyu", params = @DynamicParameters(properties = {
			@DynamicParameter(name = "userIdList", value = "用户idList", example = "body", required = true, dataTypeClass = Integer.class) }))
	@PostMapping("/showUserSelected")
	public CommonRespT<List<String>> showUserSelected(@RequestBody JSONObject json) {
		JSONArray jsonArray = json.getJSONArray("userIdList");
		List<Integer> userIdList = FastjsonUtil.convertJSONArrayToTypeList(jsonArray, Integer.class);
		return ResponseUtil.successT(userService.showUserSelected(userIdList));
	}

	@ApiOperation(value = "获取用户基础信息", notes = "获取用户id和userName，必须先登录")
	@ApiOperationSupport(order = 1, responses = @DynamicResponseParameters(properties = {
			@DynamicParameter(value = "状态码", name = "code"), @DynamicParameter(value = "返回信息", name = "msg"),
			@DynamicParameter(value = "返回参数对象", name = "obj", dataTypeClass = User.class) }))
	@GetMapping("/getCurrentUser")
	public User getCurrentUser(@ApiIgnore Authentication authentication) {
		SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
		User user = new User();
		user.setId(securityUser.getId());
		user.setUserName(securityUser.getUserName());
		return user;
	}

	@ApiOperation(value = "获取用户权限信息", notes = "使用@AuthenticationPrincipal注解，必须先登录")
	@ApiOperationSupport(order = 2, params = @DynamicParameters(), responses = @DynamicResponseParameters(properties = {
			@DynamicParameter(value = "状态码", name = "code"), @DynamicParameter(value = "返回信息", name = "msg"),
			@DynamicParameter(value = "返回参数对象", name = "obj", dataTypeClass = SecurityUser.class) }))
	@GetMapping("/getSecurityUser")
	public SecurityUser getSecurityUser(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser) {
		Set<String> roles = null;
		Set<String> urls = null;
		try {
			// 通过用户id和用户名查询角色集合
			roles = userService.getRoles(securityUser);
			// 根据role 从redis中获取其所有路径权限集合
			urls = userService.getPermissionURIs(roles);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		securityUser.setRoles(roles);
		securityUser.setPermissionURIs(urls);
		// 设置Security角色
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		roles.forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role));
		});
		securityUser.setAuthorities(authorities);

		log.debug("securityUser" + securityUser);
		return securityUser;
	}

	@ApiOperation(value = "查询用户信息分页列表")
	@ApiOperationSupport(order = 3, params = @DynamicParameters(properties = {
			@DynamicParameter(name = "userName", value = "用户名"),
			@DynamicParameter(name = "pageNum", value = "当前页码", required = true, example = "1"),
			@DynamicParameter(name = "pageSize", value = "每页条数", required = true, example = "10") }))
	@PostMapping(value = "/selectPageInfo")
	public CommonRespT<PageInfoVo<UserVo>> selectPageInfo(@RequestBody JSONObject json) {
		int pageNum = json.getIntValue("pageNum");
		int pageSize = json.getIntValue("pageSize");
		String userName = StringUtil.isEmpty(json.getString("userName")) ? null : json.getString("userName");
		PageInfoVo<UserVo> pageInfoVo = userService.selectPageInfo(userName, pageNum, pageSize);
		return ResponseUtil.successT(pageInfoVo);
	}

	@ApiOperation(value = "根据用户名称模糊查询用户对象集合")
	@ApiOperationSupport(order = 1, params = @DynamicParameters(properties = {
			@DynamicParameter(name = "userName", value = "用户名称搜索值", example = "body", required = true, dataTypeClass = String.class) }))
	@PostMapping("/listUserByName")
	public CommonRespT<List<ListUserVo>> listUserByName(@RequestBody JSONObject json) {
		String userName = json.getString("userName");
		return ResponseUtil.successT(userService.listUserByName(userName));
	}

	@ApiOperation(value = "查询待审核用户信息分页列表")
	@ApiOperationSupport(order = 3, params = @DynamicParameters(properties = {
			@DynamicParameter(name = "pageNum", value = "当前页码", required = true, example = "1"),
			@DynamicParameter(name = "pageSize", value = "每页条数", required = true, example = "10") }))
	@PostMapping(value = "/selectAuditPeoplePageInfo")
	public CommonRespT<PageInfoVo<UserInfo>> selectAuditPeoplePageInfo(@RequestBody JSONObject json) {
		int pageNum = json.getIntValue("pageNum");
		int pageSize = json.getIntValue("pageSize");
		PageInfoVo<UserInfo> pageInfoVo = userService.selectAuditPeoplePageInfo(pageNum, pageSize);
		return ResponseUtil.successT(pageInfoVo);
	}

	@ApiOperation(value = "审核微信注册的人员")
	@PostMapping("/auditWxRegisterPeople")
	public void auditWxRegisterPeople(@RequestBody JSONObject jsonObject){
		int id = jsonObject.getInteger("id");
		userService.auditWxRegisterPeople(id);
	}
}
