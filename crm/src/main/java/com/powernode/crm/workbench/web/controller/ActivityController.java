package com.powernode.crm.workbench.web.controller;

import com.powernode.crm.commons.constants.Constant;
import com.powernode.crm.commons.domain.ReturnObject;
import com.powernode.crm.commons.utils.DateFormatUtils;
import com.powernode.crm.commons.utils.UUIDUtils;
import com.powernode.crm.settings.domain.User;
import com.powernode.crm.settings.service.UserService;
import com.powernode.crm.workbench.domain.Activity;
import com.powernode.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class ActivityController {
    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        List<User> users = userService.queryAllUsers();
        request.setAttribute("users",users);
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/createActivity.do")
    @ResponseBody
    public Object createActivity(Activity activity, HttpSession session){
        //前端参数不够，再次封装参数
        User user = (User) session.getAttribute(Constant.SESSION_USER);
        activity.setId(UUIDUtils.UUIDRandom());
        activity.setCreateBy(user.getId());  //谁登录，创建者就是谁
        activity.setCreateTime(DateFormatUtils.formateDateTime(new Date()));

        //根据查询结果返回响应
        ReturnObject returnObject = new ReturnObject();
        try{
            int count = activityService.createActivity(activity);
            if (count>0){
                returnObject.setCode(Constant.RETURN_OBJECT_SUCCESS_CODE);
            }else {
                returnObject.setCode(Constant.RETURN_OBJECT_FAIL_CODE);
                returnObject.setMessage("系统繁忙，请稍后.....");
            }
        }catch (Exception e){
            returnObject.setCode(Constant.RETURN_OBJECT_FAIL_CODE);
            returnObject.setMessage("系统繁忙，请稍后.....");
        }
        return returnObject;
    }
}
