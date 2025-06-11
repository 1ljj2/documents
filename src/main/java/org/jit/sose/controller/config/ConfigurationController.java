package org.jit.sose.controller.config;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.*;
import org.jit.sose.domain.param.ListConfigParam;
import org.jit.sose.domain.vo.ListConfigVo;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** 
* @author jinyu: 
* @Date 2020年10月20日 下午2:01:37 
*  
*/
@ApiResponses({@ApiResponse(code = 200, message = "Request Success"),
    @ApiResponse(code = 401, message = "Authentication Failure"),
    @ApiResponse(code = 402, message = "Login Information Invalid"),
    @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
@Api(tags = "配置管理接口")
@RestController
@RequestMapping("/config/configuration")
public class ConfigurationController {
	
	@Autowired
	private ConfigurationService configurationService;
	
	@ApiOperation(value = "档案中能否存在未审核文档开关")
    @ApiOperationSupport(order = 10)
    @GetMapping("/checkArchiveNotAuditInSet")
    public Boolean checkArchiveNotAuditInSet() {
        return configurationService.checkArchiveNotAuditInSet();
    }
	
	@ApiOperation(value = "模板/文档名称能否编辑开关")
    @ApiOperationSupport(order = 9)
    @GetMapping("/checkFileArchiveNameChange")
    public Boolean checkFileArchiveNameChange() {
        return configurationService.checkFileArchiveNameChange();
    }

	@ApiOperation(value = "发布审核短信开关")
    @ApiOperationSupport(order = 8)
    @GetMapping("/checkMessage4Audit")
    public Boolean checkMessage4Audit() {
        return configurationService.checkMessage4Audit();
    }
	
	@ApiOperation(value = "新增配置项")
    @ApiOperationSupport(order = 7)
    @PostMapping("/addConfig")
    public void addConfig(@RequestBody @Validated ListConfigVo vo) {
		configurationService.addConfig(vo);
    }
	
	@ApiOperation(value = "编辑配置项")
    @ApiOperationSupport(order = 6)
    @PostMapping("/editConfig")
    public void editConfig(@RequestBody @Validated ListConfigVo vo) {
		configurationService.editConfig(vo);
    }
	
	@ApiOperation(value = "启用单个配置表信息", notes = "根据一个配置表标识，启用该配置表信息")
    @ApiImplicitParam(name = "id", value = "配置表标识", required = true, dataTypeClass = Integer.class, example = "1")
    @PostMapping("/isableConfig")
    @ApiOperationSupport(order = 5)
    public void isableConfig(@RequestBody Integer id) {
    	configurationService.isableConfig(id);
    }
	
	@ApiOperation(value = "禁用单个配置表信息", notes = "根据一个配置表标识，禁用该配置表信息")
    @ApiImplicitParam(name = "id", value = "配置表标识", required = true, dataTypeClass = Integer.class, example = "1")
    @PostMapping("/disableConfig")
    @ApiOperationSupport(order = 4)
    public void disableConfig(@RequestBody Integer id) {
    	configurationService.disableConfig(id);
    }
	
	@ApiOperation(value = "批量删除配置表信息")
    @ApiImplicitParam(name = "idList", value = "配置表标识标识集合", required = true, paramType = "body", allowMultiple = true, dataType = "Integer")
    @PostMapping("/removeConfigSelect")
    @ApiOperationSupport(order = 3)
    public void removeConfigSelect(@RequestBody List<Integer> idList) {
		configurationService.removeConfigSelect(idList);
    }

    @ApiOperation(value = "删除单个配置表信息", notes = "根据一个配置表标识，删除该配置表信息")
    @ApiImplicitParam(name = "id", value = "配置表标识", required = true, dataTypeClass = Integer.class, example = "1")
    @PostMapping("/removeConfig")
    @ApiOperationSupport(order = 2)
    public void removeConfig(@RequestBody Integer id) {
    	configurationService.removeConfig(id);
    }
	
	@ApiOperation(value = "根据【描述、是否禁用、分页参数】筛选配置列表对象")
    @ApiOperationSupport(order = 1)
    @PostMapping("/listConfigByCondition")
    public PageInfoVo<ListConfigVo> listConfigByCondition(@RequestBody JSONObject json) {
        // 封装
		ListConfigParam param = new ListConfigParam();
        // 搜索参数
        String description = json.getString("description");
        param.setDescription(StringUtil.isEmpty(description) ? null : description);
        String isEnable = json.getString("isEnable");
        param.setIsEnable(StringUtil.isEmpty(isEnable) ? null : isEnable);
        // 分页
        param.setPageNum(json.getIntValue("pageNum"));
        param.setPageSize(json.getIntValue("pageSize"));
        return configurationService.listConfigByCondition(param);
    }

}
 