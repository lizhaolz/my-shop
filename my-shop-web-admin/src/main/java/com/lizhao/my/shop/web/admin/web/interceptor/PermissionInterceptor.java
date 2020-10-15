package com.lizhao.my.shop.web.admin.web.interceptor;

import com.lizhao.my.shop.commons.constant.ConstantUtils;
import com.lizhao.my.shop.domain.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 */
public class PermissionInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return true;
    }

    // 在登陆之后如果还访问login页面，则重定向到首页
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        // 以login结尾的请求
        if (modelAndView != null && modelAndView.getViewName() != null && modelAndView.getViewName().endsWith("login")) {
            TbUser user = (TbUser) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);
            // 已经登陆，踢回首页
            if (user!=null) {
                httpServletResponse.sendRedirect("/main");
            }
        }
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
