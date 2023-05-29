package com.powernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class WebInfAction {
    @RequestMapping("/showMain")
    public String showMain(){
        System.out.println("访问main.jsp");
        return "main";
    }
    @RequestMapping("/showMainPlus")
    public String showMainPlus(String username, String passwd, HttpSession session){
        if ("admin".equals(username) && "123".equals(passwd)) {
            session.setAttribute("username",username);
            return "main";
        }
        session.setAttribute("msg","用户名或密码错误，请重新登录");
        return "forward:/index.jsp";
    }
}
