package com.taxchina.autotest.crmnew.dao.mapper;

import com.taxchina.autotest.crmnew.dao.entity.AppointmentCoursesCase;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface AppointmentCoursesCaseMapper {

    AppointmentCoursesCase getAppointmentCoursesCaseById(int caseId);

    int setAppointmentCoursesCase(AppointmentCoursesCase appointmentCoursesCase);

}
