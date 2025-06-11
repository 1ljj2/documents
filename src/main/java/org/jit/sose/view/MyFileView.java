package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 【我的文档】   页面请求跳转
 */
@Controller
public class MyFileView {

    /**
     * 跳转我的文档页面
     */
    @GetMapping("/myFile")
    public String myFileView() {
        return "file/myFile";
    }

}
