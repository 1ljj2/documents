package org.jit.sose.controller.config;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jit.sose.service.TxtReadService;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.web.response.CommonRespT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wufang
 * @Date 2020-09-17 14:27:01
 */
@ApiResponses({@ApiResponse(code = 200, message = "Request Success"),
        @ApiResponse(code = 401, message = "Authentication Failure"),
        @ApiResponse(code = 402, message = "Login Information Invalid"),
        @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
@Api(tags = "Txt读取项目配置信息接口")
@RestController
@RequestMapping("/config/txtRead")
public class TxtReadController {

    @Autowired
    private TxtReadService txtReadService;

    @ApiOperation(value = "获取txt中的信息")
    @ApiOperationSupport(order = 1)
    @GetMapping(value = "/getSystemInfo")
    public CommonRespT<Map<String,String>> getSystemInfo() {
        return ResponseUtil.successT(txtReadService.getSystemInfo());
    }


}
