package org.jit.sose.web.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Date:2021-03-16
 * Time:15:23
 */
public class CheckEnvironmentFilter extends OncePerRequestFilter {
    private int checkEnvironmentFlag;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        checkEnvironmentFlag=0;
        String requestURI = request.getRequestURI();
        System.out.println(requestURI);
        System.out.println("LoginUrl");
        String requestUri = request.getRequestURI();
        System.out.println("当前强强1111：："+requestUri);
        System.out.println("是否进入+++++++++++++++++++++++++++++++++++++");
            System.out.println("未授权！！！！！！！！！！！！");
            getMyEnvironment();
        if (checkEnvironmentFlag==2){
            System.out.println("cuowu");
            response.sendRedirect("/");
//            request.getRequestDispatcher("/501").forward(request, response);
            System.out.println("cuowu===");
            return;
        }
        filterChain.doFilter(request, response);
    }

    private void getMyEnvironment() {
        checkEnvironmentFlag=2;
    }
}
