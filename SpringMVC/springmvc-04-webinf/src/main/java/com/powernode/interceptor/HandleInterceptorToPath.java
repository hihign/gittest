package com.powernode.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HandleInterceptorToPath implements HandlerInterceptor {
    /*在请求被处理前拦截*/
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        if (session.getAttribute("username")!=null && session.getAttribute("username")!="") {
            return true;
        }
        request.setAttribute("msg","请先登录");
        request.getRequestDispatcher("/index.jsp").forward(request,response);
        return false;
    }

}
