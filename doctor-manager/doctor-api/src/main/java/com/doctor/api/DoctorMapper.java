package com.doctor.api;

import com.doctor.pojo.Doctor;

public interface DoctorMapper {

    Doctor findByPhone(String userPhone);

    Doctor findByAccount(String userLogin);

    int insertDoctor(Doctor doctor);
}