package com.bjpowernode.crm.workbench.service;

import com.bjpowernode.crm.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

public interface ActivityService {
    int saveCreateActivity(Activity activity);

    List<Activity> queryActivityByConditionForPage(Map<String,Object> map);

    int queryCountOfActivityForPage(Map<String,Object> map);

    int deleteActivityByIds(String[] id);

    /**
     * 根据id查询市场活动
     */
    Activity queryActivityById(String id);

    /**
     * 保存更新的市场活动
     */
    int saveEditActivity(Activity activity);

    /**
     * 查询所有的市场活动
     */
    List<Activity> queryAllActivities();

    /**
     * 根据id查询市场活动
     */
    List<Activity> querySelectedActivity(String[] id);

    /**
     * 批量插入市场活动
     */
    int saveCreateActivityByBatch(List<Activity> activityList);

    Activity queryActivityForDetailById(String id);
}
