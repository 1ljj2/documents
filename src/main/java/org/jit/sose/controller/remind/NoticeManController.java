package org.jit.sose.controller.remind;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.jit.sose.domain.entity.SecurityUser;
import org.jit.sose.domain.param.AddNoticeAuditParam;
import org.jit.sose.domain.param.AddNoticeParam;
import org.jit.sose.domain.param.ListNoticeParam;
import org.jit.sose.domain.vo.ListNoticeVo;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.service.NoticeService;
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
@Api(tags = "公告管理接口")
@ApiSupport(author = "jinyu")
@RestController
@RequestMapping("/noticeMan")
public class NoticeManController {
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private UserDepartmentConService userDepartmentConService;
	
	@Autowired
    private UserService userService;
	
	@ApiOperation(value = "查询待审核的公告个数")
    @ApiOperationSupport(order = 14)
    @GetMapping("/newNoticeToAuditCount")
    public int newNoticeToAuditCount(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser) {
		Integer userId = securityUser.getId();//获取用户信息userId
        return noticeService.newNoticeToAuditCount(userId);
    }
	
	@ApiOperation(value = "筛选我的提醒待审核公告")
    @ApiOperationSupport(order = 13)
    @PostMapping("/listAuditNoticeMyRemind")
    public PageInfoVo<ListNoticeVo> listAuditNoticeMyRemind(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser,@RequestBody JSONObject json) {
		Integer userId = securityUser.getId();//获取用户信息userId
		// 封装
		ListNoticeParam param = new ListNoticeParam();
        // 搜索参数
        String title = json.getString("title");
        param.setTitle(StringUtil.isEmpty(title) ? null : title);
        param.setDepartmentId(json.getInteger("departmentId"));
        param.setUserId(json.getInteger("userId"));
        param.setCategoryId(json.getInteger("categoryId"));
        param.setAuditId(userId);
        // 分页
        param.setPageNum(json.getIntValue("pageNum"));
        param.setPageSize(json.getIntValue("pageSize"));
        return noticeService.listAuditNoticeMyRemind(param);
    }
	
	@ApiOperation(value = "查询新的公告个数")
    @ApiOperationSupport(order = 12)
    @GetMapping("/newNoticeCount")
    public int newNoticeCount() {
        return noticeService.newNoticeCount();
    }
	
	@ApiOperation(value = "根据【标题、发布部门、发布用户、分类、分页参数】筛选公告审核人对应的列表对象")
    @ApiOperationSupport(order = 11)
    @PostMapping("/listAuditNotice")
    public PageInfoVo<ListNoticeVo> listAuditNotice(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser,@RequestBody JSONObject json) {
		Integer userId = securityUser.getId();//获取用户信息userId
		// 封装
		ListNoticeParam param = new ListNoticeParam();
        // 搜索参数
        String title = json.getString("title");
        param.setTitle(StringUtil.isEmpty(title) ? null : title);
        param.setDepartmentId(json.getInteger("departmentId"));
        param.setUserId(json.getInteger("userId"));
        param.setCategoryId(json.getInteger("categoryId"));
        param.setAuditId(userId);
        // 分页
        param.setPageNum(json.getIntValue("pageNum"));
        param.setPageSize(json.getIntValue("pageSize"));
        return noticeService.listAuditNotice(param);
    }
	
	@ApiOperation(value = "通过公告", notes = "根据一个公告标识，通过该公告")
    @ApiImplicitParam(name = "id", value = "公告标识", required = true, dataTypeClass = Integer.class, example = "1")
    @PostMapping("/checkAuditTrue")
    @ApiOperationSupport(order = 10)
    public void checkAuditTrue(@RequestBody JSONObject jsonObject) {
        System.out.println("107"+jsonObject.getInteger("id"));
        noticeService.checkAuditTrue(jsonObject.getInteger("id"));
    }
	
	@ApiOperation(value = "我的提醒页面查询已发布公告，并分页展示")
    @ApiOperationSupport(order = 9)
    @PostMapping("/listNoticeMyRemind")
    public PageInfoVo<ListNoticeVo> listNoticeMyRemind(@RequestBody JSONObject json) {
        // 封装
		ListNoticeParam param = new ListNoticeParam();
        // 分页
        param.setPageNum(json.getIntValue("pageNum"));
        param.setPageSize(json.getIntValue("pageSize"));
        param.setTitle(json.getString("title"));
        return noticeService.listNoticeMyRemind(param);
    }
	
	@ApiOperation(value = "添加公告和审核人的关联")
    @ApiOperationSupport(order = 8)
    @PostMapping("/addAuditNoticeCon")
    public void addAuditNoticeCon(@RequestBody @Validated AddNoticeAuditParam addNoticeAuditParam) {
		System.out.println(addNoticeAuditParam);
        noticeService.addAuditNoticeCon(addNoticeAuditParam);
    }
	
	@ApiOperation(value = "置顶公告", notes = "根据一个公告标识，置顶该公告")
    @ApiImplicitParam(name = "id", value = "公告标识", required = true, dataTypeClass = Integer.class, example = "1")
    @PostMapping("/setTopNotice")
    @ApiOperationSupport(order = 7)
    public void setTopNotice(@RequestBody Integer id) {
        noticeService.setTopNotice(id);
    }
	
	@ApiOperation(value = "禁用公告", notes = "根据一个公告标识，禁用该公告")
    @ApiImplicitParam(name = "id", value = "公告标识", required = true, dataTypeClass = Integer.class, example = "1")
    @PostMapping("/disableNotice")
    @ApiOperationSupport(order = 6)
    public void disableNotice(@RequestBody JSONObject jsonObject) {
        System.out.println("145"+jsonObject.getInteger("id"));
        noticeService.disableNotice(jsonObject.getInteger("id"));
    }
	
	@ApiOperation(value = "编辑公告")
    @ApiOperationSupport(order = 5)
    @PostMapping("/editNotice")
    public void editNotice(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser,@RequestBody @Validated AddNoticeParam param) {
		Integer userId = securityUser.getId();//获取用户信息userId
		param.setUserId(userId);
		Integer departmentId = userDepartmentConService.selectDepartmentIdByUserId(userId);
		param.setDepartmentId(departmentId);
		System.out.println(param);
        noticeService.editNotice(param);
    }
	
	@ApiOperation(value = "新增公告")
    @ApiOperationSupport(order = 4)
    @PostMapping("/addNotice")
    public void addNotice(@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser,@RequestBody @Validated AddNoticeParam param) {
		Integer userId = securityUser.getId();//获取用户信息userId
		param.setUserId(userId);
		Integer departmentId = userDepartmentConService.selectDepartmentIdByUserId(userId);
		param.setDepartmentId(departmentId);
		System.out.println(param);
        noticeService.addNotice(param);
    }
	
	@ApiOperation(value = "批量删除公告")
    @ApiImplicitParam(name = "idList", value = "公告标识集合", required = true, paramType = "body", allowMultiple = true, dataType = "Integer")
    @PostMapping("/removeNoticeSelect")
    @ApiOperationSupport(order = 3)
    public void removeNoticeSelect(@RequestBody List<Integer> idList) {
        noticeService.removeNoticeSelect(idList);
    }

    @ApiOperation(value = "删除单个公告", notes = "根据一个公告标识，删除该公告")
    @ApiImplicitParam(name = "id", value = "公告标识", required = true, dataTypeClass = Integer.class, example = "1")
    @PostMapping("/removeNotice")
    @ApiOperationSupport(order = 2)
    public void removeNotice(@RequestBody Integer id) {
        noticeService.removeNotice(id);
    }
	
	@ApiOperation(value = "根据【标题、发布部门、发布用户、分类、分页参数】筛选公告列表对象")
    @ApiOperationSupport(order = 1)
    @PostMapping("/listNotice")
    public PageInfoVo<ListNoticeVo> listNotice(@RequestBody JSONObject json) {
        // 封装
		ListNoticeParam param = new ListNoticeParam();
        // 搜索参数
        String title = json.getString("title");
        param.setTitle(StringUtil.isEmpty(title) ? null : title);
        param.setDepartmentId(json.getInteger("departmentId"));
        param.setUserId(json.getInteger("userId"));
        param.setCategoryId(json.getInteger("categoryId"));
        // 分页
        param.setPageNum(json.getIntValue("pageNum"));
        param.setPageSize(json.getIntValue("pageSize"));
        return noticeService.listNotice(param);
    }
	
}
 