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
import org.jit.sose.domain.entity.PermissionsType;
import org.jit.sose.service.PermissionsTypeService;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.web.response.CommonRespT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 请求权限类型
 */
@Api(tags = "权限类型接口")
@ApiSupport(order = 6, author = "wufang")
@RestController
@RequestMapping("/account/permissionsType")
public class PermissionsTypeController {

    @Autowired
    private PermissionsTypeService permissionsTypeService;

    @ApiResponses({@ApiResponse(code = 200, message = "Request Success"),
            @ApiResponse(code = 401, message = "Authentication Failure"),
            @ApiResponse(code = 402, message = "Login Information Invalid"),
            @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
    @ApiOperationSupport(order = 1,author = "wufang",
            params = @DynamicParameters(properties = {
                    @DynamicParameter(name = "permissionId", value = "请求权限Id", example = "15", required = true, dataTypeClass = Integer.class)}))
    @ApiOperation(value = "根据请求权限id 查请求类型详情")
    @PostMapping("/viewTypeByPermissionId")
    public CommonRespT<PermissionsType> viewTypeByPermissionId(@RequestBody JSONObject json) {
        Integer permissionId = json.getInteger("permissionId");
        return ResponseUtil.successT(permissionsTypeService.viewTypeByPermissionId(permissionId));
    }

    @ApiResponses({@ApiResponse(code = 200, message = "Request Success"),
            @ApiResponse(code = 401, message = "Authentication Failure"),
            @ApiResponse(code = 402, message = "Login Information Invalid"),
            @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
    @ApiOperation(value = "获取所有的请求类型名称", notes = "获取所有的请求类型名称")
    @GetMapping("/getTypeName")
    public CommonRespT<List<String>> getTypeName() {
        return ResponseUtil.successT(permissionsTypeService.getTypeName());
    }

    @ApiResponses({@ApiResponse(code = 200, message = "Request Success"),
            @ApiResponse(code = 401, message = "Authentication Failure"),
            @ApiResponse(code = 402, message = "Login Information Invalid"),
            @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
    @ApiOperation(value = "根据id查询请求类型", notes = "根据id查询请求类型")
    @ApiOperationSupport(order = 2,author = "wufang")
    @PostMapping("/showPermissionsType")
    public CommonRespT<String> showPermissionsType(@RequestBody JSONObject json) {
        Integer id = json.getInteger("id");
        return ResponseUtil.successT(permissionsTypeService.showPermissionsType(id));
    }

    @ApiResponses({@ApiResponse(code = 200, message = "Request Success"),
            @ApiResponse(code = 401, message = "Authentication Failure"),
            @ApiResponse(code = 402, message = "Login Information Invalid"),
            @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
    @ApiOperation(value = "获取所有的请求类型对象", notes = "获取所有的请求类型对象")
    @ApiOperationSupport(order = 3,author = "wufang")
    @GetMapping("/listType")
    public CommonRespT<List<PermissionsType>> listType() {
        return ResponseUtil.successT(permissionsTypeService.listType());
    }



}
