package com.powernode.spring6.dao;

import java.util.Properties;

public class TeacherProperties {
    private Properties teacher;

    @Override
    public String toString() {
        return "TeacherProperties{" +
                "teacher=" + teacher +
                '}';
    }

    public void setTeacher(Properties teacher) {
        this.teacher = teacher;
    }
}
