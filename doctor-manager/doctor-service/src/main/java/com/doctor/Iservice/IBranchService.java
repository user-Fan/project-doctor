package com.doctor.Iservice;

import com.doctor.pojo.Branch;
import com.doctor.pojo.BranchItem;

import java.util.List;

public interface IBranchService {

    /**获取所有科*/
    public List<Branch>  getAllBranch();

    /**获取所有科下的所有目*/
    public List<BranchItem>  getAllBranchItem(String itemParentNum );
}
