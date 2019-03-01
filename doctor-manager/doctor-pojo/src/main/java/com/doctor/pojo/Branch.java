package com.doctor.pojo;

public class Branch {
    private Integer branchId;

    private String branchName;

    private String branchNum;

    private String branchMsg;

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
    }

    public String getBranchNum() {
        return branchNum;
    }

    public void setBranchNum(String branchNum) {
        this.branchNum = branchNum == null ? null : branchNum.trim();
    }

    public String getBranchMsg() {
        return branchMsg;
    }

    public void setBranchMsg(String branchMsg) {
        this.branchMsg = branchMsg == null ? null : branchMsg.trim();
    }
}