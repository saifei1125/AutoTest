package com.taxchina.autotest.crmnew.dao.entity;

public class AppointmentCoursesCase {
    private int caseId;
    private String lkmName;
    private String lkmMobile;
    private String linkMap;
    private String courseName;
    private String courseId;
    private int companyId;
    private String companyName;
    private String appointmentCoursesExpect;

    public int getCaseId() {
        return caseId;
    }

    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    public String getLkmMobile() {
        return lkmMobile;
    }

    public void setLkmMobile(String lkmMobile) {
        this.lkmMobile = lkmMobile;
    }

    public String getLinkMap() {
        return linkMap;
    }

    public void setLinkMap(String linkMap) {
        this.linkMap = linkMap;
    }

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

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAppointmentCoursesExpect() {
        return appointmentCoursesExpect;
    }

    public void setAppointmentCoursesExpect(String appointmentCoursesExpect) {
        this.appointmentCoursesExpect = appointmentCoursesExpect;
    }
}
