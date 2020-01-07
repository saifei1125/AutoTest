package com.taxchina.autotest.crmnew.service.entity;

public class Course {
    private String courseId;
    private String courseName;
    private String teacherName;
    private String partCompany;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPartCompany() {
        return partCompany;
    }

    public void setPartCompany(String partCompany) {
        this.partCompany = partCompany;
    }

}
