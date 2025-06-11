package org.jit.sose.controller;

import lombok.extern.slf4j.Slf4j;
import org.jit.sose.util.ResponseUtil;
import org.jit.sose.util.WebServletUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 页面权限校验,web端
 * 
 * @author wangyue
 * @date 2020-04-19 22:21:32
 */
@Slf4j
@RestController
public class ViewCheckController {

	/**
	 * 检查页面权限
	 */
	@GetMapping("/{path}.htm")
	public String checkView(@PathVariable String path, HttpServletRequest request) {
		log.trace("ip:{},path:{}", WebServletUtil.getClientIpAddress(request), path);
		return ResponseUtil.success();
	}

	@GetMapping("/{path}/{pathSec}.htm")
	public String checkViewSec(@PathVariable String path, @PathVariable String pathSec, HttpServletRequest request) {
		log.trace("ip:{},/path/pathSec:{}", WebServletUtil.getClientIpAddress(request), "/" + path + "/" + pathSec);
		return ResponseUtil.success();
	}

}
