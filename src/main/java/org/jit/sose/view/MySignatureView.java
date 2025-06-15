package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 【我的签章】请求跳转
 */
@Controller
public class MySignatureView {

    /**
     * 我的签章
     */
    @GetMapping("/mySignature")
    public String mySignatureView() {
        return "signature/mySignature";
    }

}
