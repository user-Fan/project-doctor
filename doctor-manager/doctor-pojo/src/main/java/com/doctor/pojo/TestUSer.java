package com.doctor.pojo;

/**
 * @Auther: Administrator
 * @Date: 2018/10/20 16:02
 * @Description:
 */
public class TestUSer {
    private String UserName;
    private String UserId;
    private String Userage;
    private String Usergender;
    private String UserAddess;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserage() {
        return Userage;
    }

    public void setUserage(String userage) {
        Userage = userage;
    }

    public String getUsergender() {
        return Usergender;
    }

    public void setUsergender(String usergender) {
        Usergender = usergender;
    }

    public String getUserAddess() {
        return UserAddess;
    }

    public void setUserAddess(String userAddess) {
        UserAddess = userAddess;
    }

    public TestUSer() {
    }

    public TestUSer(String userName, String userId, String userage, String usergender, String userAddess) {
        UserName = userName;
        UserId = userId;
        Userage = userage;
        Usergender = usergender;
        UserAddess = userAddess;
    }

    @Override
    public String toString() {
        return "TestUSer{" +
                "UserName='" + UserName + '\'' +
                ", UserId='" + UserId + '\'' +
                ", Userage='" + Userage + '\'' +
                ", Usergender='" + Usergender + '\'' +
                ", UserAddess='" + UserAddess + '\'' +
                '}';
    }
}
