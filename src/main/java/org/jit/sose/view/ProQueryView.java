package org.jit.sose.view;

/**
 * @author wufang
 * @Date 2020-09-27 14:17:32
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 【流程查询】请求跳转
 */
@Controller
public class ProQueryView {

    /**
     * 流程管理
     */
    @GetMapping("/proQuery")
    public String proQueryView() {
        return "process/proQuery";
    }

}
