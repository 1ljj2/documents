package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 【流程管理】请求跳转
 */
@Controller
public class ProcessView {

    /**
     * 流程管理
     */
    @GetMapping("/process")
    public String processView() {
        return "process/process";
    }


}
