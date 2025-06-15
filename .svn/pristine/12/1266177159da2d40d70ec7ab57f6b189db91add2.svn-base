package org.jit.sose.controller.remind;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.jit.sose.domain.entity.MessReadUser;
import org.jit.sose.domain.entity.SecurityUser;
import org.jit.sose.domain.param.AddEditMessParam;
import org.jit.sose.domain.param.ListMessParam;
import org.jit.sose.domain.vo.ListMessVo;
import org.jit.sose.domain.vo.ListReadUserByMess;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.service.MessageService;
import org.jit.sose.service.UserDepartmentConService;
import org.jit.sose.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/** 
* @author jinyu: 
* @Date 2020年9月22日 上午10:42:16 
*  
*/
@Api(tags = "消息管理接口")
@ApiSupport(author = "jinyu")
@RestController
@RequestMapping("/messageMan")
public class MessageManController {
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserDepartmentConService userDepartmentConService;
	
	@Autowired
    private UserService userService;
	
	@ApiOperation(value = "查询未读新消息")
    @ApiOperationSupport(order = 8)
    @GetMapping("/newMessCount")
    public int newMessCount(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser) {
		Integer userId = securityUser.getId();//获取用户信息userId
		// 封装
		ListReadUserByMess vo = new ListReadUserByMess();
		//当前用户id
		vo.setUserId(userId);
        return messageService.newMessCount(vo);
    }
	
    @ApiOperation(value = "设置用户消息已读", notes = "根据一个消息标识，删除该消息")
    @ApiImplicitParam(name = "id", value = "消息标识", required = true, dataTypeClass = Integer.class, example = "1")
    @PostMapping("/isRead")
    @ApiOperationSupport(order = 7)
    public void isRead(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser,@RequestBody JSONObject json) {
    	Integer userId = securityUser.getId();//获取用户信息userId
    	MessReadUser data = new MessReadUser();
    	data.setMessId(json.getIntValue("messId"));
    	data.setUserId(userId);
    	messageService.isRead(data);
    }	
	
	@ApiOperation(value = "我的提醒页面根据登录用户查询相应的消息")
    @ApiOperationSupport(order = 6)
    @PostMapping("/listMessMyRemind")
    public PageInfoVo<ListMessVo> listMessMyRemind(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser,@RequestBody JSONObject json) {
		Integer userId = securityUser.getId();//获取用户信息userId
		// 封装
		ListMessParam param = new ListMessParam();
        // 分页
        param.setPageNum(json.getIntValue("pageNum"));
        param.setPageSize(json.getIntValue("pageSize"));
        param.setTitle(json.getString("title"));
        //当前用户id
        param.setReadUserId(userId);
        System.out.println(param);
        return messageService.listMessMyRemind(param);
    }
	
	@ApiOperation(value = "新增消息")
    @ApiOperationSupport(order = 5)
    @PostMapping("/addMess")
    public void addMess(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser,@RequestBody @Validated AddEditMessParam param) {
		Integer userId = securityUser.getId();//获取用户信息userId
		param.setUserId(userId);
		Integer departmentId = userDepartmentConService.selectDepartmentIdByUserId(userId);
		param.setDepartmentId(departmentId);
		messageService.addMess(param);
    }
	
	@ApiOperation(value = "编辑消息")
    @ApiOperationSupport(order = 4)
    @PostMapping("/editMess")
    public void editMess(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser,@RequestBody @Validated AddEditMessParam param) {
		Integer userId = securityUser.getId();//获取用户信息userId
		param.setUserId(userId);
		Integer departmentId = userDepartmentConService.selectDepartmentIdByUserId(userId);
		param.setDepartmentId(departmentId);
		messageService.editMess(param);
    }
	
	@ApiOperation(value = "批量删除消息")
    @ApiImplicitParam(name = "idList", value = "消息标识集合", required = true, paramType = "body", allowMultiple = true, dataType = "Integer")
    @PostMapping("/removeMessSelect")
    @ApiOperationSupport(order = 3)
    public void removeMessSelect(@RequestBody List<Integer> idList) {
		messageService.removeMessSelect(idList);
    }

    @ApiOperation(value = "删除单个消息", notes = "根据一个消息标识，删除该消息")
    @ApiImplicitParam(name = "id", value = "消息标识", required = true, dataTypeClass = Integer.class, example = "1")
    @PostMapping("/removeMess")
    @ApiOperationSupport(order = 2)
    public void removeMess(@RequestBody Integer id) {
    	messageService.removeMess(id);
    }
	
	@ApiOperation(value = "根据【标题、发布部门、发布用户、分类、分页参数】筛选消息列表对象")
    @ApiOperationSupport(order = 1)
    @PostMapping("/listMess")
    public PageInfoVo<ListMessVo> listMess(@RequestBody JSONObject json) {
		
		// 封装
        ListMessParam param = new ListMessParam();
        // 搜索参数
        String title = json.getString("title");
        param.setTitle(StringUtil.isEmpty(title) ? null : title);
        param.setDepartmentId(json.getInteger("departmentId"));
        param.setUserId(json.getInteger("userId"));
        param.setCategoryId(json.getInteger("categoryId"));
        // 分页
        param.setPageNum(json.getIntValue("pageNum"));
        param.setPageSize(json.getIntValue("pageSize"));
        return messageService.listMess(param);
    }
	
}
 