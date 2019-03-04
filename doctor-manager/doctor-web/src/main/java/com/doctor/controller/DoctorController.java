package com.doctor.controller;

import com.doctor.Iservice.IDoctorService;
import com.doctor.common.ReturnUtil;
import com.doctor.pojo.Doctor;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DoctorController {


    @Autowired
    IDoctorService doctorService;

    public final Logger logger = LoggerFactory.getLogger(DoctorController.class);

    /*@RequestMapping("getDoctors")
    @ResponseBody
    public List<Doctor> getDoctor(String itemParentNum){
        logger.info("getDoctors||itemParentNum{}",itemParentNum);
        List<Doctor> result = doctorService.findByItemParentNum(itemParentNum);
        logger.info("getDoctors||result||{}",result);
        return result;
    };*/

    //医生list
    @ApiOperation(value = "医生list")
    @RequestMapping(value = "/doctorList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String doctorList() {
        try {
            List<Doctor> doctors = doctorService.selctDoctotList();
            return ReturnUtil.toJSONString(0, "查询成功", doctors);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.toJSONString(1, "系统错误", null);
        }
    }

    //医生禁用启用
    @RequestMapping(value = "/activateD", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public int activateD(@RequestParam("id") int id) {
        try {
            Doctor doctor = doctorService.findById(id);
            int status = 0;
            if (doctor.getDoctorStatus() == 0) {
                //未激活 需要启用
                status = 1;
            } else if (doctor.getDoctorStatus() == 1) {
                //已启用 需要禁用
                status = 0;
            }
            int rel = doctorService.updateStatus(id, status);
            if (rel >= 1) {
                return 0;
            }
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    //医生详情
    @ApiOperation(value = "医生详情")
    @RequestMapping("/findByDId")
    @ResponseBody
    public String findByDId(@RequestParam("id") int id) {
        try {
            Doctor doctor = doctorService.findById(id);
            if (null == doctor){
                return ReturnUtil.toJSONString(1, "查询失败", null);
            }
            return ReturnUtil.toJSONString(0, "查询成功", doctor);
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.toJSONString(1, "系统错误", null);
        }
    }

    //编辑医生信息
    @ApiOperation(value = "医生详情")
    @RequestMapping(value = "/updateDoctor", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateDoctor(Doctor doctor){
        System.out.println(doctor.toString());
        try {
            if (null == doctor){
                return ReturnUtil.toJSONString(1, "没有医生信息", null);
            }
                int rel = doctorService.updateDoctor(doctor);
                if (rel == 0){
                    return ReturnUtil.toJSONString(1, "修改医生信息失败", null);
                }
                return ReturnUtil.toJSONString(0, "修改成功", null);
        }catch (Exception e){
            e.printStackTrace();
            return ReturnUtil.toJSONString(1, "系统错误", null);
        }
    }

    //删除医生
    @ApiOperation(value = "删除医生")
    @RequestMapping(value = "/deleteDoctor", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public int deleteDoctor(@RequestParam("id") int id){
        try {
            int rel = doctorService.deleteDoctor(id);
            if (rel == 0){
                return 1;
            }
            return 0;
        }catch (Exception e){
            e.printStackTrace();
            return 1;
        }
    }
}
