package com.doctor.service;

import com.doctor.Iservice.IDoctorService;
import com.doctor.api.DoctorMapper;
import com.doctor.pojo.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("IDoctorService")
public class DoctorServiceImpl implements IDoctorService {

    @Autowired
    DoctorMapper doctorMapper;

    @Override
    public Doctor findByPhone(String params) {
        return doctorMapper.findByPhone(params);
    }

    @Override
    public Doctor findByAccount(String params) {
        return doctorMapper.findByAccount(params);
    }

    @Override
    public List<Doctor> findByItemParentNum(String itemParentNum) {
        List<Doctor> result = doctorMapper.findByItemParentNum(itemParentNum);
      for (int i = 0;i<result.size();i++){
          if (null!=result.get(i)&&null!=result.get(i).getDenger()){
              String denger  = result.get(i).getDenger();
              if (denger.equals("0")){
                  denger = "保密";
              }else if (denger.equals("1")){
                  denger = "男";
              }else if(denger.equals("2")){
                  denger = "女";
              }
              result.get(i).setDenger(denger);
          }
      }
        return result;
    }

}
