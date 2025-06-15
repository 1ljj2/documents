package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 【我的提醒】请求跳转
 */
@Controller
//@RequestMapping("/myRemind")
public class MyRemindView {

    /**
     * 我的提醒
     */
	@GetMapping("/myRemind")
    public String myRemindView() {
        return "remind/myRemind";
    }

}
