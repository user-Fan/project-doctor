package com.doctor.api;

import com.doctor.pojo.Doctor;

import java.util.List;

public interface DoctorMapper {

    Doctor findByPhone(String userPhone);

    Doctor findByAccount(String userLogin);

    int insertDoctor(Doctor doctor);

    List<Doctor> findByItemParentNum(String itemParentNum);
}