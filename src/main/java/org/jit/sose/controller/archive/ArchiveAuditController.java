package org.jit.sose.controller.archive;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jit.sose.domain.entity.SecurityUser;
import org.jit.sose.domain.param.ForReviewArcToNextParam;
import org.jit.sose.domain.vo.ListAuditArchiveVo;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.service.ArchiveAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * @author wufang
 * @Date 2020-10-19 15:14:38
 */
@Api(tags = "档案审核记录接口")
@ApiSupport(author = "wufang")
@RestController
@RequestMapping("/archive/archiveAudit")
public class ArchiveAuditController {

    @Autowired
    private ArchiveAuditService archiveAuditService;

    @ApiOperation(value = "档案审核送审下一个人审核")
    @PostMapping("/forReviewArcToNext")
    public void forReviewArcToNext(@RequestBody ForReviewArcToNextParam param) {
        archiveAuditService.forReviewArcToNext(param);
    }

    @ApiOperation(value = "获取审核档案模态框所需信息：步骤条+下一个流程运转步骤信息")
    @PostMapping("/getAuditArchiveModalInfo")
    public Map<String, Object> getAuditArchiveModalInfo(@RequestBody JSONObject json) {
        Integer exampleId = json.getInteger("exampleId");
        Integer processId = json.getInteger("processId");
        Integer nowStepId = json.getInteger("nowStepId");
        return archiveAuditService.getAuditArchiveModalInfo(exampleId,processId, nowStepId);
    }

    @ApiOperation(value = "根据当前用户标识获取自身需要审核的档案")
    @PostMapping("/listAuditArchiveMyRemind")
    public PageInfoVo<ListAuditArchiveVo> listAuditArchiveMyRemind(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser, @RequestBody JSONObject json) {
        Integer pageNum = json.getInteger("pageNum");
        Integer pageSize = json.getInteger("pageSize");
        Integer userId = securityUser.getId();
        return archiveAuditService.listAuditArchiveMyRemind(userId, pageNum, pageSize);
    }

}
