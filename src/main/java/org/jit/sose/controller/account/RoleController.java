package org.jit.sose.controller.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import io.swagger.annotations.*;
import org.jit.sose.domain.entity.Role;
import org.jit.sose.domain.param.ListMessParam;
import org.jit.sose.domain.param.UpdateUserRoleParam;
import org.jit.sose.domain.vo.ListMessVo;
import org.jit.sose.domain.vo.ListRoleVo;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.domain.vo.RoleListAndOwnVo;
import org.jit.sose.service.RoleService;
import org.jit.sose.util.FastjsonUtil;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.util.StringUtil;
import org.jit.sose.web.response.CommonResp;
import org.jit.sose.web.response.CommonRespT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Api(tags = "角色接口")
@ApiSupport(order = 3, author = "wangyue")
@ApiResponses({ @ApiResponse(code = 200, message = "success"),
		@ApiResponse(code = 401, message = "Authentication Failure"),
		@ApiResponse(code = 402, message = "Login Information Invalid"),
		@ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 420, message = "Data Error"),
		@ApiResponse(code = 421, message = "Cache Error"), @ApiResponse(code = 500, message = "Request fail") })
@RestController
@RequestMapping("/account/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@ApiOperation(value = "根据角色nameList模糊查询角色id集合")
	@ApiOperationSupport(order = 2,
			params = @DynamicParameters(properties = {
					@DynamicParameter(name = "roleNameList", value = "角色nameList", example = "body", required = true, dataTypeClass = String.class)}))
	@PostMapping("/translateNameToId")
	public CommonRespT<List<Integer>> translateNameToId(@RequestBody JSONObject json) {
		JSONArray jsonArray = json.getJSONArray("roleNameList");
		List<String> roleNameList = FastjsonUtil.convertJSONArrayToTypeList(jsonArray, String.class);
		return ResponseUtil.successT(roleService.translateNameToId(roleNameList));
	}

	@ApiOperation(value = "根据角色idList模糊查询角色名称集合")
	@ApiOperationSupport(author = "jinyu", params = @DynamicParameters(properties = {
			@DynamicParameter(name = "roleIdList", value = "角色idList", example = "body", required = true, dataTypeClass = Integer.class) }))
	@PostMapping("/showRoleSelected")
	public CommonRespT<List<String>> showRoleSelected(@RequestBody JSONObject json) {
//		Integer roleId = json.getInteger("roleId");
		JSONArray jsonArray = json.getJSONArray("roleIdList");
		List<Integer> roleIdList = FastjsonUtil.convertJSONArrayToTypeList(jsonArray, Integer.class);
//		List<Integer> roleIdList = new ArrayList<>();
//		roleIdList.add(roleId);
		return ResponseUtil.successT(roleService.showRoleSelected(roleIdList));
	}

	@ApiOperation(value = "过滤查询角色集合", notes = "根据角色key、角色名称过滤查询")
	@ApiOperationSupport(order = 1, params = @DynamicParameters(properties = {
			@DynamicParameter(name = "tableName", value = "角色key", required = true, example = "ROLE_ADMIN"),
			@DynamicParameter(name = "colmName", value = "角色名称", required = true, example = "管理"),
			@DynamicParameter(name = "pageNum", value = "当前页码", required = true, dataTypeClass = Integer.class, example = "1"),
			@DynamicParameter(name = "pageSize", value = "每页条数", required = true, dataTypeClass = Integer.class, example = "10") }))
	@PostMapping("/listPageInfo")
	public CommonRespT<PageInfoVo<Role>> listPageInfo(@RequestBody JSONObject json) {
		Role role = new Role();
		// 过滤查询条件
		String roleKey = json.getString("roleKey");
		String roleName = json.getString("roleName");
		role.setRoleKey(StringUtil.isEmpty(roleKey) ? null : roleKey);
		role.setRoleName(StringUtil.isEmpty(roleName) ? null : roleName);
		// 当前页码
		Integer pageNum = json.getIntValue("pageNum");
		// 每页条数
		Integer pageSize = json.getIntValue("pageSize");
		PageInfoVo<Role> pageInfoVo = roleService.listPageInfo(role, pageNum, pageSize);
		return ResponseUtil.successT(pageInfoVo);
	}

	@ApiOperation(value = "插入角色", response = CommonResp.class)
	@ApiOperationSupport(order = 2)
	@PostMapping("/insert")
	public void insert(@RequestBody Role role) {
		// 检查Role数据格式
		roleService.insert(role);
	}

	@ApiOperation(value = "更新角色", response = CommonResp.class)
	@ApiOperationSupport(order = 3)
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public void update(@RequestBody Role role) {
		roleService.update(role);
	}

	@ApiOperation(value = "批量删除角色", response = CommonResp.class)
	@ApiOperationSupport(order = 4)
	@ApiImplicitParam(name = "idList", value = "主键集合", required = true, allowMultiple = true, dataTypeClass = Integer.class)
	@PostMapping(value = "/deleteList")
	public void deleteList(@RequestBody List<Integer> idList) {
		roleService.deleteList(idList);
	}

	@ApiOperation(value = "批量禁用角色", response = CommonResp.class)
	@ApiOperationSupport(order = 5)
	@ApiImplicitParam(name = "idList", value = "主键集合", required = true, allowMultiple = true, dataTypeClass = Integer.class)
	@PostMapping(value = "/disableList")
	public void disableList(@RequestBody List<Integer> idList) {
		roleService.disableList(idList);
	}

	@ApiOperation(value = "获取用户对应的角色集合和所有角色集合")
	@ApiOperationSupport(order = 6)
	@PostMapping("/listByUserId")
	public CommonRespT<RoleListAndOwnVo> listByUserId(@RequestBody Integer userId) {
		RoleListAndOwnVo roleListAndOwnVo = roleService.listRoleKeyNameByUserId(userId);
		return ResponseUtil.successT(roleListAndOwnVo);
	}

	@ApiOperation(value = "更新用户的角色集合", response = CommonResp.class)
	@ApiOperationSupport(order = 7)
	@PostMapping("/updateUserRole")
	public void updateUserRole(@RequestBody @Validated UpdateUserRoleParam param) {
		roleService.updateUserRole(param.getUserId(), param.getRoleIdList());
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code = 401, message = "Authentication Failure"),
			@ApiResponse(code = 402, message = "Login Information Invalid"),
			@ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail") })
	@ApiOperation(value = "根据roleName查询角色对象集合")
	@ApiOperationSupport(params = @DynamicParameters(properties = {
			@DynamicParameter(name = "roleName", value = "角色名称搜索值", example = "body", required = true, dataTypeClass = String.class) }))
	@PostMapping(value = "/listRoleByName")
	public CommonRespT<List<ListRoleVo>> listRoleByName(@RequestBody JSONObject json) {
		String roleName = json.getString("roleName");
		return ResponseUtil.successT(roleService.listRoleByName(roleName));
	}

	@ApiResponses({ @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code = 401, message = "Authentication Failure"),
			@ApiResponse(code = 402, message = "Login Information Invalid"),
			@ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail") })
	@ApiOperation(value = "根据id查询角色对象")
	@ApiOperationSupport(params = @DynamicParameters(properties = {
			@DynamicParameter(name = "id", value = "角色id搜索值", example = "body", required = true, dataTypeClass = String.class) }))
	@PostMapping(value = "/listRoleNameById")
	public List<ListRoleVo> listRoleNameById(@RequestBody Integer id) {
//		Integer id = json.getInteger("id");
		return roleService.listRoleNameById(id);
	}
}
