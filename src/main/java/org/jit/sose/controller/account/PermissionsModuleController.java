package org.jit.sose.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jit.sose.domain.entity.PermissionsModule;
import org.jit.sose.service.PermissionsModuleService;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.web.response.CommonRespT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 请求权限所属模块
 */
@Api(tags = "权限所属模块接口")
@ApiSupport(order = 7, author = "wufang")
@RestController
@RequestMapping("/account/permissionsModule")
public class PermissionsModuleController {

    @Autowired
    private PermissionsModuleService permissionsModuleService;

    @ApiResponses({@ApiResponse(code = 200, message = "Request Success"),
            @ApiResponse(code = 401, message = "Authentication Failure"),
            @ApiResponse(code = 402, message = "Login Information Invalid"),
            @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
    @ApiOperationSupport(order = 1,author = "wufang",
            params = @DynamicParameters(properties = {
                    @DynamicParameter(name = "permissionId", value = "请求权限Id", example = "15", required = true, dataTypeClass = Integer.class)}))
    @ApiOperation(value = "根据请求权限id 查请求所属模块详情", notes = "根据请求权限id 看请求所属模块详情")
    @PostMapping("/viewModuleByPermissionId")
    public CommonRespT<PermissionsModule> viewModuleByPermissionId(@RequestBody JSONObject json) {
        Integer permissionId = json.getInteger("permissionId");
        return ResponseUtil.successT(permissionsModuleService.viewModuleByPermissionId(permissionId));
    }

    @ApiResponses({@ApiResponse(code = 200, message = "Request Success"),
            @ApiResponse(code = 401, message = "Authentication Failure"),
            @ApiResponse(code = 402, message = "Login Information Invalid"),
            @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
    @ApiOperation(value = "获取所有的模块名称", notes = "获取所有的模块名称")
    @ApiOperationSupport(order = 2,author = "wufang")
    @PostMapping("/getModuleName")
    public CommonRespT<List<String>> getModuleName() {
        return ResponseUtil.successT(permissionsModuleService.getModuleName());
    }

    @ApiResponses({@ApiResponse(code = 200, message = "Request Success"),
            @ApiResponse(code = 401, message = "Authentication Failure"),
            @ApiResponse(code = 402, message = "Login Information Invalid"),
            @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
    @ApiOperation(value = "根据名称模糊查询对象集合", notes = "根据模块名称模糊查询模块对象集合")
    @ApiOperationSupport(order = 3,author = "wufang",
            params = @DynamicParameters(properties = {
                    @DynamicParameter(name = "query", value = "模块名称搜索值", example = "view", required = true, dataTypeClass = String.class)}))
    @PostMapping("/listModuleByName")
    public CommonRespT<List<PermissionsModule>> listModuleByName(@RequestBody JSONObject json) {
        String query = json.getString("query");
        return ResponseUtil.successT(permissionsModuleService.listModuleByName(query));
    }


}
