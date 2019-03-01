package com.doctor.api;

import com.doctor.pojo.BranchItem;

import java.util.List;

public interface BranchItemMapper {
    int deleteByPrimaryKey(Integer itemId);

    int insert(BranchItem record);

    int insertSelective(BranchItem record);

    BranchItem selectByPrimaryKey(Integer itemId);

    int updateByPrimaryKeySelective(BranchItem record);

    int updateByPrimaryKey(BranchItem record);

    List<BranchItem> selectBranchItemsByitemParentNum(String itemParentNum);
}