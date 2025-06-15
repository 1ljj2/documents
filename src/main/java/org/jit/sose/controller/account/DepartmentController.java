package org.jit.sose.controller.account;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.github.xiaoymin.knife4j.annotations.DynamicParameter;
import com.github.xiaoymin.knife4j.annotations.DynamicParameters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.jit.sose.domain.entity.Department;
import org.jit.sose.domain.vo.ListDepartmentVo;
import org.jit.sose.domain.vo.ListIdNameVo;
import org.jit.sose.domain.vo.PageInfoVo;
import org.jit.sose.service.DepartmentService;
import org.jit.sose.util.FastjsonUtil;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.util.StringUtil;
import org.jit.sose.web.response.CommonResp;
import org.jit.sose.web.response.CommonRespT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author jinyu
 * @Date 2020-07-05 19:32:16
 */
@Api(tags = "部门接口")
@ApiSupport(author = "jinyu")
@RestController
@RequestMapping("/account/department")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @ApiOperation(value = "查询par_id为1(软件工程学院)的部门name和id集合")
    @GetMapping("/getDepartmentList")
    public CommonRespT<List<ListIdNameVo>> getDepartmentList() {
        List<ListIdNameVo> departmentList = departmentService.getDepartmentList();
        return ResponseUtil.successT(departmentList);
    }

    @ApiOperation(value = "获取档案的分类name集合")
    @PostMapping("/getParIdList")
    public CommonRespT<List<Department>> getParIdList(@RequestBody JSONObject json) {
        Integer id = json.getInteger("id");
        List<Department> departmentList = departmentService.getParIdList(id);
        return ResponseUtil.successT(departmentList);
    }

    @ApiOperation(value = "根据部门nameList模糊查询部门id集合")
    @ApiOperationSupport(order = 2,
            params = @DynamicParameters(properties = {
                    @DynamicParameter(name = "departmentNameList", value = "部门nameList", example = "body", required = true, dataTypeClass = String.class)}))
    @PostMapping("/translateNameToId")
    public CommonRespT<List<Integer>> translateNameToId(@RequestBody JSONObject json) {
        JSONArray jsonArray = json.getJSONArray("departmentNameList");
        List<String> departmentNameList = FastjsonUtil.convertJSONArrayToTypeList(jsonArray, String.class);
        return ResponseUtil.successT(departmentService.translateNameToId(departmentNameList));
    }

    @ApiOperation(value = "根据部门idList模糊查询部门名称集合")
    @ApiOperationSupport(author = "jinyu", params = @DynamicParameters(properties = {
            @DynamicParameter(name = "departmentIdList", value = "部门idList", example = "body", required = true, dataTypeClass = Integer.class)}))
    @PostMapping("/showDepartmentSelected")
    public CommonRespT<List<String>> showDepartmentSelected(@RequestBody JSONObject json) {
        JSONArray jsonArray = json.getJSONArray("departmentIdList");
        List<Integer> departmentIdList = FastjsonUtil.convertJSONArrayToTypeList(jsonArray, Integer.class);
        return ResponseUtil.successT(departmentService.showDepartmentSelected(departmentIdList));
    }

    @ApiResponses({@ApiResponse(code = 200, message = "success"),
            @ApiResponse(code = 401, message = "Authentication Failure"),
            @ApiResponse(code = 402, message = "Login Information Invalid"),
            @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
    @ApiOperation(value = "查询所有部门对象集合")
    @ApiOperationSupport(order = 2)
    @GetMapping(value = "/listDepartment")
    public CommonRespT<List<ListDepartmentVo>> listDepartment() {
        return ResponseUtil.successT(departmentService.listDepartmentByName(null));
    }

    @ApiResponses({@ApiResponse(code = 200, message = "Request Success"),
            @ApiResponse(code = 401, message = "Authentication Failure"),
            @ApiResponse(code = 402, message = "Login Information Invalid"),
            @ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 500, message = "Request fail")})
    @ApiOperation(value = "根据部门名称模糊查询部门对象集合")
    @ApiOperationSupport(order = 1, params = @DynamicParameters(properties = {
            @DynamicParameter(name = "departmentName", value = "部门名称搜索值", example = "body", required = true, dataTypeClass = String.class)}))
    @PostMapping("/listDepartmentByName")
    public CommonRespT<List<ListDepartmentVo>> listDepartmentByName(@RequestBody JSONObject json) {
        String departmentName = json.getString("departmentName");
        return ResponseUtil.successT(departmentService.listDepartmentByName(departmentName));
    }

    @RequestMapping("/listPage")
    @ResponseBody
    public List<Department> listPage() {
        return departmentService.listPage();
    }

    //@ApiOperation(value = "插入部门", response = CommonResp.class)
    @PostMapping("/insertOneDepartment")
    public void insert(@RequestBody Department department) {
        System.out.println("进入插入方法");
        int id = department.getParId();
        System.out.println(id);
        int oldLevel = departmentService.selectLevelById(id);
        department.setLevel(oldLevel + 1);
        System.out.println("等级" + department.getLevel() + "===" + "父" + department.getParId());
        departmentService.insert(department);
    }

    @ApiOperation(value = "编辑部门", response = CommonResp.class)
    @RequestMapping(value = "/edit")
    public void edit(@RequestBody Department department) {
        System.out.println("进入编辑");
        System.out.println(department.toString());
        System.out.println("退出编辑");
        departmentService.edit(department);
    }

    @ApiOperation(value = "删除部门", response = CommonResp.class)
    @PostMapping(value = "/delete")
    public void delete(@RequestBody Integer id) {
        departmentService.deleteOneDepartment(id);
    }

    @ApiOperation(value = "批量删除部门", response = CommonResp.class)
    @PostMapping(value = "/deleteSelection")
    public void deleteList(@RequestBody int[] idList) {
        departmentService.deleteList(idList);
    }

    @ApiOperation(value = "根据【部门名称、等级、分页参数】查找部门", response = CommonResp.class)
    @PostMapping(value = "/selectDepartment")
    public CommonRespT<PageInfoVo<Department>> listPageInfo(@RequestBody JSONObject json) {
        Department department = new Department();
        // 过滤查询条件
        String departmentName = json.getString("departmentName");
        Integer level = json.getInteger("level");
        //String level1=String.valueOf(level);
        department.setDepartmentName(StringUtil.isEmpty(departmentName) ? null : departmentName);
        //String newLevel=StringUtil.isEmpty(level1) ? null :  level1;
        //Integer n=Integer.parseInt(newLevel);
        System.out.println("-----------------------------------@1");
        department.setLevel(level);
        System.out.println("-----------------------------------@2");
        // 当前页码
        Integer pageNum = json.getInteger("pageNum");
        // 每页条数
        Integer pageSize = json.getInteger("pageSize");
        PageInfoVo<Department> pageInfo = departmentService.selectPageInfo(department, pageNum, pageSize);
        return ResponseUtil.successT(pageInfo);
    }
}
