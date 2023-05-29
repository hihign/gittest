package com.powernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JumpAction {
    @RequestMapping("/action")
    public String action(){
        return "main";
    }

    @RequestMapping("/request2page1")
    public String first(){
        System.out.println("跳转到页面");
        return "main";
    }

    @RequestMapping("/request2action1")
    public String second(){
        System.out.println("跳转到action");
        //转发是一次请求，转发到action需要Servlet，所以路径需要加后缀.action
        return "forward:/action.action";
    }

    @RequestMapping("request2page2")
    public String third(){
        System.out.println("重定向到页面");
        //重定向浏览器自发地向服务器重新发送了一次请求，而这次请求访问的是页面不需要controller处理的，所以重定向的路径后缀不需要加.action
        return "redirect:/admin/main.jsp";
    }

    @RequestMapping("/request2action2")
    public String fourth(){
        System.out.println("重定向到action");
        return "redirect:/action.action";
    }
}
