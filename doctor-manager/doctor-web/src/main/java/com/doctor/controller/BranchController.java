package com.doctor.controller;

import com.doctor.Iservice.IBranchService;
import com.doctor.pojo.Branch;
import com.doctor.pojo.BranchItem;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BranchController {

    @Autowired
    IBranchService BranchService;

    @RequestMapping("getAllBranch")
    @ResponseBody
    public List<Branch> getAllBranch() {
        List<Branch> result = BranchService.getAllBranch();
        return result;
    }

    @RequestMapping("getBranchItems")
    @ResponseBody
    public List<BranchItem> getBranchItems(String itemParentNum) {
        return BranchService.getAllBranchItem("WK000");
    }

    @RequestMapping("getAllBranchItems")
    @ResponseBody
    //获取所有科目表
    public Map<String, List<BranchItem>> getBranchItems() {
        Map<String, List<BranchItem>> map = new HashMap<>();
        List<Branch> result = BranchService.getAllBranch();
        if (null != result && result.size() >= 0) {
            for (Branch branch : result) {
                if (null != branch && StringUtils.isNotBlank(branch.getBranchName())&&StringUtils.isNotBlank(branch.getBranchNum())) {
                    String branchName = branch.getBranchName();
                        String  branchNum = branch.getBranchNum();
                        map.put(branchName,BranchService.getAllBranchItem(branchNum));
                }
            }
        }
        return map;
    }
}