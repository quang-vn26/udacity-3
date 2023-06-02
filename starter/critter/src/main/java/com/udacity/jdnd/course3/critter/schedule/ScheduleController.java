package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.common.MapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {
        Schedule schedule = MapperDTO.convertToSchedule(scheduleDTO);
        return MapperDTO.convertToScheduleDto(scheduleService
                .saveSchedule(scheduleDTO.getEmployeeIds(), scheduleDTO.getPetIds(), schedule));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {
        return MapperDTO.convertToScheduleDtoList(scheduleService.getAllSchedules());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {
        return MapperDTO.convertToScheduleDtoList(scheduleService.getScheduleByPetId(petId));
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return MapperDTO.convertToScheduleDtoList(scheduleService.getScheduleByEmployeeId(employeeId));
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return MapperDTO.convertToScheduleDtoList(scheduleService.getScheduleByCustomerId(customerId));
    }
}
