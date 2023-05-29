package com.bjpowernode.servlet;
//模板设计方法
//在模板类的模板方法当中定义核心算法骨架，具体的实现方法可以延迟到子类当中完成
public abstract class DayOfTeacher {
    public void invoke(){
        getUp();
        eatBreakfirst();
        goToSchool();
        goHome();
    }

    public void getUp(){
        System.out.println("早上起床");
    }

    public void eatBreakfirst(){
        System.out.println("吃早饭");
    }


    //老师特有的方法
    public abstract void goToSchool();


    public void goHome(){
        System.out.println("回家");
    }
}
