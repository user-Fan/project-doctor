package com.doctor.service;

import com.doctor.Iservice.IDoctorService;
import com.doctor.api.DoctorMapper;
import com.doctor.pojo.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
