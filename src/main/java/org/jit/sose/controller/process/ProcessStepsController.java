package org.jit.sose.controller.process;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jit.sose.domain.vo.ListStepsVo;
import org.jit.sose.service.ProcessStepsService;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.web.response.CommonRespT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wufang
 * @Date 2020-07-02 14:27:27
 */
@ApiResponses({@ApiResponse(code = 200, message = "Request Success"),
        @ApiResponse(code = 401, message = "Authentication Failure"),
        @ApiResponse(code = 402, message = "Login Information Invalid"),
        @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
@Api(tags = "流程步骤接口")
@ApiSupport(author = "wufang")
@RestController
@RequestMapping("/process/processSteps")
public class ProcessStepsController {

    @Autowired
    private ProcessStepsService processStepsService;


    //    @ApiOperation(value = "检查是否是流程的第一个操作步骤", notes = "根据processId和stepId检查")
//    @ApiOperationSupport(order = 7)
//    @PostMapping("/checkIsFirstStep")
//    public CommonRespT<Boolean> checkIsFirstStep(@RequestBody Integer stepId) {
//        Boolean flag = processStepsService.checkIsFirstStep(stepId);
//        return ResponseUtil.successT(flag);
//    }
//
//    @ApiOperation(value = "检查是否是流程的最后一个操作步骤", notes = "根据stepId检查")
//    @ApiOperationSupport(order = 8)
//    @PostMapping("/checkIsFinalStep")
//    public CommonRespT<Boolean> checkIsFinalStep(@RequestBody Integer stepId) {
//        Boolean flag = processStepsService.checkIsFinalStep(stepId);
//        return ResponseUtil.successT(flag);
//    }
//
//    @ApiOperation(value = "查看实例化的流程步骤图", notes = "根据【用户信息、流程信息】获取【流程步骤图及处理信息】集合")
//    @ApiOperationSupport(order = 6)
//    @PostMapping("/checkStepInfo")
//    public CommonRespT<List<UserStepVo>> checkStepInfo(@RequestBody JSONObject json) {
//        Integer userStepId = json.getInteger("userStepId");
//        return ResponseUtil.successT(processStepsService.checkStepInfo(userStepId));
//    }
//
    @ApiOperation(value = "根据idList流程步骤对象")
    @ApiOperationSupport(order = 5)
    @PostMapping("/viewStepByIdList")
    public CommonRespT<List<ListStepsVo>> viewStepByIdList(@RequestBody List<Integer> idList) {
        return ResponseUtil.successT(processStepsService.viewStepByIdList(idList));
    }


//    @ApiOperation(value = "新增单个流程步骤")
//    @ApiOperationSupport(order = 4)
//    @PostMapping("/addStep")
//    public Integer addStep(@RequestBody AddEditStepParam param) {
//        return processStepsService.addStep(param);
//    }


//    @ApiOperation(value = "编辑单个流程步骤")
//    @ApiOperationSupport(order = 3)
//    @PostMapping("/editProcessStep")
//    public void editProcessStep(@RequestBody AddEditStepParam param) {
//        System.out.println(param);
//        processStepsService.editProcessStep(param);
//    }

    @ApiOperation(value = "删除单个流程步骤", notes = "根据流程id删除指定的一个流程步骤，重新部署流程步骤")
    @ApiOperationSupport(order = 2)
    @PostMapping("/removeStepByProcessId")
    public void removeStepByProcessId(@RequestBody JSONObject json) {
        Integer processId = json.getInteger("processId");
        Integer stepId = json.getInteger("stepId");
        processStepsService.removeStepByProcessId(processId, stepId);
    }

    @ApiOperation(value = "根据流程id获取其全部流程步骤对象")
    @ApiOperationSupport(order = 1)
    @PostMapping("/viewStepsByProcessId")
    public CommonRespT<List<ListStepsVo>> viewStepsByProcessId(@RequestBody Integer processId) {
        return ResponseUtil.successT(processStepsService.viewStepsByProcessId(processId));
    }

}
