package com.lizhao.my.shop.web.admin.web.interceptor;

import com.lizhao.my.shop.commons.constant.ConstantUtils;
import com.lizhao.my.shop.domain.TbUser;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * <p>Title: LoginInterceptor</p>
 * <p>Description</p>
 * @author 10943
 * @version 1.0.0
 */
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        // 进入首页拦截：如果没有登录，禁止进入首页
        TbUser user = (TbUser) httpServletRequest.getSession().getAttribute(ConstantUtils.SESSION_USER);

        // 未登录
        if (user == null){
            httpServletResponse.sendRedirect("/login");
        }
        // 放行
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
