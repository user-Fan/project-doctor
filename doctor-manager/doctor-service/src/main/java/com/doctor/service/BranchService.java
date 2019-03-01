package com.doctor.service;

import com.doctor.Iservice.IBranchService;
import com.doctor.api.BranchItemMapper;
import com.doctor.api.BranchMapper;
import com.doctor.pojo.Branch;
import com.doctor.pojo.BranchItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService implements IBranchService {

    @Autowired
    BranchMapper branchMapper;

    @Autowired
    BranchItemMapper branchItemMapper;


    @Override
    public List<Branch> getAllBranch() {
         return  branchMapper.selectAll();
    }

    @Override
    public List<BranchItem> getAllBranchItem(String itemParentNum) {
        return branchItemMapper.selectBranchItemsByitemParentNum(itemParentNum);
    }

}
