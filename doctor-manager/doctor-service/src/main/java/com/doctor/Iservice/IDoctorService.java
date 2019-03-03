package com.doctor.Iservice;

import com.doctor.pojo.Doctor;
import io.swagger.models.auth.In;

import java.util.List;

public interface IDoctorService {
    Doctor findByPhone(String params);

    Doctor findByAccount(String params);

    List<Doctor> selctDoctotList();

    Doctor findById(Integer findById);

    int updateStatus(int id ,int status);
}
