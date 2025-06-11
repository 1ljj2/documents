package org.jit.sose.web.handler;

import com.alibaba.fastjson.JSON;
import org.jit.sose.web.response.CommonResp;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GoLogoutSuccessHandler implements LogoutSuccessHandler{

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
        throws IOException, ServletException {
        // TODO Auto-generated method stub
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.setStatus(200);
        CommonResp commonResp = new CommonResp();
        commonResp.setCode(200);
        commonResp.setMsg("logout success");
        response.getWriter().write(JSON.toJSONString(commonResp));
        response.getWriter().flush();
    }

}
