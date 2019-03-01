package com.doctor.Iservice;

import com.doctor.pojo.Doctor;

public interface IDoctorService {
    Doctor findByPhone(String params);

    Doctor findByAccount(String params);
}
