package com.qigan.qiganshop.util.filter;

import com.alibaba.druid.util.StringUtils;
import com.qigan.qiganshop.pojo.AppUser;
import com.qigan.qiganshop.service.AppUserService;
import com.qigan.qiganshop.util.result.format.JsonResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")

public class TokenFilter implements Filter {

    private WebApplicationContext wac;
    private JsonResult jr;
    private AppUserService service;
    List<String> doFilterUrls = new ArrayList<>();

    @Override
    public void init(FilterConfig filterConfig) {
        wac = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());
        service = (AppUserService) wac.getBean("appUserServiceImpl");
        jr = (JsonResult) wac.getBean("jsonResult");

    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) {
        // 放行的 URL
        doFilterUrls.add("/qiganshop/app/Goods/findOne.do");
        doFilterUrls.add("/qiganshop/manager/logout.do");

        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setCharacterEncoding("utf-8");
        //response.setContentType("application/json; charset=utf-8");


        // token 标志,不存在
        boolean flag = false;
        String token = null;
        try {
            String requestURI = request.getRequestURI();

            // 如果请求地址是被放行地址,直接放行
            if (doFilterUrls.contains(requestURI)) {
                chain.doFilter(servletRequest, servletResponse);
            } else {

                // 从参数中获取token
                token = ServletRequestUtils.getStringParameter(request, "token");
                if (!StringUtils.isEmpty(token)) {
                    // 如果请求参数中带 token
                    //存在
                    flag = true;

                }

                if (flag) {
                    // 有 token 的方法
                    if (StringUtils.isEmpty(token)) {
                        // token 为空
                        response.setStatus(400);
                        PrintWriter writer = response.getWriter();
                        writer.write(jr.jsonResultData("400", "参数不全").toString());
                        writer.close();

                    } else {
                        // token 不为空
                        AppUser user = service.getAppUserByToken(token);
                        if (user == null) {
                            // 查不到用户
                            response.setStatus(401);
                            PrintWriter pw = response.getWriter();
                            pw.write(jr.jsonResultData("401").toString());
                            pw.flush();
                            pw.close();
                        } else {
                            chain.doFilter(servletRequest, servletResponse);
                        }
                    }
                } else {
                    chain.doFilter(servletRequest, servletResponse);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }

}
