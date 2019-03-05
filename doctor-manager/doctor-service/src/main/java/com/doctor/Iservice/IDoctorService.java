package com.doctor.Iservice;

import com.doctor.pojo.Doctor;

import java.util.List;

public interface IDoctorService {
    Doctor findByPhone(String params);

    Doctor findByAccount(String params);

    List<Doctor> findByItemParentNum(String itemParentNum );

    List<Doctor> selctDoctotList();

    Doctor findById(Integer findById);

    int updateStatus(int id ,int status);

    int updateDoctor(Doctor doctor);

    int deleteDoctor(int id);
}
