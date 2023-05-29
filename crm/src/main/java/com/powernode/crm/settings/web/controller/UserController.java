package com.powernode.crm.settings.web.controller;

import com.powernode.crm.commons.constants.Constant;
import com.powernode.crm.commons.domain.ReturnObject;
import com.powernode.crm.commons.utils.DateFormatUtils;
import com.powernode.crm.settings.domain.User;
import com.powernode.crm.settings.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin(){
        return "settings/qx/user/login";
    }

    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
    public Object login(String loginAct, String loginPwd, String isRemPwd, HttpServletRequest request, HttpServletResponse response, HttpSession session){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("loginAct",loginAct);
        map.put("loginPwd",loginPwd);
        User user = userService.queryUserByLoginActAndPwd(map);

        //根据查询结果返回响应信息
        ReturnObject returnObject = new ReturnObject();
        if (user==null) {
            //登陆失败，账号或密码错误
            returnObject.setCode(Constant.RETURN_OBJECT_FAIL_CODE);
            returnObject.setMessage("账号或密码错误");
        }else {
            if (user.getExpireTime().compareTo(DateFormatUtils.formateDateTime(new Date()))<0) {
                //登陆失败，账号已过期
                returnObject.setCode(Constant.RETURN_OBJECT_FAIL_CODE);
                returnObject.setMessage("账号已过期");
            } else if ("0".equals(user.getLockState())) {
                //登陆失败，账号被锁定
                returnObject.setCode(Constant.RETURN_OBJECT_FAIL_CODE);
                returnObject.setMessage("账号被锁定");
            } else if (!user.getAllowIps().contains(request.getRemoteAddr())) {
                //登陆失败，IP受限
                returnObject.setCode(Constant.RETURN_OBJECT_FAIL_CODE);
                returnObject.setMessage("IP受限");
            }else {
                //登陆成功
                returnObject.setCode(Constant.RETURN_OBJECT_SUCCESS_CODE);
                session.setAttribute(Constant.SESSION_USER,user);

                //如果选了记住密码，十天内免登录，往外写cookie
                if ("true".equals(isRemPwd)) {
                    Cookie cookie1 = new Cookie("loginAct", user.getLoginAct());
                    Cookie cookie2 = new Cookie("loginPwd", user.getLoginPwd());
                    cookie1.setMaxAge(10*24*60*60);
                    cookie2.setMaxAge(10*24*60*60);
                    response.addCookie(cookie1);
                    response.addCookie(cookie2);
                }else {
                    //如果前端不想免登录了，删除cookie
                    Cookie cookie1 = new Cookie("loginAct", "1");
                    Cookie cookie2 = new Cookie("loginPwd", "1");
                    cookie1.setMaxAge(0);
                    cookie2.setMaxAge(0);
                    response.addCookie(cookie1);
                    response.addCookie(cookie2);
                }
            }
        }
        return returnObject;
    }

    @RequestMapping("settings/qx/user/logout.do")
    public String logout(HttpServletResponse response,HttpServletRequest request){
        Cookie cookie1 = new Cookie("loginAct", "1");
        Cookie cookie2 = new Cookie("loginPwd", "1");
        cookie1.setMaxAge(0);
        cookie2.setMaxAge(0);
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        HttpSession session = request.getSession(true);
        session.invalidate();
        return "redirect:/";
    }

}
