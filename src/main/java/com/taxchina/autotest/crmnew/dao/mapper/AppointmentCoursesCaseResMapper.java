package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.AppointmentCoursesCaseRes;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AppointmentCoursesCaseResMapper {

    int setAppointmentCoursesCaseRes(AppointmentCoursesCaseRes appointmentCoursesCaseRes);

}
