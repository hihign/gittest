package com.bjpowernode.crm.workbench.web.controller;

import com.bjpowernode.crm.commons.contants.Contants;
import com.bjpowernode.crm.commons.domain.ReturnObject;
import com.bjpowernode.crm.commons.utils.DateUtils;
import com.bjpowernode.crm.commons.utils.HSSFUtils;
import com.bjpowernode.crm.commons.utils.UUIDUtils;
import com.bjpowernode.crm.settings.domain.User;
import com.bjpowernode.crm.settings.service.UserService;
import com.bjpowernode.crm.workbench.domain.Activity;
import com.bjpowernode.crm.workbench.domain.ActivityRemark;
import com.bjpowernode.crm.workbench.service.ActivityRemarkService;
import com.bjpowernode.crm.workbench.service.ActivityService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

@Controller
public class ActivityController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActivityService activityService;
    @Autowired
    private ActivityRemarkService activityRemarkService;

    @RequestMapping("/workbench/activity/index.do")
    public String index(HttpServletRequest request){
        //调用service层方法，查询所有的用户
        List<User> userList=userService.queryAllUsers();
        //把数据保存到request中
        request.setAttribute("userList",userList);
        //请求转发到市场活动的主页面
        return "workbench/activity/index";
    }

    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    public @ResponseBody Object saveCreateActivity(Activity activity, HttpSession session){
        User user=(User) session.getAttribute(Contants.SESSION_USER);
        //封装参数
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateUtils.formateDateTime(new Date()));
        activity.setCreateBy(user.getId());

        ReturnObject returnObject=new ReturnObject();
        try {
            //调用service层方法，保存创建的市场活动
            int ret = activityService.saveCreateActivity(activity);

            if(ret>0){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }else{
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙,请稍后重试....");
            }
        }catch (Exception e){
            e.printStackTrace();

            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙,请稍后重试....");
        }

        return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivity.do")
    @ResponseBody
    public Object queryActivity(String name,String owner,String startDate,String endDate,Integer pageNo,Integer pageSize){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("name",name);
        map.put("owner",owner);
        map.put("startDate",startDate);
        map.put("endDate",endDate);
        map.put("pageNo",(pageNo-1)*pageSize);
        map.put("pageSize",pageSize);
        List<Activity> activityList = activityService.queryActivityByConditionForPage(map);
        int count = activityService.queryCountOfActivityForPage(map);
        Map<String,Object> objectMap = new HashMap<String, Object>();
        objectMap.put("activityList",activityList);
        objectMap.put("count",count);
        return objectMap;
    }

    @RequestMapping("/workbench/activity/deleteActivity.do")
    @ResponseBody
    public Object deleteActivity(String[] id){
        ReturnObject returnObject = new ReturnObject();
        try{
            int retVal= activityService.deleteActivityByIds(id);
            if (retVal<=0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后再试");
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }
        }catch (Exception e){
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后再试");
        }
       return returnObject;
    }

    @RequestMapping("/workbench/activity/queryActivityById.do")
    @ResponseBody
    public Object queryActivityById(String id){
        return activityService.queryActivityById(id);
    }

    @RequestMapping("/workbench/activity/saveEditActivity.do")
    @ResponseBody
    public Object saveEditActivity(Activity activity,HttpSession session){
        User user = (User)session.getAttribute(Contants.SESSION_USER);
        activity.setEditTime(DateUtils.formateDate(new Date()));
        activity.setEditBy(user.getId());
        ReturnObject returnObject = new ReturnObject();
        try{
            int ret = activityService.saveEditActivity(activity);
            if (ret==0) {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
                returnObject.setMessage("系统忙，请稍后重试。");
            }else {
                returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            }
        }catch (Exception e){
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后重试。");
        }
        return returnObject;
    }

    @RequestMapping("/workbench/activity/exportAllActivities.do")
    public void exportAllActivities(HttpServletResponse response) throws Exception{
        List<Activity> activityList = activityService.queryAllActivities();
        //生成excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("市场活动列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell=row.createCell(1);
        cell.setCellValue("所有者");
        cell=row.createCell(2);
        cell.setCellValue("名称");
        cell=row.createCell(3);
        cell.setCellValue("开始日期");
        cell=row.createCell(4);
        cell.setCellValue("结束日期");
        cell=row.createCell(5);
        cell.setCellValue("成本");
        cell=row.createCell(6);
        cell.setCellValue("描述");
        cell=row.createCell(7);
        cell.setCellValue("创建时间");
        cell=row.createCell(8);
        cell.setCellValue("创建者");
        cell=row.createCell(9);
        cell.setCellValue("编辑时间");
        cell=row.createCell(10);
        cell.setCellValue("编辑者");
        if (activityList!=null && activityList.size()>0) {
            Activity activity = null;
            for(int i=0;i<activityList.size();i++){
                activity = activityList.get(i);
                row = sheet.createRow(i + 1);
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
        //版本1：效率低（访问磁盘）
       /* //根据wb对象生成excel文件
        OutputStream os = new FileOutputStream("E:\\2022JavaProject\\activityList.xls");
        wb.write(os);
        os.close();
        wb.close();
        //把生成的excel文件下载到客户端
        //1、设置响应的内容类型
        response.setContentType("application/octet-stream;charset=UTF-8");
        //2、设置响应头
        response.addHeader("Content-Disposition","attachment;filename=activityList.xls");
        //3、利用OuputStream输出流，将文件输出到浏览器
        OutputStream out = response.getOutputStream();
        InputStream in = new FileInputStream("E:\\2022JavaProject\\activityList.xls");
        byte[] bytes = new byte[256];
        int len=0;
        while ((len = in.read(bytes)) != -1) {
            out.write(bytes,0,len);
        }
        in.close();
        out.flush();*/

        //版本2：效率高（数据从内存到内存）
        //1、设置响应内容类型
        response.setContentType("application/octet-stream;charset=UTF-8");
        //2、设置响应头
        response.setHeader("Content-Disposition","attachment;filename=activityList.xls");
        //输出文件到浏览器
        OutputStream os = response.getOutputStream();
        wb.write(os);
    }


    @RequestMapping("/workbench/activity/exportSelectedActivity.do")
    @ResponseBody
    public void exportSelectedActivity(
            @RequestParam("id")
            String[] id,
            HttpServletResponse response) throws Exception{
        List<Activity> activityList = activityService.querySelectedActivity(id);
        //生成excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("市场活动列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell=row.createCell(1);
        cell.setCellValue("所有者");
        cell=row.createCell(2);
        cell.setCellValue("名称");
        cell=row.createCell(3);
        cell.setCellValue("开始日期");
        cell=row.createCell(4);
        cell.setCellValue("结束日期");
        cell=row.createCell(5);
        cell.setCellValue("成本");
        cell=row.createCell(6);
        cell.setCellValue("描述");
        cell=row.createCell(7);
        cell.setCellValue("创建时间");
        cell=row.createCell(8);
        cell.setCellValue("创建者");
        cell=row.createCell(9);
        cell.setCellValue("编辑时间");
        cell=row.createCell(10);
        cell.setCellValue("编辑者");
        if (activityList!=null && activityList.size()>0) {
            Activity activity = null;
            for(int i=0;i<activityList.size();i++){
                activity = activityList.get(i);
                row = sheet.createRow(i + 1);
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }

        //设置响应内容类型
        response.setContentType("application/octet-stream;charset=UTF-8");
        //设置响应头
        response.addHeader("Content-Disposition","attachment;filename=activityList.xls");
        //将文件响应到客户端
        OutputStream os = response.getOutputStream();
        wb.write(os);
        os.flush();
        wb.close();
    }

    @RequestMapping("/workbench/activity/fileUpload.do")
    @ResponseBody
    public Object fileUpload(MultipartFile activityFile,String userName,HttpSession session){
        System.out.println(userName);
        User user = (User) session.getAttribute(Contants.SESSION_USER);
        ReturnObject returnObject = new ReturnObject();
        try{
           /* 版本1：效率低，访问磁盘
           //将接收的文件写入到服务器中的某个目录中
            String originalFilename=activityFile.getOriginalFilename();
            File file = new File("E:\\2022JavaProject\\",originalFilename);
            activityFile.transferTo(file);

            //利用poi插件解析excel文件
            InputStream is = new FileInputStream("E:\\2022JavaProject\\"+originalFilename);*/
            //版本2：效率高，通过流
            InputStream is = activityFile.getInputStream();
            HSSFWorkbook wb = new HSSFWorkbook(is);
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFRow row = null;
            HSSFCell cell = null;
            Activity activity = null;
            List<Activity> activityList = new ArrayList<>();
            for (int i=1;i<=sheet.getLastRowNum();i++){
                row = sheet.getRow(i);
                activity = new Activity();
                activity.setId(UUIDUtils.getUUID());
                activity.setOwner(user.getId());
                activity.setCreateBy(user.getId());
                activity.setCreateTime(DateUtils.formateDate(new Date()));
                for (int j=0;j<row.getLastCellNum();j++){
                    cell = row.getCell(j);
                    String cellValue= HSSFUtils.getCellValue(cell);
                    //根据列号，将取的值封装到Activity类中不同属性
                    if (j==0){
                        activity.setName(cellValue);
                    }else if (j==1){
                        activity.setStartDate(cellValue);
                    }else if (j==2){
                        activity.setEndDate(cellValue);
                    }else if (j==3){
                        activity.setCost(cellValue);
                    }else if (j==4){
                        activity.setDescription(cellValue);
                    }
                }
                activityList.add(activity);
            }
            int count = activityService.saveCreateActivityByBatch(activityList);
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_SUCCESS);
            returnObject.setRetData(count);
        }catch (Exception e){
            e.printStackTrace();
            returnObject.setCode(Contants.RETURN_OBJECT_CODE_FAIL);
            returnObject.setMessage("系统忙，请稍后再试");
        }
        return  returnObject;
    }


    @RequestMapping("/workbench/activity/queryActivityDetail.do")
    public String queryActivityDetail(String id,HttpServletRequest request){
        Activity activity = activityService.queryActivityForDetailById(id);
        List<ActivityRemark> activityRemarkList = activityRemarkService.queryActivityRemarkForDetailByActivityId(id);
        request.setAttribute("activity",activity);
        request.setAttribute("activityRemarkList",activityRemarkList);
        return "workbench/activity/detail";
    }
}
