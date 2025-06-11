package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 【文档管理】请求跳转
 */
@Controller
public class FileManView {

    /**
     * 文档管理
     */
    @GetMapping("/fileMan")
    public String fileManView() {
        return "file/fileMan";
    }


}
