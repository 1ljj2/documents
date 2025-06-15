package org.jit.sose.controller.account;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.jit.sose.domain.entity.MenuBack;
import org.jit.sose.domain.entity.SecurityUser;
import org.jit.sose.domain.param.UpdateRoleMenuParam;
import org.jit.sose.domain.vo.MenuBackAllAndOwnTreeVo;
import org.jit.sose.domain.vo.MenuBackTableTreeVo;
import org.jit.sose.domain.vo.MenuBackTreeVo;
import org.jit.sose.domain.vo.MenuBackVo;
import org.jit.sose.service.MenuBackService;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.web.response.CommonResp;
import org.jit.sose.web.response.CommonRespT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 后台菜单
 *
 * @author wangyue
 * @date 2020-05-08 09:57:42
 */
@Slf4j
@Api(tags = "后台菜单接口")
@ApiSupport(order = 4, author = "wangyue")
@ApiResponses({ @ApiResponse(code = 200, message = "success"),
		@ApiResponse(code = 401, message = "Authentication Failure"),
		@ApiResponse(code = 402, message = "Login Information Invalid"),
		@ApiResponse(code = 403, message = "No Permission"), @ApiResponse(code = 420, message = "Data Error"),
		@ApiResponse(code = 421, message = "Cache Error"), @ApiResponse(code = 500, message = "Request fail") })
@RestController
@RequestMapping("/account/menuback")
public class MenuBackController {

	@Value("${security.enable}")
	private Boolean securityEnable;

	@Autowired
	private MenuBackService menuService;

	@ApiOperation(value = "通过用户id递归查询所有菜单", notes = "查询目录和菜单")
	@ApiOperationSupport(order = 1)
	@GetMapping("/listMenu")
	public CommonRespT<List<MenuBackVo>> listMenuByUserId(
			@ApiIgnore @AuthenticationPrincipal SecurityUser securityUser) {
		int userId;
		if (securityEnable) {
			userId = securityUser.getId();
		} else {
			// 若不启用security，给id为 1 的默认值
			userId = 1;
		}
		List<MenuBackVo> menuList = menuService.listMenuByUserId(userId);
		return ResponseUtil.successT(menuList);
	}

	@ApiOperation(value = "查询角色拥有的菜单集合", notes = "查询目录、菜单和操作按钮")
	@ApiOperationSupport(order = 2)
	@ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "body", dataType = "Integer", example = "4")
	@PostMapping("/listOwnTree")
	public CommonRespT<List<MenuBackTreeVo>> listOwnTree(@RequestBody Integer roleId) {
		return ResponseUtil.successT(menuService.listOwnTree(roleId));
	}

	@ApiOperation(value = "查询所有目录菜单和角色拥有的菜单集合", notes = "查询目录、菜单和操作按钮")
	@ApiOperationSupport(order = 3)
	@ApiImplicitParam(name = "roleId", value = "角色id", required = true, paramType = "body", dataType = "Integer", example = "4")
	@PostMapping("/listAllDirAndOwnTree")
	public CommonRespT<MenuBackAllAndOwnTreeVo> listAllDirAndOwnTree(@RequestBody Integer roleId) {
		return ResponseUtil.successT(menuService.listAllDirAndOwnTree(roleId));
	}

	@ApiOperation(value = "更新角色对应的菜单集合", response = CommonResp.class)
	@ApiOperationSupport(order = 4)
	@PostMapping(value = "/updateRoleMenu")
	public void editUserMenu(@RequestBody @Validated UpdateRoleMenuParam param) {
		log.warn("UpdateRoleMenuParam:" + param);
		menuService.updateRoleMenu(param.getRoleId(), param.getMenuIdList());
	}

	@ApiOperation(value = "查询菜单的表格树", notes = "查询目录、菜单和操作按钮")
	@ApiOperationSupport(order = 5)
	@PostMapping("/listMenuTableTree")
	public CommonRespT<List<MenuBackTableTreeVo>> listMenuTableTree() {
		return ResponseUtil.successT(menuService.listMenuTableTree());
	}

	@ApiOperation(value = "新增菜单", response = CommonResp.class)
	@ApiOperationSupport(order = 6, includeParameters = { "url", "name", "parentId", "type", "seq", "icon" })
	@PostMapping(value = "/insert")
	public void insert(@RequestBody @Validated({ MenuBack.Insert.class }) MenuBack param) {
		log.warn("insert:" + param);
		menuService.insert(new MenuBack(param));
	}

	@ApiOperation(value = "更新菜单", response = CommonResp.class)
	@ApiOperationSupport(order = 6, includeParameters = { "id", "parentId", "type", "url", "name", "seq", "icon",
			"enable" })
	@PostMapping(value = "/update")
	public void update(@RequestBody @Validated({ MenuBack.Update.class }) MenuBack param) {
		log.warn("update:" + param);
		menuService.update(new MenuBack(param));
	}

	@ApiOperation(value = "删除菜单项", response = CommonResp.class)
	@ApiOperationSupport(order = 7)
	@ApiImplicitParam(name = "id", value = "主键", required = true, dataTypeClass = Integer.class)
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public void delete(@RequestBody Integer id) {
		menuService.delete(id);
	}

	@ApiOperation(value = "批量删除菜单项", response = CommonResp.class)
	@ApiOperationSupport(order = 8)
	@ApiImplicitParam(name = "idList", value = "主键集合", required = true, allowMultiple = true, dataTypeClass = Integer.class)
	@PostMapping(value = "/deleteList")
	public void deleteList(@RequestBody List<Integer> idList) {
		menuService.deleteList(idList);
	}

	@ApiOperation(value = "禁用菜单项", response = CommonResp.class)
	@ApiOperationSupport(order = 9)
	@ApiImplicitParam(name = "id", value = "主键", required = true, dataTypeClass = Integer.class)
	@RequestMapping(value = "/disable", method = RequestMethod.POST)
	public void disable(@RequestBody Integer id) {
		menuService.disable(id);
	}

	@ApiOperation(value = "批量禁用菜单项", response = CommonResp.class)
	@ApiOperationSupport(order = 10)
	@ApiImplicitParam(name = "idList", value = "主键集合", required = true, allowMultiple = true, dataTypeClass = Integer.class)
	@PostMapping(value = "/disableList")
	public void disableList(@RequestBody List<Integer> idList) {
		menuService.disableList(idList);
	}

//	/**
//	 * 递归查询菜单列表
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/selectMenuList", method = RequestMethod.GET)
//	public List<Menu> selectMenuList() {
//		return menuService.selectMenuList();
//	}

//	/**
//	 * 查找父级菜单项
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/selectFatherMenuList", method = RequestMethod.GET)
//	public List<Menu> selectFatherMenuList() {
//		return menuService.selectFatherMenuList();
//	}
//
//	/**
//	 * 新增前检查是否有重复
//	 * 
//	 * @param menu 要查重的信息集合
//	 * @return 重复信息
//	 */
//	@RequestMapping(value = "/addCheckSame", method = RequestMethod.POST)
//	public String addCheckSame(@RequestBody Menu menu) {
//
//		menu.setPath(StringUtil.isEmpty(menu.getPath()) ? null : menu.getPath());
//		System.out.println(menuService.addCheckSame(menu));
//		return ResponseUtil.success(menuService.addCheckSame(menu));
//	}
//	/**
//	 * 过滤查找
//	 * 
//	 * @param menu 要查询的信息集合
//	 * @return 过滤查询得到信息的集合
//	 */
//	@RequestMapping(value = "/filter", method = RequestMethod.POST)
//	public PageInfo<Menu> filter(@RequestBody String str) {
//		JSONObject strj = new JSONObject(str);
//		Menu menu = new Menu();
//		// 过滤查询条件
//		menu.setTitle("".equals(strj.getString("title")) ? null : strj.getString("title"));
//		// 当前页索引
//		Integer pageNum = strj.getInt("pageNum");
//		// 当前页页面大小
//		Integer pageSize = strj.getInt("pageSize");
//
//		return menuService.selectMenuByTitle(menu, pageNum, pageSize);
//	}
//	/**
//	 * 更新菜单项
//	 * 
//	 * @param menu
//	 */
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public void update(@RequestBody Menu menu) {
//
//		menuService.update(menu);
//	}
//

}
