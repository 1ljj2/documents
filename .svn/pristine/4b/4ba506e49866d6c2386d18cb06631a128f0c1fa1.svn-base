package org.jit.sose.controller.archive;

import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jit.sose.domain.entity.SecurityUser;
import org.jit.sose.domain.param.MyArchiveForReviewParam;
import org.jit.sose.service.ArchiveAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author: LJH
 * @Date: 2020/10/17 15:55
 */
@ApiResponses({@ApiResponse(code = 200, message = "Request Success"),
        @ApiResponse(code = 401, message = "Authentication Failure"),
        @ApiResponse(code = 402, message = "Login Information Invalid"),
        @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
@Api(tags = "我的档案接口")
@ApiSupport(author = "LJH")
@RestController
@RequestMapping("/archive/myArchive")
public class MyArchiveController {

    @Autowired
    ArchiveAuditService archiveAuditService;


    @ApiOperation(value = "提交档案审核")
    @PostMapping("/myArchiveForReview")
    public Integer myArchiveForReview(@RequestBody MyArchiveForReviewParam param,
                                      @ApiIgnore @AuthenticationPrincipal SecurityUser securityUser) {
        param.setNowUserId(securityUser.getId());
        System.out.println(param+"+++++++++++++++++++++++++档案送审");
//        archiveTemplateService.addArchiveTem(param);
        return archiveAuditService.myArchiveForReview(param);
    }
}
