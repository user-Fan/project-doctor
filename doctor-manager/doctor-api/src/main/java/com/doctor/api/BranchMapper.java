package com.doctor.api;

import com.doctor.pojo.Branch;

import java.util.List;

public interface BranchMapper {
    int deleteByPrimaryKey(Integer branchId);

    int insert(Branch record);

    int insertSelective(Branch record);

    Branch selectByPrimaryKey(Integer branchId);

    int updateByPrimaryKeySelective(Branch record);

    int updateByPrimaryKey(Branch record);

    List<Branch> selectAll();

}