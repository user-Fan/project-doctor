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
    public List<Doctor> selctDoctotList() {
        return doctorMapper.selctDoctotList();
    }

    @Override
    public Doctor findById(Integer id){
        return doctorMapper.findById(id);
    }

    @Override
    public int updateStatus(int id ,int status) {
        return doctorMapper.updateStatus(id,status);
    }

}
