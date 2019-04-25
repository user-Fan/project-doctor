package com.doctor.pojo;

import java.util.Date;

public class Doctor {
    private Integer doctorId;

    private String doctorEmail;

    private String doctorAccount;

    private String doctorPassword;

    private String doctorName;

    private String doctorPhone;

    private String doctorCardId;

    private String denger;

    private Integer age;

    private String address;

    private String doctorTitle;

    private String doctorIntroduce;

    private String itemParentNum;

    private Integer permissionsId;

    private Date creatTime;

    private String creatby;

    private Date updateTime;

    private String updateby;

    private Integer doctorStatus;

    private String photo;

    public String getDoctorEmail() {
        return doctorEmail;
    }

    public void setDoctorEmail(String doctorEmail) {
        this.doctorEmail = doctorEmail;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorAccount() {
        return doctorAccount;
    }

    public void setDoctorAccount(String doctorAccount) {
        this.doctorAccount = doctorAccount == null ? null : doctorAccount.trim();
    }

    public String getDoctorPassword() {
        return doctorPassword;
    }

    public void setDoctorPassword(String doctorPassword) {
        this.doctorPassword = doctorPassword == null ? null : doctorPassword.trim();
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName == null ? null : doctorName.trim();
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone == null ? null : doctorPhone.trim();
    }

    public String getDoctorCardId() {
        return doctorCardId;
    }

    public void setDoctorCardId(String doctorCardId) {
        this.doctorCardId = doctorCardId == null ? null : doctorCardId.trim();
    }

    public String getDenger() {
        return denger;
    }

    public void setDenger(String denger) {
        this.denger = denger == null ? null : denger.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDoctorTitle() {
        return doctorTitle;
    }

    public void setDoctorTitle(String doctorTitle) {
        this.doctorTitle = doctorTitle == null ? null : doctorTitle.trim();
    }

    public String getDoctorIntroduce() {
        return doctorIntroduce;
    }

    public void setDoctorIntroduce(String doctorIntroduce) {
        this.doctorIntroduce = doctorIntroduce == null ? null : doctorIntroduce.trim();
    }

    public String getItemParentNum() {
        return itemParentNum;
    }

    public void setItemParentNum(String itemParentNum) {
        this.itemParentNum = itemParentNum;
    }

    public Integer getpermissionsId() {
        return permissionsId;
    }

    public void setpermissionsId(Integer permissionsId) {
        this.permissionsId = permissionsId;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getCreatby() {
        return creatby;
    }

    public void setCreatby(String creatby) {
        this.creatby = creatby == null ? null : creatby.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateby() {
        return updateby;
    }

    public void setUpdateby(String updateby) {
        this.updateby = updateby == null ? null : updateby.trim();
    }

    public Integer getDoctorStatus() {
        return doctorStatus;
    }

    public void setDoctorStatus(Integer doctorStatus) {
        this.doctorStatus = doctorStatus;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", doctorEmail='" + doctorEmail + '\'' +
                ", doctorAccount='" + doctorAccount + '\'' +
                ", doctorPassword='" + doctorPassword + '\'' +
                ", doctorName='" + doctorName + '\'' +
                ", doctorPhone='" + doctorPhone + '\'' +
                ", doctorCardId='" + doctorCardId + '\'' +
                ", denger='" + denger + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", doctorTitle='" + doctorTitle + '\'' +
                ", doctorIntroduce='" + doctorIntroduce + '\'' +
                ", itemParentNum='" + itemParentNum + '\'' +
                ", permissionsId=" + permissionsId +
                ", creatTime=" + creatTime +
                ", creatby='" + creatby + '\'' +
                ", updateTime=" + updateTime +
                ", updateby='" + updateby + '\'' +
                ", doctorStatus=" + doctorStatus +
                ", photo='" + photo + '\'' +
                '}';
    }
}