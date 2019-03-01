package com.doctor.controller;

import com.doctor.Iservice.IBranchService;
import com.doctor.pojo.Branch;
import com.doctor.pojo.BranchItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BranchController {

    @Autowired
    IBranchService BranchService;

    @RequestMapping("getAllBranch")
    @ResponseBody
    public List<Branch> getAllBranch(){
        List<Branch> result = BranchService.getAllBranch();
        return result;
    }

    @RequestMapping("getAllBranchItems")
    @ResponseBody
    public List<BranchItem> getAllBranchItems(String itemParentNum){
        return BranchService.getAllBranchItem("WK000");
    }


}
