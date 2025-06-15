package org.jit.sose.controller.archive;

import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jit.sose.domain.vo.ListArchiveFileForAuditVo;
import org.jit.sose.service.ArchiveExampleFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author LJH
 * @Date 2020年10月21日14点23分
 */
@ApiResponses({@ApiResponse(code = 200, message = "Request Success"),
        @ApiResponse(code = 401, message = "Authentication Failure"),
        @ApiResponse(code = 402, message = "Login Information Invalid"),
        @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
@ApiSupport(author = "LJH")
@RestController
@RequestMapping("/archive/archiveExampleFile")
public class ArchiveExampleFileController {
    @Autowired
    private ArchiveExampleFileService archiveExampleFileService;

    @ApiOperation(value = "查询用户上传档案的文件及模板列表")
    @PostMapping("/listArchiveFile")
    public List<ListArchiveFileForAuditVo> listArchiveFile(@RequestBody JSONObject jsonObject) {
//        return archiveExampleWatchService.listArchiveFileByCondition(param);
        System.out.println(jsonObject.getInteger("exampleId"));
        return archiveExampleFileService.listArchiveFile(jsonObject.getInteger("exampleId"));
    }
}
 