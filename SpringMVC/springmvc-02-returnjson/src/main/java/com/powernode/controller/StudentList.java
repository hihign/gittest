package com.powernode.controller;

import com.powernode.pojo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StudentList {
    @RequestMapping("/student")
    @ResponseBody
    public List<Student> retJson(){
        List<Student> studentList=new ArrayList<Student>();
       Student student1 = new Student("张三",23);
       Student student2 = new Student("李四",24);
       Student student3 = new Student("王五",25);
       studentList.add(student1);
       studentList.add(student2);
       studentList.add(student3);
       return  studentList;
    }
}
