package org.jit.sose.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jit.sose.domain.entity.SecurityUser;
import org.jit.sose.util.QcloudSmsUtil;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.web.response.CommonRespT;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author wufang
 * @Date 2020-07-30 21:55:01
 */
@ApiResponses({@ApiResponse(code = 200, message = "success"),
        @ApiResponse(code = 401, message = "Authentication Failure"),
        @ApiResponse(code = 402, message = "Login Information Invalid"),
        @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
@Api(tags = "短信接口")
@RestController
@RequestMapping("/message")
public class MessageController {

    @ApiOperation(value = "【我的文档】-【送审】-提醒短信")
    @PostMapping(value = "/myFileSendMessage")
    public CommonRespT<Integer> myFileSendMessage(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser, @RequestBody JSONObject json) {
        String phone = json.getString("phone");// 获取发送至用户的手机号码
        String username = securityUser.getUsername();
//        String userName = json.getString("userName");// 申请审核的用户名称
        //给用户发送短信,发送成功返回值为0
        System.out.println("申请用户名："+username);
        int  result = QcloudSmsUtil.sendMyFileQcloudSms(phone, username);
        return ResponseUtil.successT(result);
    }


    @ApiOperation(value = "待办事务的提醒短信", notes = "使用腾讯云短信发送短信提醒用户审批待办事务")
    @PostMapping(value = "/remindBackLog")
    public CommonRespT<Integer> remind(@RequestBody JSONObject json) {
        String phone = json.getString("phone");// 获取手机号码
        Integer size = json.getInteger("size");// 获取待办事宜的总条数
        String cutTime = json.getString("cutTime");// 获取最近的一个的待办事宜的截至时间
        //给用户发送短信,发送成功返回值为0
        int  result = QcloudSmsUtil.sendRemindBackLogQcloudSms(phone, size, cutTime);
        return ResponseUtil.successT(result);
    }

}
