package com.doctor.pojo;

import java.util.Date;

public class User {

	/*
	 * CREATE TABLE `user` ( `user_id` int(8) unsigned zerofill NOT NULL
	 * AUTO_INCREMENT COMMENT 'user表的id', `user_name` varchar(5) NOT NULL COMMENT
	 * '用户名', `user_age` int(5) DEFAULT NULL COMMENT '年龄', `user_id_card`
	 * varchar(20) DEFAULT NULL COMMENT '身份证号', `user_phone` varchar(30) NOT NULL
	 * COMMENT '手机号', `user_Email` varchar(40) CHARACTER SET utf8 COLLATE
	 * utf8_unicode_ci DEFAULT NULL COMMENT '邮箱', `user_address` varchar(50) NOT
	 * NULL COMMENT '联系地址', `suer_status` int(1) NOT NULL DEFAULT '0' COMMENT
	 * '0未激活1使用中-1逻辑删除', `user_gender` varchar(2) DEFAULT '保密' COMMENT '性别',
	 * `creat_time` date DEFAULT NULL COMMENT '创建时间', `creatBy` varchar(12) NOT NULL
	 * COMMENT '创建人', `user_login` varchar(12) NOT NULL COMMENT '登录名', `update_time`
	 * timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	 * `updateBy` varchar(12) DEFAULT NULL COMMENT '更新人', `photo` varchar(20)
	 * DEFAULT NULL COMMENT '头像', `permissions_id` int(8) DEFAULT NULL COMMENT
	 * '权限编号', PRIMARY KEY (`user_id`) ) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT
	 * CHARSET=utf8 ROW_FORMAT=DYNAMIC;
	 */
	private Integer userId;
	private String userName;
	private Integer userAge;
	private String userIdCard;
	private String userPhone;
	private String userEmail;
	private String userAddress;
	private Integer userStatus;
	private String userGender;
	private Date creatTime;
	private String creatBy;
	private String userLogin;
	private Integer update_time;
	private String updateBy;
	private String photo;
	private Integer permissionsId;
	private String Password;
	private Integer passwordId;

	public User() {
		super();
	}

	public Integer getPasswordId(){return passwordId;}

	public void setPasswordId(Integer passwordId){this.passwordId = passwordId;}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getUserAge() {
		return userAge;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public String getUserIdCard() {
		return userIdCard;
	}

	public void setUserIdCard(String userIdCard) {
		this.userIdCard = userIdCard;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public Integer getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}

	public String getCreatBy() {
		return creatBy;
	}

	public void setCreatBy(String creatBy) {
		this.creatBy = creatBy;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public Integer getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Integer update_time) {
		this.update_time = update_time;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getPermissionsId() {
		return permissionsId;
	}

	public void setPermissionsId(Integer permissionsId) {
		this.permissionsId = permissionsId;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public User(Integer userId, String userName, Integer userAge, String userIdCard, String userPhone, String userEmail, String userAddress, Integer userStatus, String userGender, Date creatTime, String creatBy, String userLogin, Integer update_time, String updateBy, String photo, Integer permissionsId, String password,Integer passwordId) {
		this.userId = userId;
		this.userName = userName;
		this.userAge = userAge;
		this.userIdCard = userIdCard;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userAddress = userAddress;
		this.userStatus = userStatus;
		this.userGender = userGender;
		this.creatTime = creatTime;
		this.creatBy = creatBy;
		this.userLogin = userLogin;
		this.update_time = update_time;
		this.updateBy = updateBy;
		this.photo = photo;
		this.permissionsId = permissionsId;
		Password = password;
		this.passwordId = passwordId;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", userName='" + userName + '\'' +
				", userAge=" + userAge +
				", userIdCard='" + userIdCard + '\'' +
				", userPhone='" + userPhone + '\'' +
				", userEmail='" + userEmail + '\'' +
				", userAddress='" + userAddress + '\'' +
				", userStatus=" + userStatus +
				", userGender='" + userGender + '\'' +
				", creatTime=" + creatTime +
				", creatBy='" + creatBy + '\'' +
				", userLogin='" + userLogin + '\'' +
				", update_time=" + update_time +
				", updateBy='" + updateBy + '\'' +
				", photo='" + photo + '\'' +
				", permissionsId=" + permissionsId +
				", Password='" + Password + '\'' +
				'}';
	}
}
