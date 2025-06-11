package org.jit.sose.controller.archive;

import org.jit.sose.domain.param.ListChargingArchiveParam;
import org.jit.sose.domain.param.ListFileTemParam;
import org.jit.sose.domain.param.ListMyFileParam;
import org.jit.sose.domain.param.ListNoticeParam;
import org.jit.sose.domain.vo.ListArchiveFileVo;
import org.jit.sose.domain.vo.ListChargingArchiveVo;
import org.jit.sose.domain.vo.ListFileTemVo;
import org.jit.sose.domain.vo.ListMyFileVo;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.service.ArchiveExampleWatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/** 
* @author jinyu: 
* @Date 2020年10月18日 上午11:19:54 
*  
*/
@ApiResponses({@ApiResponse(code = 200, message = "Request Success"),
    @ApiResponse(code = 401, message = "Authentication Failure"),
    @ApiResponse(code = 402, message = "Login Information Invalid"),
    @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
@Api(tags = "系统档案监管接口")
@ApiSupport(author = "jinyu")
@RestController
@RequestMapping("/archive/archiveExample")
public class ArchiveExampleController {
	@Autowired
	private ArchiveExampleWatchService archiveExampleWatchService;

	@ApiOperation(value = "根据【绑定id、分页参数】筛选文件列表对象")
    @PostMapping("/listArchiveFileByCondition")
    public PageInfoVo<ListArchiveFileVo> listArchiveFileByCondition(@RequestBody ListChargingArchiveParam param) {
        return archiveExampleWatchService.listArchiveFileByCondition(param);
    }
	
	@ApiOperation(value = "禁用系统档案", notes = "根据一个标识，禁用该系统档案")
    @ApiImplicitParam(name = "id", value = "系统档案标识", required = true, dataTypeClass = Integer.class, example = "1")
    @PostMapping("/disableArchiveExample")
    @ApiOperationSupport(order = 2)
    public void disableArchiveExample(@RequestBody Integer id) {
		archiveExampleWatchService.disableArchiveExample(id);
    }
	
	@ApiOperation(value = "根据【用户档案编号、用户档案名称、负责人、创建者、分页参数】筛选我的档案")
    @PostMapping("/listChargingArchiveByCondition")
	@ApiOperationSupport(order = 1)
	public PageInfoVo<ListChargingArchiveVo> listChargingArchiveByCondition(@RequestBody ListChargingArchiveParam param) {
        System.out.println("ListChargingArchiveParam"+param);
        System.out.println(archiveExampleWatchService.listChargingArchiveByCondition(param));
		return archiveExampleWatchService.listChargingArchiveByCondition(param);
    }
}
 