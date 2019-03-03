package com.doctor.controller;

import com.doctor.Iservice.IDoctorService;
import com.doctor.pojo.Doctor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DoctorController {


    @Autowired
    IDoctorService doctorService;

    public final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    @RequestMapping("getDoctors")
    @ResponseBody
    public List<Doctor> getDoctor(String itemParentNum){
        logger.info("getDoctors||itemParentNum{}",itemParentNum);
        List<Doctor> result = doctorService.findByItemParentNum(itemParentNum);
        logger.info("getDoctors||result||{}",result);
        return result;
    };
}
