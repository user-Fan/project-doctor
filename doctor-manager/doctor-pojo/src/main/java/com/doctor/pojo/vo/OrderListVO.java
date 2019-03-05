package com.doctor.pojo.vo;

public class OrderListVO {
    //用户姓名
    private String userName;
    //医生姓名
    private String doctorName;
    //挂号号码
    private String registrationNum;
    //挂号时间
    private String registrationTime;
    //就诊时间
    private String attendanceTime;
    //挂号原因
    private String registrationCause;
    //订单状态
    private String orderState;
    //交易积分
    private String integral;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getRegistrationNum() {
        return registrationNum;
    }

    public void setRegistrationNum(String registrationNum) {
        this.registrationNum = registrationNum;
    }

    public String getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(String registrationTime) {
        this.registrationTime = registrationTime;
    }

    public String getAttendanceTime() {
        return attendanceTime;
    }

    public void setAttendanceTime(String attendanceTime) {
        this.attendanceTime = attendanceTime;
    }

    public String getRegistrationCause() {
        return registrationCause;
    }

    public void setRegistrationCause(String registrationCause) {
        this.registrationCause = registrationCause;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }
}
