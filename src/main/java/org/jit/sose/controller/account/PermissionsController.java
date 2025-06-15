package org.jit.sose.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.xiaoymin.knife4j.annotations.*;
import io.swagger.annotations.*;
import org.jit.sose.domain.entity.MenuBackPermissions;
import org.jit.sose.domain.entity.Permissions;
import org.jit.sose.domain.param.ListPageInfoParam;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.domain.vo.PermissionsVo;
import org.jit.sose.service.PermissionsService;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.util.StringUtil;
import org.jit.sose.web.exception.DataFormatException;
import org.jit.sose.web.response.CommonResp;
import org.jit.sose.web.response.CommonRespT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 权限接口
 *
 * @author wangyue
 * @date 2020-06-08 12:22
 */
@Api(tags = "权限接口")
@ApiSupport(order = 5, author = "wangyue")
@ApiResponses({ @ApiResponse(code = 200, message = "success"),
		@ApiResponse(code = 401, message = "Authentication Failure"),
		@ApiResponse(code = 402, message = "Login Information Invalid"),
		@ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 420, message = "Data Error"),
		@ApiResponse(code = 421, message = "Cache Error"), @ApiResponse(code = 500, message = "Request fail") })
@RestController
@RequestMapping("/account/permissions")
public class PermissionsController {

	@Autowired
	private PermissionsService permissionsService;

	@ApiOperation(value = "根据按钮id查询所属的请求路径集合")
	@ApiOperationSupport(order = 1)
	@ApiImplicitParam(name = "buttonId", value = "菜单按钮id", required = true, paramType = "body", dataType = "Integer", example = "35")
	@PostMapping("/listByMenuButtonId")
	public CommonRespT<List<PermissionsVo>> listByMenuButtonId(@RequestBody Integer buttonId) {
		return ResponseUtil.successT(permissionsService.listByMenuButtonId(buttonId));
	}

	@ApiOperation(value = "通过请求路径查询请求权限集合")
	@ApiOperationSupport(order = 2, params = @DynamicParameters(properties = {
			@DynamicParameter(name = "url", value = "请求路径url", required = true, dataTypeClass = String.class, example = "/account/permissions/selectByUrl") }))
	@PostMapping("/listByUrl")
	public CommonRespT<List<PermissionsVo>> listByUrl(@RequestBody JSONObject json) {
		String url = json.getString("url");
		return ResponseUtil.successT(permissionsService.listByUrl(url));
	}

	@ApiOperation(value = "添加菜单和权限的关联", notes = "插入或更新后台菜单和请求权限的关联表", response = CommonResp.class)
	@ApiOperationSupport(order = 3)
	@PostMapping("/insertMenuPermissions")
	public void insertMenuPermissions(@RequestBody MenuBackPermissions record) {
		permissionsService.insertMenuPermissions(record);
	}

	@ApiOperation(value = "删除菜单和权限的关联", notes = "逻辑删除后台菜单和请求权限的关联表")
	@ApiOperationSupport(order = 4, params = @DynamicParameters(properties = {
			@DynamicParameter(name = "menuBackId", value = "后台菜单Id", example = "30", required = true, dataTypeClass = Integer.class),
			@DynamicParameter(name = "permissionId", value = "请求权限Id", example = "15", required = true, dataTypeClass = Integer.class) }), responses = @DynamicResponseParameters(properties = {
					@DynamicParameter(value = "状态码", name = "code"), @DynamicParameter(value = "返回信息", name = "msg"),
					@DynamicParameter(value = "是否执行成功", name = "obj", dataTypeClass = Boolean.class) }))
	@PostMapping("/deleteMenuPermissions")
	public Boolean deleteMenuPermissions(@RequestBody JSONObject json) {
		Integer menuBackId = json.getInteger("menuBackId");
		Integer permissionId = json.getInteger("permissionId");
		if (menuBackId == null || permissionId == null) {
			throw new DataFormatException();
		}
		// 条件构造器
		LambdaQueryWrapper<MenuBackPermissions> query = new LambdaQueryWrapper<>();
		query.eq(MenuBackPermissions::getMenuBackId, menuBackId).eq(MenuBackPermissions::getPermissionId, permissionId);
		boolean delete = new MenuBackPermissions().delete(query);
		return delete;
	}

	@ApiOperation(value = "过滤查询请求权限集合", notes = "根据请求所属类名、请求路径过滤分页查询")
	@ApiOperationSupport(order = 5, author = "wufang", params = @DynamicParameters(properties = {
			@DynamicParameter(name = "moduleId", value = "所属模块名", required = true, dataTypeClass = Integer.class, example = "1"),
			@DynamicParameter(name = "url", value = "请求路径", required = true, dataTypeClass = String.class, example = "/config/eec"),
			@DynamicParameter(name = "pageNum", value = "当前页码", required = true, dataTypeClass = String.class, example = "1"),
			@DynamicParameter(name = "pageSize", value = "每页条数", required = true, dataTypeClass = String.class, example = "10") }), responses = @DynamicResponseParameters(name = "PageInfoVo<Permissions>", properties = {
					@DynamicParameter(value = "请求名称", name = "name", example = "selectPageInfo"),
					@DynamicParameter(value = "描述", name = "description", example = "查询用户信息分页列表"),
					@DynamicParameter(value = "是否启用", name = "enable", example = "启用"),
					@DynamicParameter(value = "类型描述", name = "typeDescription", example = "基于角色的访问控制"),
					@DynamicParameter(value = "请求地址", name = "url", example = "/account/user/selectPageInfo"),
					@DynamicParameter(value = "模块描述", name = "moduleDescription", example = "用户接口"),
					@DynamicParameter(value = "备注", name = "remark") }))
	@PostMapping(value = "/listPageInfo")
	public PageInfoVo<Permissions> listPageInfo(@RequestBody JSONObject json) {
		// 过滤查询条件
		Integer moduleId = json.getInteger("moduleId");
		Integer typeId = json.getInteger("typeId");
		String url = json.getString("url");
		// 封装参数
		ListPageInfoParam param = new ListPageInfoParam();
		param.setModuleId(moduleId);
		param.setTypeId(typeId);
		param.setUrl(StringUtil.isEmpty(url) ? null : url);
		param.setPageNum(json.getIntValue("pageNum"));
		param.setPageSize(json.getIntValue("pageSize"));
		// 执行并返回数据
		return permissionsService.listPageInfo(param);
	}

	@ApiOperation(value = "删除单个请求权限", notes = "根据一个请求权限标识，删除请求权限")
	@ApiOperationSupport(order = 6, author = "wufang")
	@ApiImplicitParam(name = "id", value = "请求权限标识", required = true, dataTypeClass = Integer.class, example = "1")
	@PostMapping("/removePermission")
	public void removePermission(@RequestBody Integer id) {
		permissionsService.removePermission(id);
	}

	@ApiOperationSupport(order = 7, author = "wufang")
	@ApiOperation(value = "禁用单个请求权限", notes = "根据一个请求权限标识，禁用请求权限")
	@ApiImplicitParam(name = "id", value = "请求权限标识", required = true, dataTypeClass = Integer.class, example = "1", paramType = "body")
	@PostMapping("/disablePermission")
	public void disablePermission(@RequestBody Integer id) {
		permissionsService.disablePermission(id);
	}

	@ApiOperationSupport(order = 8, author = "wufang", params = @DynamicParameters(properties = {
			@DynamicParameter(name = "permissionsTypeId", value = "请求权限的类型标识", example = "1", required = true, dataTypeClass = Integer.class),
			@DynamicParameter(name = "permissionsModuleId", value = "请求权限的模块标识", example = "1", required = true, dataTypeClass = Integer.class),
			@DynamicParameter(name = "name", value = "权限名称", example = "1", required = true, dataTypeClass = String.class),
			@DynamicParameter(name = "description", value = "权限描述", example = "1", dataTypeClass = String.class),
			@DynamicParameter(name = "url", value = "请求路径", example = "1", required = true, dataTypeClass = String.class),
			@DynamicParameter(name = "remark", value = "备注", example = "1", dataTypeClass = String.class) }))
	@ApiOperation(value = "新增请求权限")
	@PostMapping("/addPermission")
	public void addPermission(@RequestBody Permissions permission) {
		permissionsService.addPermission(permission);
	}

	@ApiOperationSupport(order = 9, author = "wufang", params = @DynamicParameters(properties = {
			@DynamicParameter(name = "permissionsTypeId", value = "请求权限的类型标识", example = "1", required = true, dataTypeClass = Integer.class),
			@DynamicParameter(name = "permissionsModuleId", value = "请求权限的模块标识", example = "1", required = true, dataTypeClass = Integer.class),
			@DynamicParameter(name = "name", value = "权限名称", example = "1", required = true, dataTypeClass = String.class),
			@DynamicParameter(name = "description", value = "权限描述", example = "1", dataTypeClass = String.class),
			@DynamicParameter(name = "url", value = "请求路径", example = "1", required = true, dataTypeClass = String.class),
			@DynamicParameter(name = "remark", value = "备注", example = "1", dataTypeClass = String.class) }))
	@ApiOperation(value = "编辑请求权限", notes = "根据一个请求权限标识，编辑请求权限")
	@PostMapping("/editPermission")
	public void editPermission(@RequestBody Permissions permission) {
		permissionsService.editPermission(permission);
	}

	@ApiOperationSupport(order = 10, author = "wufang")
	@ApiOperation(value = "批量删除请求权限", notes = "批量删除请求权限")
	@ApiImplicitParam(name = "idList", value = "请求权限标识集合", required = true, paramType = "body", allowMultiple = true, dataType = "Integer")
	@PostMapping("/removePermissionSelect")
	public void removePermissionSelect(@RequestBody List<Integer> idList) {
		permissionsService.removePermissionSelect(idList);
	}

	@ApiOperation(value = "批量禁用请求权限", notes = "批量禁用请求权限")
	@ApiOperationSupport(order = 11, author = "wufang")
	@ApiImplicitParam(name = "idList", value = "请求权限标识集合", required = true, paramType = "body", allowMultiple = true, dataType = "Integer")
	@PostMapping("/disablePermissionSelect")
	public void disablePermissionSelect(@RequestBody List<Integer> idList) {
		permissionsService.disablePermissionSelect(idList);
	}
}
