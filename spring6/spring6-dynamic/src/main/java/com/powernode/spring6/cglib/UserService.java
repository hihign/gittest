package com.powernode.spring6.cglib;

public class UserService {
    public boolean login(String username,String password){
        if ("admin".equals(username) && "123".equals(password)) {
            return true;
        }
        return false;
    }
    public void logout(){
        System.out.println("正在退出系统....");
    }
}
