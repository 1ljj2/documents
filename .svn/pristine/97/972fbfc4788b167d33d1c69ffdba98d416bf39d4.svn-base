package org.jit.sose.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 【我审核的】请求跳转
 */
@Controller
@RequestMapping("/audit")
public class AuditView {

    /**
     * 文档
     */
    @GetMapping("/fileAudited")
    public String fileAuditedView() {
        return "file/fileAudited";
    }

    /**
     * 档案
     */
    @GetMapping("/archiveAudited")
    public String archiveAuditedView() {
        return "archive/archiveAudited";
    }
    
    /**
     * 公告
     */
    @GetMapping("/noticeAudited")
    public String noticeAuditedView() {
        return "remind/noticeAudited";
    }

}
