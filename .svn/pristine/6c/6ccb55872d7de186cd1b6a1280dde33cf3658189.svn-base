package org.jit.sose.controller.file;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jit.sose.service.FileExampleProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wufang
 * @Date 2020-10-18 15:24:55
 */
@Api(tags = "文档审核接口")
@ApiSupport(author = "wufang")
@RestController
@RequestMapping("/file/fileExampleProcess")
public class FileExampleProcessController {

    @Autowired
    private FileExampleProcessService fileExampleProcessService;

    @ApiOperation(value = "获取审核文档模态框所需信息：步骤条+下一个流程运转步骤信息")
    @PostMapping("/getAuditFileModalInfo")
    public Map<String, Object> getAuditFileModalInfo(@RequestBody JSONObject json) {
        Integer exampleId = json.getInteger("exampleId");
        Integer processId = json.getInteger("processId");
        Integer nowStepId = json.getInteger("nowStepId");
        return fileExampleProcessService.getAuditFileModalInfo(exampleId,processId, nowStepId);
    }

}
