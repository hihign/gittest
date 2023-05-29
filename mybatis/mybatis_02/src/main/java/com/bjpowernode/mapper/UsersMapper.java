package com.bjpowernode.mapper;

import com.bjpowernode.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UsersMapper {
    List<User> getAll();
    //根据用户主键查询用户
    User getById(Integer id);
    //根据用户名模糊查询用户
    List<User> getByName(String username);
    //用户的更新
    int update(User user);
    //增加用户
    int insert(User user);
    //根据主键删除用户
    int delete(Integer id);
    //优化后的模糊查询
    List<User> getByNamePlus(String username);
    //按用户名或地址模糊查询用户信息
    List<User> getByNameOrAddress(
            @Param("nameOrAge")
            String nameOrAge,
            @Param("value")
            String value);
}
