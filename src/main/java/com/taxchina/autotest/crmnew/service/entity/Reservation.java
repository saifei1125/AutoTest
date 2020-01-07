package com.taxchina.autotest.crmnew.service.entity;
//app约课
public class Reservation {
    private int id;
    private String companyId;
    private String companyName;
    private String clientName;
    private String clientNumber;
    private String invitationCode;
    private int reservationStatus;
    private String custUserlevel;
    private String courseId;
    private String courseName;
    private String contractProductId;
    private String contractNo;
    private String createUserId;
    private String createBy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientNumber() {
        return clientNumber;
    }

    public void setClientNumber(String clientNumber) {
        this.clientNumber = clientNumber;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public int getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(int reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public String getCustUserlevel() {
        return custUserlevel;
    }

    public void setCustUserlevel(String custUserlevel) {
        this.custUserlevel = custUserlevel;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getContractProductId() {
        return contractProductId;
    }

    public void setContractProductId(String contractProductId) {
        this.contractProductId = contractProductId;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
