package org.jit.sose.controller.account;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jit.sose.domain.vo.ListIdNameVo;
import org.jit.sose.service.YearTermService;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.util.StringUtil;
import org.jit.sose.web.response.CommonRespT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wufang
 * @Date 2020-08-05 11:57:48
 */
@ApiResponses({@ApiResponse(code = 200, message = "success"),
        @ApiResponse(code = 401, message = "Authentication Failure"),
        @ApiResponse(code = 402, message = "Login Information Invalid"),
        @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
@Api(tags = "学年学期接口")
@ApiSupport(author = "wufang")
@RestController
@RequestMapping("/account/yearTerm")
public class YearTermController {

    @Autowired
    private YearTermService yearTermService;

    @ApiOperation(value = "根据输入的termName获得termList")
    @ApiOperationSupport(order = 1)
    @PostMapping(value = "/listTermByName")
    public CommonRespT<List<ListIdNameVo>> listTermByName(@RequestBody JSONObject json) {
        String name = StringUtil.isEmpty(json.getString("name")) ? null : json.getString("name");
        return ResponseUtil.successT(yearTermService.listTermByName(name));
    }

}
