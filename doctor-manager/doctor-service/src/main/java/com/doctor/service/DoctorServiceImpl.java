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
    public Doctor findById(Integer id) {
        Doctor doctor = doctorMapper.findById(id);
        if (doctor.getDenger().equals("0")) {
            doctor.setDenger("保密");
        } else if (doctor.getDenger().equals("1")) {
            doctor.setDenger("男");
        } else if (doctor.getDenger().equals("2")) {
            doctor.setDenger("女");
        }
        return doctor;
    }

    @Override
    public int updateStatus(int id, int status) {
        return doctorMapper.updateStatus(id, status);
    }

    @Override
    public int updateDoctor(Doctor doctor){
        if (doctor.getDenger().equals("保密")){
            doctor.setDenger("0");
        }else if(doctor.getDenger().equals("男")){
            doctor.setDenger("1");
        }else if(doctor.getDenger().equals("女")){
            doctor.setDenger("2");
        }
        return doctorMapper.updateDoctor(doctor);
    }

    @Override
    public int deleteDoctor(int id){
        return doctorMapper.deleteDoctor(id);
    }

}
