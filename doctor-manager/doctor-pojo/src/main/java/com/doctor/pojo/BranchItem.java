package com.doctor.pojo;

public class BranchItem {
    private Integer itemId;

    private String itemNum;

    private String itemName;

    private String itemMsg;

    private String itemParentNum;

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum == null ? null : itemNum.trim();
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemMsg() {
        return itemMsg;
    }

    public void setItemMsg(String itemMsg) {
        this.itemMsg = itemMsg == null ? null : itemMsg.trim();
    }

    public String getItemParentNum() {
        return itemParentNum;
    }

    public void setItemParentNum(String itemParentNum) {
        this.itemParentNum = itemParentNum == null ? null : itemParentNum.trim();
    }
}