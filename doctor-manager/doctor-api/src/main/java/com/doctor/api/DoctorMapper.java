package com.doctor.api;

import com.doctor.pojo.Doctor;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DoctorMapper {

    Doctor findByPhone(String userPhone);

    Doctor findByAccount(String userLogin);

    int insertDoctor(Doctor doctor);

    List<Doctor> selctDoctotList();

    Doctor findById(Integer id);

    int updateStatus(@Param("id")int id,@Param("status")int status);

    int updateDoctor(Doctor doctor);

    int deleteDoctor(int id);
}