package com.udacity.jdnd.course3.critter.schedule;

import java.util.List;

public interface ScheduleService {

    Schedule saveSchedule(List<Long> employeeIds, List<Long> petIds, Schedule schedule);

    List<Schedule> getAllSchedules();

    List<Schedule> getScheduleByPetId(Long petId);

    List<Schedule> getScheduleByEmployeeId(Long employeeId);

    List<Schedule> getScheduleByCustomerId(Long customerId);
}
